import java.util.Scanner;

// https://alvinalexander.com/blog/post/java/java-how-case-insensitive-search-string-matches-method/
/**
 * Demonstrates how to perform a case-insensitive pattern
 * match using String and the String.matches() method.
 */
public class StringMatchesCaseInsensitive {

  public static void main(String[] args) {
    String stringToSearch = "Four score and seven years ago our fathers ...";

    // this won't work because the pattern is in upper-case
    System.out.println("Try 1: " + stringToSearch.matches(".*SEVEN.*"));

    // the magic (?i:X) syntax makes this search case-insensitive, so it returns true
    System.out.println("Try 2: " + stringToSearch.matches("(?i:.*SEVEN.*)"));

    System.out.println(neuesspiel());
  }

  private static boolean neuesspiel() {
    Scanner eingabe = new Scanner(System.in);
    System.out.println("Möchtest du nochmal spielen? [Y/N]");
    String userInput = eingabe.next();
    if (userInput.matches("(?i:Y)")) return true;
    if (userInput.matches("(?i:N)")) return false;
    return neuesspiel();
  }
}
