package flatmap;

import java.util.ArrayList;
import java.util.List;

/**
 * SchoolClass
 */
public class SchoolClass {

  private final String className;
  private final ArrayList<Student> studentList = new ArrayList<>();

  public SchoolClass(String className) {
    this.className = className;
  }

  public String getClassName() {
    return this.className;
  }

  public void addStudent(Student student) {
    this.studentList.add(student);
    System.out.println("Adding Student to Class " + this.className);
  }

  public List<Student> getStudentList() {
    return this.studentList;
  }
}
