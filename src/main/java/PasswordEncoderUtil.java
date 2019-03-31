import static org.hamcrest.CoreMatchers.containsString;

import java.util.Scanner;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {
  public static void main(String[] args) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	  String input = "sven";
	  System.out.println(encoder.encode(input));
	  String input2 = "stan";
	  System.out.println(encoder.encode(input2));

  }
}
