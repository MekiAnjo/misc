package orphan_ZeitGeldAufgaben.altersunterschied;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

  private static final Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    String europeanDatePattern = "dd.MM.yyyy";
    DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(
      europeanDatePattern
    );
    System.out.print("Person 1: ");
    String dob1 = scan.nextLine();
    System.out.print("Person 2: ");
    String dob2 = scan.nextLine();

    Period p = Period.between(
      LocalDate.parse(dob1, europeanDateFormatter),
      LocalDate.parse(dob2, europeanDateFormatter)
    );
    System.out.printf(
      "Der Altersunterschied beträgt %s Jahre, %s Monate und %s Tage",
      p.getYears(),
      p.getMonths(),
      p.getDays()
    );
  }
}
