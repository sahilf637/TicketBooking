package ticketbooking.services;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import ticketbooking.entities.User;

public class UserBookingService {
  private User user;

  private static final String USERS_PATH = "../localDB/users.json";

  private List<User> userList;

  private ObjectMapper objectMapper = new ObjectMapper();

  public UserBookingService(User user1) throws IOException {
    this.user = user1;

    File user = new File(USERS_PATH);

    userList = objectMapper.readValue(user, new TypeReference<List<User>>() {
    });

  }

  public Boolean loginUser() {
    Optional<User> foundUser = userList.stream().filter(user1 -> {
      return user1.getName().equals(user.getName()) && user1.getPassword().equals(user.getPassword());
    });
    return foundUser.isPresent();
  }
}
