package kata._6kyu;

import java.util.stream.Collectors;

/**
 * BreakCamelCase
 * 
 * https://www.codewars.com/kata/5208f99aee097e6552000148/train/java
 * 
 * https://www.codewars.com/kata/5208f99aee097e6552000148/solutions/java
 * 
 * Complete the solution so that the function will break up camel casing, using a space between words.

    Example
    "camelCasing"  =>  "camel Casing"
    "identifier"   =>  "identifier"
    ""             =>  ""


    credit regex : https://stackoverflow.com/questions/7593969/regex-to-split-camelcase-or-titlecase-advanced
    (?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])
    with digit handling
    (?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])|(?<=[0-9])(?=[A-Z][a-z])|(?<=[a-zA-Z])(?=[0-9])
 */
public class BreakCamelCase {

  public static void main(String[] args) {
    System.out.println(camelCase("camelCasing"));
    System.out.println(camelCase("identifier"));
    System.out.println(camelCase(""));
    System.out.println(camelCase("eclipseRCPExt"));
    System.out.println(camelCase("eclip12seRCPExt"));
  }

  public static String camelCase(String input) {
    if (input.isEmpty()) return input;
    StringBuilder sb = new StringBuilder();
    String[] splitted = input.split(
      "(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])|(?<=[0-9])(?=[A-Z][a-z])|(?<=[a-zA-Z])(?=[0-9])"
    );

    for (String string : splitted) {
      sb.append(string);
      sb.append(" ");
    }
    return sb.toString().trim();
  }

  public static String camelCase2(String input) { // ! very slow solution
    return input.replaceAll("([A-Z])", " $1").trim();
  }

  public static String camelCase3(String input) {
    StringBuilder sb = new StringBuilder();
    for (char c : input.toCharArray()) {
      if (c >= 'A' && c <= 'Z') {
        sb.append(' ');
      }
      sb.append(c);
    }
    return sb.toString();
  }

  public static String camelCase4(String input) { // ! slow solution, could be improved with using StringBuilder
    String output = "";
    for (int i = 0; i < input.length(); i++) {
      output =
        Character.isUpperCase(input.charAt(i))
          ? output + " " + input.charAt(i)
          : output + input.charAt(i);
    }
    return output;
  }

  public static String camelCase5(String input) { // ! [IMPROVED]
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < input.length(); i++) {
      output =
        Character.isUpperCase(input.charAt(i))
          ? output.append(" ").append(input.charAt(i))
          : output.append(input.charAt(i));
    }
    return output.toString();
  }

  public static String camelCase6(String input) {
    return String.join(" ", input.split("(?=[A-Z])"));
  }

  public static String camelCase7(String input) {
    return input
      .chars()
      .mapToObj(
        ch ->
          Character.isUpperCase(ch)
            ? " " + String.valueOf((char) ch)
            : String.valueOf((char) ch)
      )
      .collect(Collectors.joining());
  }
}
