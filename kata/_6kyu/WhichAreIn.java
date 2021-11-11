package kata._6kyu;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * WhichAreIn
 * 
 * https://www.codewars.com/kata/550554fd08b86f84fe000a58/train/java
 * 
 * Given two arrays of strings a1 and a2 return a sorted array r in lexicographical order of the strings of a1 which are substrings of strings of a2.

    Example 1:
    a1 = ["arp", "live", "strong"]

    a2 = ["lively", "alive", "harp", "sharp", "armstrong"]

    returns ["arp", "live", "strong"]

    Example 2:
    a1 = ["tarp", "mice", "bull"]

    a2 = ["lively", "alive", "harp", "sharp", "armstrong"]

    returns []

    Notes:
    Arrays are written in "general" notation. See "Your Test Cases" for examples in your language.
    In Shell bash a1 and a2 are strings. The return is a string where words are separated by commas.
    Beware: r must be without duplicates.
 */

public class WhichAreIn {

  public static void main(String[] args) {
    String[] a1 = new String[] { "arp", "live", "strong", "123", "321", "12" };
    String[] a2 = new String[] {
      "lively",
      "alive",
      "harp",
      "sharp",
      "armstrong",
    };

    String[] solution = inArray(a1, a2);

    for (String string : solution) {
      System.out.println(string);
    }
  }

  public static String[] inArray(String[] array1, String[] array2) {
    Set<String> set = new HashSet<>();

    for (int i = 0; i < array1.length; i++) {
      for (int j = 0; j < array2.length; j++) {
        if (
          (array2[j].contains(array1[i])) &&
          (array2[j].length() >= array1[i].length())
        ) {
          set.add(array1[i]);
        }
      }
    }
    // String[] result = new String[set.size()];
    // set.toArray(result);
    // * Macht das gleiche wie die beiden oberen Zeilen
    String[] result = set.toArray(String[]::new);
    Arrays.sort(result);
    return result;
  }

  public static String[] inArray2(String[] array1, String[] array2) {
    Set<String> result = new HashSet<>();

    for (String a1 : array1) {
      for (String a2 : array2) {
        if (a2.contains(a1)) {
          result.add(a1);
          break; // * Durch das break wird verhindert das Duplikate in das Set gelangen
        }
      }
    }

    String[] resultArray = result.toArray(new String[result.size()]);

    Arrays.sort(resultArray);

    return resultArray;
  }

  public static String[] inArrayFunction(String[] array1, String[] array2) {
    return Arrays
      .stream(array1)
      .filter(str -> Arrays.stream(array2).anyMatch(s -> s.contains(str)))
      .distinct()
      .sorted()
      .toArray(String[]::new);
  }
}
