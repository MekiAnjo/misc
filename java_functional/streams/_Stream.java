package java_functional.streams;

import static java_functional.streams._Stream.Gender.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * _Stream
 */
public class _Stream {

  public static void main(String[] args) {
    List<Person> people = List.of(
      new Person("John", MALE),
      new Person("Maria", FEMALE),
      new Person("Aisha", FEMALE),
      new Person("Alex", MALE),
      new Person("Alice", FEMALE),
      new Person("Bob", PREFER_NOT_TO_SAY)
    );

    // people
    //   .stream()
    //   .map(person -> person.name) // wandelt die gender von den Person Objekten
    //   .mapToInt(String::length)
    //   // .collect(Collectors.toSet()) // speichert die gender in ein Set (keine Duplikate)
    //   .forEach(System.out::println);

    boolean containsOnlyFemales = people
      .stream()
      .allMatch(person -> FEMALE.equals(person.gender));

    System.out.println(containsOnlyFemales);
  }

  static class Person {

    private final String name;
    private final Gender gender;

    Person(String name, Gender gender) {
      this.name = name;
      this.gender = gender;
    }

    @Override
    public String toString() {
      return "Person{" + "name= " + name + ", gender= " + gender + "}";
    }
  }

  enum Gender {
    MALE,
    FEMALE,
    PREFER_NOT_TO_SAY,
  }
}
