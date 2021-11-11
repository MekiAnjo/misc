package kata._6kyu;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * String2CamelCase
 * 
 * https://www.codewars.com/kata/517abf86da9663f1d2000003/train/java
 * 
 * Complete the method/function so that it converts dash/underscore delimited words into camel casing. The first word within the output should be capitalized only if the original word was capitalized (known as Upper Camel Case, also often referred to as Pascal case).

    Examples
    "the-stealth-warrior" gets converted to "theStealthWarrior"
    "The_Stealth_Warrior" gets converted to "TheStealthWarrior"
 * 
 * 
 * 
 */
public class String2CamelCase {

  public static void main(String[] args) {
    System.out.println(toCamelCase("the-stealth-warrior"));
    System.out.println(toCamelCase("The_Stealth_Warrior"));
  }

  static String toCamelCase(String s) {
    if (s.equals(" ")) return ""; // wahrscheinlich unnötig
    StringBuilder builder = new StringBuilder();
    long sLenght = Stream
      .of(s)
      .filter(str -> str.equals("-") || str.equals("_"))
      .count();
    String[] splitted = new String[(int) sLenght];
    if (s.contains("-")) {
      splitted = s.split("-");
    } else if (s.contains("_")) {
      splitted = s.split("_");
    }
    for (int i = 1; i < splitted.length; i++) {
      splitted[i] =
        splitted[i].substring(0, 1).toUpperCase() +
        splitted[i].substring(1).toLowerCase();
    }
    for (int i = 0; i < splitted.length; i++) {
      builder.append(splitted[i]);
    }

    return builder.toString();
  }

  static String toCamelCase2(String s) { // ! inefficient, besides don't use StringBuffer in single thread tasks
    Matcher m = Pattern.compile("[_|-](\\w)").matcher(s);
    StringBuffer sb = new StringBuffer();
    while (m.find()) {
      m.appendReplacement(sb, m.group(1).toUpperCase());
    }
    return m.appendTail(sb).toString();
  }

  static String toCamelCase3(String str) { // ? probably best solution
    String[] words = str.split("[-_]");
    return Arrays
      .stream(words, 1, words.length)
      .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
      .reduce(words[0], String::concat);
  }

  static String toCamelCase4(String s) { // ? probably best solution
    String[] stringArray = s.split("[-|_]");

    return (
      stringArray[0] +
      Arrays
        .stream(stringArray)
        .skip(1)
        .map(i -> i.substring(0, 1).toUpperCase() + i.substring(1))
        .collect(Collectors.joining())
    );
  }

  static String toCamelCase5(String s) { // * pretty much my own solution but shorter
    String[] words = s.split("[_\\-]");
    s = words[0];
    for (int i = 1; i < words.length; i++) s +=
      words[i].substring(0, 1).toUpperCase() +
      words[i].substring(1).toLowerCase();
    return s;
  }
}
