package orphan_ZeitGeldAufgaben.vorbereitung;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {

  public static void main(String[] args) {
    String outputFormat = "%-15s%s%n";
    System.out.printf(outputFormat, "Instant:", Instant.now());
    System.out.printf(outputFormat, "LocalDate:", LocalDate.now());
    System.out.printf(outputFormat, "LocalTime:", LocalTime.now());
    System.out.printf(outputFormat, "LocalDateTime:", LocalDateTime.now());
  }
}
