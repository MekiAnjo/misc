package kata._7kyu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * IsoGram
 */
public class Isogram {

  // An isogram is a word that has no repeating letters, consecutive or non-consecutive. Implement a function that determines whether a string that contains only letters is an isogram. Assume the empty string is an isogram. Ignore letter case.

  public static void main(String[] args) {
    System.out.println(isIsogram("Dermatoglyphics"));
    System.out.println(isIsogram("aba"));
    System.out.println(isIsogram("moOse"));

    System.out.println(isIsogramFunction("Dermatoglyphics"));
    System.out.println(isIsogramFunction("aba"));
    System.out.println(isIsogramFunction("moOse"));

    IntStream s = IntStream.of(1, 2, 3, 4);
    long count = s.peek(System.out::println).count();
    System.out.println(count);
  }

  public static boolean isIsogram(String str) {
    // ...
    boolean status = true;
    String[] stringSplitter = str.toLowerCase().split("");
    Map<String, Integer> stringSplittSet = new HashMap<>();

    for (String string : stringSplitter) {
      Integer value = 1;
      if (stringSplittSet.containsKey(string)) {
        stringSplittSet.put(string, value + 1);
      } else {
        stringSplittSet.put(string, value);
      }
    }

    for (Integer integer : stringSplittSet.values()) {
      if (integer > 1) {
        status = false;
        break;
      } else {
        status = true;
      }
    }
    return status;
  }

  public static boolean isIsogramFunction(String str) {
    boolean status = true;
    String[] stringSplitter = str.toLowerCase().split("");
    int StringLengthDistinct = Arrays
      .stream(stringSplitter)
      .distinct()
      .collect(Collectors.toList())
      .size();
    if (str.length() == StringLengthDistinct) {
      status = true;
    } else {
      status = false;
    }
    return status;
  }

  public static boolean isIsogramFunctionShorter(String str) {
    return str.length() == str.toLowerCase().chars().distinct().count();
  }

  public static boolean isIsogramSet(String str) {
    if (str.equals("")) return true;
    String[] ary = str.toLowerCase().split("");
    Set<String> mySet = new HashSet<>(Arrays.asList(ary));
    return (str.length() == mySet.size());
  }
}
