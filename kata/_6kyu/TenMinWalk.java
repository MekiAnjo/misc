package kata._6kyu;

import java.awt.Point;

/**
 * TenMinWalk
 * https://www.codewars.com/kata/54da539698b8a2ad76000228/solutions/java
 * 
 * Description:
You live in the city of Cartesia where all roads are laid out in a perfect grid. You arrived ten minutes too early to an appointment, so you decided to take the opportunity to go for a short walk. The city provides its citizens with a Walk Generating App on their phones -- everytime you press the button it sends you an array of one-letter strings representing directions to walk (eg. ['n', 's', 'w', 'e']). You always walk only a single block for each letter (direction) and you know it takes you one minute to traverse one city block, so create a function that will return true if the walk the app gives you will take you exactly ten minutes (you don't want to be early or late!) and will, of course, return you to your starting point. Return false otherwise.

Note: you will always receive a valid array containing a random assortment of direction letters ('n', 's', 'e', or 'w' only). It will never give you an empty array (that's not a walk, that's standing still!).

 * 
 */
public class TenMinWalk {

  public static void main(String[] args) {
    System.out.println(
      isValidFunction(
        new char[] { 'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's' }
      )
    );
    System.out.println(
      isValidFunction(
        new char[] {
          'w',
          'e',
          'w',
          'e',
          'w',
          'e',
          'w',
          'e',
          'w',
          'e',
          'w',
          'e',
        }
      )
    );
    System.out.println(isValidFunction(new char[] { 'w' }));
    System.out.println(
      isValidFunction(
        new char[] { 'n', 'n', 'n', 's', 'n', 's', 'n', 's', 'n', 's' }
      )
    );
    System.out.println(
      isValidFunction2(
        new char[] { 'n', 's', 'n', 's', 'n', 's', 'n', 's', 'n', 's' }
      )
    );
    System.out.println(
      isValidFunction2(
        new char[] {
          'w',
          'e',
          'w',
          'e',
          'w',
          'e',
          'w',
          'e',
          'w',
          'e',
          'w',
          'e',
        }
      )
    );
    System.out.println(isValidFunction2(new char[] { 'w' }));
    System.out.println(
      isValidFunction2(
        new char[] { 'n', 'n', 'n', 's', 'n', 's', 'n', 's', 'n', 's' }
      )
    );
  }

  public static boolean isValid(char[] walk) {
    if (walk.length != 10) return false;

    int x = 0;
    int y = 0;

    for (char c : walk) {
      switch (c) {
        case 'n':
          y++;
          break;
        case 's':
          y--;
          break;
        case 'w':
          x--;
          break;
        case 'e':
          x++;
          break;
      }
    }
    return x == 0 && y == 0;
  }

  public static boolean isValid2(char[] walk) {
    if (walk.length != 10) {
      return false;
    }
    char north = 'n';
    char west = 'w';
    int sum = 0;

    for (char p : walk) {
      if (p == north || p == west) {
        sum += 1;
      } else {
        sum -= 1;
      }
    }
    return sum == 0;
  }

  public static boolean isValid3(char[] walk) {
    Point point = new Point(0, 0);
    for (char c : walk) {
      switch (c) {
        case 'n':
          point.translate(1, 0);
          break;
        case 'e':
          point.translate(0, 1);
          break;
        case 's':
          point.translate(-1, 0);
          break;
        case 'w':
          point.translate(0, -1);
          break;
      }
    }
    return point.equals(new Point(0, 0)) && walk.length == 10;
  }

  public static boolean isValidFunction(char[] walk) {
    String s = new String(walk);
    return (
      s.chars().filter(p -> p == 'n').count() ==
      s.chars().filter(p -> p == 's').count() &&
      s.chars().filter(p -> p == 'e').count() ==
      s.chars().filter(p -> p == 'w').count() &&
      s.chars().count() == 10
    );
  }

  public static boolean isValidFunction2(char[] walk) {
    String s = new String(walk);
    return (
      s.chars().filter(p -> p == 'n' || p == 'w').count() ==
      s.chars().filter(p -> p == 's' || p == 'e').count() &&
      s.chars().count() == 10
    );
  }
}
