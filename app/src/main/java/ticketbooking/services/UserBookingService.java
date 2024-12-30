package ticketbooking.services;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import ticketbooking.entities.User;
import ticketbooking.utils.UserServiceUtil;
import ticketbooking.entities.Ticket;

public class UserBookingService {
  private User user;

  private static final String USERS_PATH = "./src/main/java/ticketbooking/localDB/users.json";
  private List<User> userList;

  private ObjectMapper objectMapper = new ObjectMapper();

  public UserBookingService(User user1) throws IOException {
    this.user = user1;
    loadUserList();
  }

  public UserBookingService() throws IOException {
    loadUserList();
  }

  private List<User> loadUserList() throws IOException {
    File user = new File(USERS_PATH);
    return userList = objectMapper.readValue(user, new TypeReference<List<User>>() {
    });
  }

  public Boolean loginUser() {
    Optional<User> foundUser = userList.stream().filter(user1 -> {
      return user1.getName().equalsIgnoreCase(user.getName())
          && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashPassword());
    }).findFirst();
    return foundUser.isPresent();
  }

  public Boolean signUp(User user1) {
    try {
      userList.add(user1);
      saveUserListToFile();
      return Boolean.TRUE;
    } catch (IOException e) {
      return Boolean.FALSE;
    }
  }

  private void saveUserListToFile() throws IOException {
    File usersFile = new File(USERS_PATH);
    objectMapper.writeValue(usersFile, userList);
  }

  public void fetchBooking() {
    user.printTickets();
  }

  public Boolean cancelBooking() throws IOException {
    Scanner s = new Scanner(System.in);
    System.out.println("Enter the ticket id to cancel the booking");
    String ticketId = s.nextLine();
    try {
      if (ticketId == null || ticketId.isEmpty()) {
        return Boolean.FALSE;
      }
      user.getTicketsBooked().removeIf(ticket -> ticket.getTicketId().equals(ticketId));
      for (int i = 0; i < userList.size(); i++) {
        if (userList.get(i).getUserId().equals(user.getUserId())) {
          userList.set(i, user);
          saveUserListToFile();
          return Boolean.TRUE;
        }
      }
      return Boolean.FALSE;
    } catch (IOException e) {
      return Boolean.FALSE;
    }

  }
}
