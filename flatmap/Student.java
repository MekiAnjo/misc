package flatmap;

/**
 * Student
 */
public class Student {

  private final String name;
  private final int age;
  private final String phoneNumber;

  public Student(String name, int age, String phoneNumber) {
    this.name = name;
    this.age = age;
    this.phoneNumber = phoneNumber;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }
}