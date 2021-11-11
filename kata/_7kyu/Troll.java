package kata._7kyu;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Troll
 * 
 * https://www.codewars.com/kata/52fba66badcd10859f00097e/train/java
 * 
 * Trolls are attacking your comment section!

    A common way to deal with this situation is to remove all of the vowels from the trolls' comments, neutralizing the threat.

    Your task is to write a function that takes a string and return a new string with all vowels removed.

    For example, the string "This website is for losers LOL!" would become "Ths wbst s fr lsrs LL!".

    Note: for this kata y isn't considered a vowel.
 */
public class Troll {

  public static void main(String[] args) {
    System.out.println(disemvowel("This website is for losers LOL!"));
  }

  public static String disemvowel(String str) {
    return str.replaceAll("(?i:[aeiou])", "");
  }

  public static String disemvowel2(String str) {
    //This is setting up something to add to and return.
    String output = "";

    //This iterates (steps through) the string.
    for (int i = 0; i < str.length(); i++) {
      //! means not in Java, so it checks if our current letter is not a vowel, or is a consonant.
      if (
        str.charAt(i) != 'a' &&
        str.charAt(i) != 'e' &&
        str.charAt(i) != 'i' &&
        str.charAt(i) != 'o' &&
        str.charAt(i) != 'u' &&
        str.charAt(i) != 'A' &&
        str.charAt(i) != 'E' &&
        str.charAt(i) != 'I' &&
        str.charAt(i) != 'O' &&
        str.charAt(i) != 'U'
      ) {
        //Here, we add the consonant to the string we will output.
        output = output + str.charAt(i);
      }
    }

    //We give the output back after running through the program (The original sentence, just without vowels)
    return output;
    //Hope you found this explanation helpful if you're new to programming! I know it's not the best solution, but it's easy enough to understand. :P
  }

  public static String disemvowe3(String str) {
    String output = "";
    for (int i = 0; i < str.length(); i++) {
      switch (str.toLowerCase().charAt(i)) {
        case 'a':
          output += "";
          break;
        case 'e':
          output += "";
          break;
        case 'i':
          output += "";
          break;
        case 'o':
          output += "";
          break;
        case 'u':
          output += "";
          break;
        default:
          output += str.charAt(i);
      }
    }
    return output;
  }
}
