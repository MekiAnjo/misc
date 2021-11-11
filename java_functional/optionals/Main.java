package java_functional.optionals;

import java.util.Optional;

/**
 * Main
 */
public class Main {

  public static void main(String[] args) {
    Optional
      .ofNullable("john@gmail.com")
      .ifPresentOrElse(
        email -> System.out.println("Sending email to " + email),
        () -> System.out.println("Cannot send email") // '()' ist ein runable, also einfach nur damit es ausgeführt wird
      );
  }
}
