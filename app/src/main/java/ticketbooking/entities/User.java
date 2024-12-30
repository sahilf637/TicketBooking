package ticketbooking.entities;

import java.util.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

  private String name;
  private String password;
  private String hashPassword;
  private List<Ticket> ticketsBooked;
  private String userId;

  public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userId) {
    this.name = name;
    this.password = password;
    this.hashPassword = hashedPassword;
    this.ticketsBooked = ticketsBooked;
    this.userId = userId;
  }

  public User() {
  }

  public String getName() {
    return name;
  }

  public String getHashPassword() {
    return hashPassword;
  }

  public String getPassword() {
    return password;
  }

  public void printTickets() {
    for (Ticket ticket : ticketsBooked) {
      System.out.println(ticket.getTicketInfo());
    }
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setHashPassword(String hashPassword) {
    this.hashPassword = hashPassword;
  }

  public List<Ticket> getTicketsBooked() {
    return ticketsBooked;
  }

  public void setTicketsBooked(List<Ticket> ticketsBooked) {
    this.ticketsBooked = ticketsBooked;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserId() {
    return userId;
  }
}
