package kata._7kyu;

/**
 * XO
 * 
 * https://www.codewars.com/kata/55908aad6620c066bc00002a/train/java
 * 
 * Check to see if a string has the same amount of 'x's and 'o's. The method must return a boolean and be case insensitive. The string can contain any char.

    Examples input/output:

    XO("ooxx") => true
    XO("xooxx") => false
    XO("ooxXm") => true
    XO("zpzpzpp") => true // when no 'x' and 'o' is present should return true
    XO("zzoo") => false
    XO("Xxxxertr34") => false
 */
public class XO {

  public static void main(String[] args) {
    System.out.println(getXO4("ooxx"));
    System.out.println(getXO4("xooxx"));
    System.out.println(getXO4("ooxXm"));
    System.out.println(getXO4("zpzpzpp"));
    System.out.println(getXO4("zzoo"));
    System.out.println(getXO4("Xxxxertr34"));
  }

  public static boolean getXO(String str) {
    str = str.toLowerCase();
    // if (!(temp.contains("x") && temp.contains("o"))) {
    //   return true;
    // }
    int x = 0;
    int o = 0;
    for (char s : str.toCharArray()) {
      switch (s) {
        case 'x':
          x++;
          break;
        case 'o':
          o++;
          break;
        default:
          break;
      }
    }
    return x == o;
  }

  public static boolean getXO2(String str) {
    // if (!str.contains("x") && str.contains("o")) {
    //   return false;
    // }
    // if (str.contains("x") && !str.contains("o")) {
    //   return false;
    // }
    // if (!(str.contains("x") && str.contains("o"))) {
    //   return true;
    // }
    // ! the above is not necessary
    return (
      str.toLowerCase().chars().filter(s -> s == 'x').count() ==
      str.toLowerCase().chars().filter(s -> s == 'o').count()
    );
  }

  public static boolean getXO3(String str) { // inefficient because it creates a temporary String object with replace(), more so with toLowerCase()
    // str = str.toLowerCase(); // nicht benötigt da replace case insensitive ist
    return str.replace("o", "").length() == str.replace("x", "").length();
  }

  public static boolean getXO4(String str) { // almost the most efficient I guess
    int x = 0;
    int o = 0;
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (c == 'x' || c == 'X') {
        x++;
      } else if (c == 'o' || c == 'O') {
        o++;
      }
    }
    return x == o;
  }
}
