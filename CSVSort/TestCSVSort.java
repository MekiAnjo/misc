package CSVSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestCSVSort {

  private static final String CSV_PATH = "CSVSort\\students.csv";
  private static boolean append = true;
  private static List<String> aList = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    readAllLinesfromFile(CSV_PATH);
    System.out.println("Unsorted:\n");
    for (String aStudentString : aList) {
      System.out.println(aStudentString + "\n");
    }

    List<Student> students = converToStudents(aList);
    System.out.println("SORTED:\n");
    for (Student student : students) {
      System.out.println(student);
    }
  }

  private static List<String> readAllLinesfromFile(final String PATH)
    throws IOException {
    FileReader fileReader = new FileReader(PATH);
    BufferedReader buffereReader = new BufferedReader(fileReader);
    String line = null;
    while ((line = buffereReader.readLine()) != null) {
      aList.add(line);
    }
    buffereReader.close();

    return aList;
  }

  private static List<Student> converToStudents(List<String> studentsStrings) {
    List<Student> students = new ArrayList<>();
    studentsStrings.remove(0);
    for (String studentString : studentsStrings) {
      String[] parts = studentString.split(",");
      int studentID = Integer.valueOf(parts[0]);
      String fName = parts[1];
      String lName = parts[2];
      int mark = Integer.valueOf(parts[3]);
      String grade = parts[4];
      students.add(new Student(studentID, fName, lName, mark, grade));
    }
    // ! Outdated, use Lambdas instead
    // Collections.sort(
    //   students,
    //   new Comparator<Student>() {
    //     @Override
    //     public int compare(Student o1, Student o2) {
    //       return o1.compareTo(o2);
    //     }
    //   }
    // );
    students.sort((Student s1, Student s2) -> s1.compareTo(s2));

    return students;
  }

  private static void writeAllLinesToFile(
    final String PATH,
    List<String> aList
  ) throws IOException {
    FileWriter fileWriter = new FileWriter(PATH, append);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    for (String line : aList) {
      printWriter.printf("%s" + "%n", line);
    }
    printWriter.close();
  }
}
