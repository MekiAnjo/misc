package kata._6kyu;

import java.util.ArrayList;
import java.util.List;

/**
 * StringSplit
 */

/* 
  https://www.codewars.com/kata/515de9ae9dcfc28eb6000001/train/java

  Complete the solution so that it splits the string into pairs of two characters. If the string contains an odd number of characters then it should replace the missing second character of the final pair with an underscore ('_').

  Examples:

  StringSplit.solution("abc") // should return {"ab", "c_"}
  StringSplit.solution("abcdef") // should return {"ab", "cd", "ef"}
*/
public class StringSplit {

  private static String[] solution1;
  private static String[] solution2;

  public static void main(String[] args) {
    System.out.println("String Solutions");
    solution1 = solution("abc");
    solution2 = solution("abcdef");

    for (String string : solution1) {
      System.out.println(string);
    }
    System.out.println();
    for (String string : solution2) {
      System.out.println(string);
    }
  }

  public static String[] solution(String s) {
    // ! does not work
    String[] solution = new String[s.length()];
    StringBuilder sb = new StringBuilder();
    boolean istUngerade = s.length() % 2 != 0;

    solution = s.split("\\w{2}");
    // if (istUngerade) {
    //   solution[s.length()/2-1] = "_";
    // }

    return solution;
  }

  public static String[] solution2(String s) {
    s = (s.length() % 2 == 0) ? s : s + "_";
    return s.split("(?<=\\G.{2})");
  }

  static String[] solution3(String s) {
    return (s + (s.length() % 2 > 0 ? "_" : "")).split("(?<=\\G.{2})");
  }

  /* Regular expression here is interpreted as follows:

  (?<= is a Positive lookbehind.
  It matches a group before the main expression without including it in the result.

  \G
  Previous match end.
  Matches the end position of the previous match.

  . Dot
  Matches any character except line breaks.

  {2} is a Quantifier.
  Matches 2 of the preceding token. 
  
  */

  public static String[] solution4(String s) {
    if (s.length() % 2 == 1) s += "_";
    int n = s.length() / 2;

    String[] sub = new String[n];
    for (int i = 0; i < n; ++i) sub[i] =
      "" + s.charAt(i * 2) + s.charAt(1 + i * 2);

    return sub;
  }

  public static String[] solution5(String s) {
    List<String> result = new ArrayList<>();
    if (s.length() % 2 == 1) {
      s = s + "_";
    }
    for (int i = 0; i < s.length() - 1; i += 2) {
      result.add(s.substring(i, i + 2));
    }
    return result.toArray(new String[0]);
  }
}
