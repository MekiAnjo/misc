package flatmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * _FlatMap
 */
public class _FlatMap {

  public static void main(String[] args) {
    List<SchoolClass> schoolClasses = createSchoolClasses();

    // ! Imperative Way
    System.out.println("Imperative Way");
    List<String> ImpStudentList = new ArrayList<>();
    List<String> ImpStudentList2 = new ArrayList<>();

    // fori loop
    for (int i = 0; i < schoolClasses.size(); i++) {
      for (int j = 0; j < schoolClasses.get(i).getStudentList().size(); j++) {
        System.out.println(ImpStudentList);
        ImpStudentList.add(
          schoolClasses.get(i).getStudentList().get(j).getName()
        );
      }
    }
    System.out.println(ImpStudentList);

    // foreach loop
    for (SchoolClass schoolClass : schoolClasses) {
      for (Student student : schoolClass.getStudentList()) {
        ImpStudentList2.add(student.getName());
        System.out.println(ImpStudentList2);
      }
    }

    // ! Declarative Way
    System.out.println("Declarative Way");
    // Outputs a List in a List
    List<List<String>> entireSchoolStudentsList = schoolClasses
      .stream()
      .map(
        schoolClass ->
          schoolClass
            .getStudentList()
            .stream()
            .map(Student::getName)
            .collect(Collectors.toList())
      )
      .collect(Collectors.toList());

    // combines elements of two Lists into one (flattening them)
    List<String> studentNames = schoolClasses
      .stream()
      .flatMap(
        schoolClass -> {
          return schoolClass.getStudentList().stream().map(Student::getName);
        }
      )
      .collect(Collectors.toList());

    List<String> studentNames2 = schoolClasses
      .stream()
      .flatMap(
        schoolClass ->
          schoolClass.getStudentList().stream().map(Student::getName)
      )
      .collect(Collectors.toList());

    System.out.println(entireSchoolStudentsList);
    System.out.println(studentNames);
    System.out.println(studentNames2);
  }

  private static List<SchoolClass> createSchoolClasses() {
    SchoolClass christinasClass = new SchoolClass("Mrs. Christina");
    christinasClass.addStudent(new Student("bob", 11, "212-221-2332"));
    christinasClass.addStudent(new Student("veronica", 10, "212-221-4214"));
    christinasClass.addStudent(new Student("amy", 11, "212-221-3212"));
    christinasClass.addStudent(new Student("shane", 11, "212-232-2212"));

    SchoolClass shanesClass = new SchoolClass("Mr. Shane");
    shanesClass.addStudent(new Student("gabrielle", 11, "212-221-4358"));
    shanesClass.addStudent(new Student("nick", 10, "212-221-4522"));
    shanesClass.addStudent(new Student("bella", 11, "212-221-8744"));
    shanesClass.addStudent(new Student("juan", 11, "212-231-2272"));

    return Arrays.asList(christinasClass, shanesClass);
  }
}
