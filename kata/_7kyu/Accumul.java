package kata._7kyu;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Accumul
 *
 */

/*
  This time no story, no theory. The examples below show you how to write function accum:

  Examples:

  accum("abcd")     -> "A-Bb-Ccc-Dddd"
  accum("RqaEzty")  -> "R-Qq-Aaa-Eeee-Zzzzz-Tttttt-Yyyyyyy"
  accum("cwAt")     -> "C-Ww-Aaa-Tttt"
  The parameter of accum is a string which includes only letters from a..z and A..Z.
*/
public class Accumul {

  public static void main(String[] args) {
    System.out.println("abcd");
    System.out.println(accum("abcd"));
    System.out.println(accumFunction("abcd"));
  }

  public static String accum(String s) {
    StringBuilder bldr = new StringBuilder();
    int i = 0;
    for (char c : s.toCharArray()) {
      if (i > 0) bldr.append('-');
      bldr.append(Character.toUpperCase(c));
      for (int j = 0; j < i; j++) bldr.append(Character.toLowerCase(c));
      i++;
    }
    return bldr.toString();
  }

  // ---------------------------------------------------------------------------------------------------------------
  public static String accumFunction(String s) {
    return IntStream
      .range(0, s.length())
      .mapToObj(
        i ->
          Stream
            .generate(() -> s.charAt(i))
            .limit(i + 1)
            .collect(
              StringBuilder::new,
              StringBuilder::append,
              StringBuilder::append
            )
            .toString()
      )
      .map(a -> a.substring(0, 1).toUpperCase() + a.substring(1).toLowerCase())
      .collect(Collectors.joining("-"));
  }

  // ---------------------------------------------------------------------------------------------------------------
  public static final String DELIMITER = "-";

  public static String accumMix(String input) {
    List<String> stringList = IntStream
      .range(0, input.length())
      .mapToObj(i -> duplicateCharFirstUpper(input.charAt(i), i + 1))
      .collect(Collectors.toList());

    return String.join(DELIMITER, stringList);
  }

  private static String duplicateCharFirstUpper(
    char c,
    int numberOfAppearances
  ) {
    char upperCase = Character.toUpperCase(c);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(upperCase);

    for (int i = 1; i < numberOfAppearances; i++) {
      stringBuilder.append(Character.toLowerCase(c));
    }

    return stringBuilder.toString();
  }
}
