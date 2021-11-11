package CSVSort;

/**
 * Student
 */
public class Student implements Comparable<Student> {

  private int studenID, mark;
  private String fName, lName, grade;

  public Student(
    int studenID,
    String lName,
    String fName,
    int mark,
    String grade
  ) {
    this.studenID = studenID;
    this.mark = mark;
    this.fName = fName;
    this.lName = lName;
    this.grade = grade;
  }

  public int getStudenID() {
    return this.studenID;
  }

  public void setStudenID(int studenID) {
    this.studenID = studenID;
  }

  public int getMark() {
    return this.mark;
  }

  public void setMark(int mark) {
    this.mark = mark;
  }

  public String getFName() {
    return this.fName;
  }

  public void setFName(String fName) {
    this.fName = fName;
  }

  public String getLName() {
    return this.lName;
  }

  public void setLName(String lName) {
    this.lName = lName;
  }

  public String getGrade() {
    return this.grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  @Override
  public int compareTo(Student s) {
    if (this.mark == s.mark) {
      return this.fName.compareTo(s.fName);
    } else {
      return (s.mark - this.mark) > 0 ? 1 : -1;
    }
  }

  @Override
  public String toString() {
    return (
      "Student{" +
      " studenID='" +
      getStudenID() +
      "'" +
      ", mark='" +
      getMark() +
      "'" +
      ", fName='" +
      getFName() +
      "'" +
      ", lName='" +
      getLName() +
      "'" +
      ", grade='" +
      getGrade() +
      "'" +
      "}"
    );
  }
}
