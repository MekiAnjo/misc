package java_functional.streams._stream_2020;

/**
 * Person
 */
public class Person {

  private final String name;
  private final int age;
  private final Gender gender;

  public Person(String name, int age, Gender gender) {
    this.name = name;
    this.age = age;
    this.gender = gender;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public Gender getGender() {
    return this.gender;
  }

  @Override
  public String toString() {
    return (
      "{" +
      " name='" +
      getName() +
      "'" +
      ", age='" +
      getAge() +
      "'" +
      ", gender='" +
      getGender() +
      "'" +
      "}"
    );
  }
}
