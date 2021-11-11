import java.util.Optional;

/**
 * Optionels
 */
public class Optionals {

  public static void main(String[] args) {
    var person = new Person("james", "JAMES@gmail.com");

    // Optinals selber wrappen
    if (person.getEmail().isPresent()) {
      String email = person.getEmail().get();
      System.out.println(email.toLowerCase());
    } else {
      System.out.println("email is not provided");
    }

    // Der eigentliche Weg
    String email = person
      .getEmail()
      .map(String::toLowerCase)
      .orElse("email not provided");
    System.out.println(email);
    // Optional<String> hello = Optional.ofNullable(null);

    // System.out.println(hello.isPresent());
    // System.out.println(hello.isEmpty());

    // hello.ifPresentOrElse(
    //   System.out::println,
    //   () -> System.out.println("world")
    // );
    // String orElse = hello
    //   .map(String::toUpperCase)
    //   .orElseThrow(IllegalArgumentException::new);

    // .orElseGet(
    //   () -> {
    //     // ... extra computation to retrieve the value
    //     return "world";
    //   }
    // );

    // .orElse("world");  // wenn hello nicht present dann else

  }
}

class Person {

  private final String name;
  private final String email;

  public Person(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public Optional<String> getEmail() {
    return Optional.ofNullable(email);
  }
}
