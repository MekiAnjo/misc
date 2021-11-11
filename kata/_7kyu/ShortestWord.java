package kata._7kyu;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * ShortestWord
 * 
 * https://www.codewars.com/kata/57cebe1dc6fdc20c57000ac9/train/java
 * 
 * Simple, given a string of words, return the length of the shortest word(s).

  String will never be empty and you do not need to account for different data types.
 */
public class ShortestWord {

  public static void main(String[] args) {
    System.out.println(
      findShort("bitcoin take over the world maybe who knows perhaps")
    );
  }

  public static int findShort(String s) {
    String[] splitted = s.split(" ");

    var min = Integer.MAX_VALUE;
    for (String string : splitted) {
      if (string.length() < min) {
        min = string.length();
      }
    }
    return min;
  }

  public static int findShort2(String s) { // * "\s+" takes care of any number of spaces in between, also not the fastest
    return Stream.of(s.split("\s+")).mapToInt(String::length).min().getAsInt();
  }
  public static int findShort3(String s) { // ? Probably fastest solution
    return Pattern.compile("\s+").splitAsStream(s).mapToInt(String::length).min().getAsInt();
  }

  public static int findShort4(String s) {
    String[] words = s.split(" ");
    Stream<String> stream = Arrays.stream(words);
    Arrays.fill(words, null);
    return stream.mapToInt(String::length)
                 .min()
                 .getAsInt();
  }
}
