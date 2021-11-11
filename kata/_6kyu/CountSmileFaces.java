package kata._6kyu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * CountSmileFaces
 * 
 * https://www.codewars.com/kata/583203e6eb35d7980400002a/train/java
 * 
 * Given an array (arr) as an argument complete the function countSmileys that should return the total number of smiling faces.

  Rules for a smiling face:

  Each smiley face must contain a valid pair of eyes. Eyes can be marked as : or ;
  A smiley face can have a nose but it does not have to. Valid characters for a nose are - or ~
  Every smiling face must have a smiling mouth that should be marked with either ) or D
  No additional characters are allowed except for those mentioned.

  Valid smiley face examples: :) :D ;-D :~)
  Invalid smiley faces: ;( :> :} :]

  Example
  countSmileys([':)', ';(', ';}', ':-D']);       // should return 2;
  countSmileys([';D', ':-(', ':-)', ';~)']);     // should return 3;
  countSmileys([';]', ':[', ';*', ':$', ';-D']); // should return 1;
  Note
  In case of an empty array return 0. You will not be tested with invalid input (input will always be an array). Order of the face (eyes, nose, mouth) elements will always be the same.
 */
public class CountSmileFaces {

  public static void main(String[] args) {
    List<String> list = new ArrayList<>();
    list.add(":)");
    list.add(":D");
    list.add(":-}");
    list.add(":-()");

    System.out.println(countSmileys(list));
  }

  public static int countSmileys(List<String> arr) {
    if (arr.isEmpty()) return 0;
    int sumValidSmileys = 0;
    for (String string : arr) {
      List<String> list = Arrays.asList(string.split(""));
      if (list.size() == 3) {
        if (list.get(0).equals(":") || list.get(0).equals(";")) {
          if (list.get(1).equals("-") || list.get(1).equals("~")) {
            if (list.get(2).equals(")") || list.get(2).equals("D")) {
              sumValidSmileys++;
            } else continue;
          } else continue;
        } else continue;
      }
      if (list.size() == 2) {
        if (list.get(0).equals(":") || list.get(0).equals(";")) {
          if (list.get(1).equals(")") || list.get(1).equals("D")) {
            sumValidSmileys++;
          }
        }
      }
    }
    return sumValidSmileys;
  }

  public static int countSmileys2(List<String> arr) {
    return (int) arr.stream().filter(x -> x.matches("[:;][-~]?[)D]")).count();
  }

  public static int countSmileys3(List<String> arr) {
    return (int) arr.stream().filter(smileyCheck).count();
  }

  private static final Predicate<String> smileyCheck = str -> {
    if (str.length() != 2 && str.length() != 3) return false;
    if (!str.startsWith(":") && !str.startsWith(";")) return false;
    if (!str.endsWith(")") && !str.endsWith("D")) return false;
    if (
      str.length() != 2 && str.charAt(1) != '-' && str.charAt(1) != '~'
    ) return false;
    return true;
  };
}
