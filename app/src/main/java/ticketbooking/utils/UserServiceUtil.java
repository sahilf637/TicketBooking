package ticketbooking.utils;

import org.mindrot.jbcrypt.BCrypt;

public class UserServiceUtil {

  public static String hashPassword(String plainPasssword) {
    return BCrypt.hashpw(plainPasssword, BCrypt.gensalt());
  }

  public static boolean checkPassword(String plainPassword, String hashedPassword) {
    return BCrypt.checkpw(plainPassword, hashedPassword);
  }

}
