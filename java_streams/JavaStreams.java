package java_streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * JavaStreams
 * credit https://www.youtube.com/watch?v=t1-YZ6bF-g0
 */
public class JavaStreams {

  private static final String ABSTAND = "-".repeat(150);

  public static void main(String[] args) throws IOException {
    // 1. Integer Stream
    IntStream
      .range(1, 10) // Zählt die Integer Zahlen von 1 - 10 auf (10 nicht inkludiert)
      .forEach(System.out::print); // Integer Zahlen werden einer foreach Schleife weitergegeben und mit der print Funktion ausgegeben
    System.out.println();

    System.out.println(ABSTAND);

    // 2. Integer Stream with skip
    IntStream
      .range(1, 10)
      .skip(5) // Die ersten 5 Zahlen werden übersprungen
      .forEach(x -> System.out.println(x)); // x ist das Element das in einem Stream übergeben wird

    System.out.println(ABSTAND);

    // 3. Integer Stream with sum
    System.out.println(IntStream.range(1, 5).sum()); // Die übergebenen Elemente werden zusammen summiert

    System.out.println(ABSTAND);

    // 4. Stream.of, sorted and findFirst
    Stream
      .of("Ava", "Aneri", "Alberto")
      .sorted() // Sortiert nach dem Alphabet
      .findFirst() // Nimmt das erste Element
      .ifPresent(System.out::println); // Führt aus wenn etwas übergeben wurde

    System.out.println(ABSTAND);

    // 5. Stream from Array, sort, filter and print
    String[] names = {
      "Al",
      "Ankit",
      "Kushal",
      "Brent",
      "Sarika",
      "amanda",
      "Hans",
      "Shivika",
      "Sarah",
    };
    Arrays
      .stream(names) // same as Stream.of(names)
      .filter(x -> x.startsWith("S")) // Filter alle Elemente die mit "S" starten
      .sorted()
      .forEach(System.out::println);

    System.out.println(ABSTAND);

    // 6. average of squares of an int array
    Arrays
      .stream(new int[] { 2, 4, 6, 8, 10 })
      .map(x -> x * x) // Wendet Funktion nach dem Lambda auf alle Elemente an
      .average() // Nimmt den Durchschnitt vom Gesamten
      .ifPresent(System.out::println);

    System.out.println(ABSTAND);

    // 7. Stream from List, filter and print
    List<String> people = Arrays.asList(
      "Al",
      "Ankit",
      "Brent",
      "Sarika",
      "amanda",
      "Hans",
      "Shivika",
      "Sarah"
    );
    people
      .stream() // wandelt die Collection in einen Stream um
      .map(String::toLowerCase)
      .filter(x -> x.startsWith("a"))
      .forEach(System.out::println);

    System.out.println(ABSTAND);

    // 8. Stream rows from text file, sort, filter, and print
    Stream<String> bands = Files.lines(
      Paths.get(
        "C://Users/MekiAnjo/git/proving-ground/misc/java_streams/bands.txt"
      )
    );
    bands.sorted().filter(x -> x.length() > 13).forEach(System.out::println);
    bands.close();

    System.out.println(ABSTAND);

    // 9. Stream rows from text file and save to List
    List<String> bands2 = Files
      .lines(
        Paths.get(
          "C://Users/MekiAnjo/git/proving-ground/misc/java_streams/bands.txt"
        )
      )
      .filter(x -> x.contains("jit"))
      .collect(Collectors.toList()); // Speichert alle Elemente in ein Listen objekt
    bands2.forEach(x -> System.out.println(x));

    System.out.println(ABSTAND);

    // 10. Stream rows from CSV file and count
    Stream<String> rows1 = Files.lines(
      Paths.get(
        "C://Users/MekiAnjo/git/proving-ground/misc/java_streams/data.txt"
      )
    );
    int rowCount = (int) rows1
      .map(x -> x.split(",")) // trennt den String an den Kommas
      .filter(x -> x.length == 3) // filtert alle Reihen die nicht 3 Elemente enthalten
      .count(); // Zählt die übergebenen Elemente
    System.out.println(rowCount + " rows.");
    rows1.close();

    System.out.println(ABSTAND);

    // 11. Stream rows from CSV file, parse data from rows
    Stream<String> rows2 = Files.lines(
      Paths.get(
        "C://Users/MekiAnjo/git/proving-ground/misc/java_streams/data.txt"
      )
    );
    rows2
      .map(x -> x.split(","))
      .filter(x -> x.length == 3)
      .filter(x -> Integer.valueOf(x[1]) > 15) // Filtert alle Werte in den Reihen die größer als 15 sind an der Stelle 1
      .forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
    rows2.close();

    System.out.println(ABSTAND);

    // 12. Stream rows from CSV file, store fields in HashMap
    Stream<String> rows3 = Files.lines(Paths.get("java_streams\\data.txt"));
    Map<String, Integer> map = new HashMap<>();
    map =
      rows3
        .map(x -> x.split(","))
        .filter(x -> x.length == 3)
        .filter(x -> Integer.valueOf(x[1]) > 15)
        .collect(
          Collectors.toMap(
            x -> x[0], // x ist hierbei das Reihen Array
            x -> Integer.valueOf(x[1])
          )
        );
    rows3.close();
    // for (String key : map.keySet()) {  // Erste Iteration der Ausgabe
    //   System.out.println(key + " " + map.get(key));
    // }
    for (Map.Entry<String, Integer> entry : map.entrySet()) { // Zweite Iteration der Ausgabe (Besser wenn der Schlüssel und der Wert benötigt werden)
      String key = entry.getKey();
      Object value = entry.getValue();
      System.out.println(key + " " + value);
    }

    System.out.println(ABSTAND);

    // 13. Reduction - sum
    double total = Stream
      .of(7.3, 1.5, 4.8)
      // .reduce(0.0, (Double a, Double b) -> a + b); // reduziert alle übergebenen Elemente zu einem einzigen Wert, durch die Angegebene Funktion nach dem Lambda. '0.0' ist der Startwert, der zu 'a' zugewiesen wird und 'b' sind die übergebenen Elemente. Beim zweiten Durchlauf ist 'a' das frühere 'b' und 'b' das nächste übergebene Element
      .reduce(0.0, Double::sum); // macht das gleiche wie der Kommentar oben
    System.out.println("Total = " + total);

    System.out.println(ABSTAND);

    // 14. Reduction - summary statistics
    IntSummaryStatistics summary = IntStream
      .of(7, 2, 19, 88, 73, 4, 10)
      .summaryStatistics();
    System.out.println(summary);
  }
}
