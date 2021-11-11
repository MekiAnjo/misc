/**
 * SwitchExpressions
 */
public class SwitchExpressions {

  public static void main(String[] args) {
    DayOfWeek day = DayOfWeek.FRIDAY;

    System.out.println(standardSwitch(day));
    System.out.println(switchWithReturnAndMultipleValues(day));
    switchAssignments();
    switchBreakReturnValuePast();
    switchBreakReturnValue();
  }

  private static int standardSwitch(DayOfWeek day) {
    int numLetters = -1;

    switch (day) {
      case MONDAY:
      case FRIDAY:
      case SUNDAY:
        numLetters = 6;
        break;
      case TUESDAY:
        numLetters = 7;
        break;
      case THURSDAY:
      case SATURDAY:
        numLetters = 8;
        break;
      case WEDNESDAY:
        numLetters = 9;
        break;
    }
    return numLetters;
  }

  private static int switchWithReturnAndMultipleValues(DayOfWeek day) {
    return switch (day) {
      case MONDAY, FRIDAY, SUNDAY -> 6;
      case TUESDAY -> 7;
      case THURSDAY, SATURDAY -> 8;
      case WEDNESDAY -> 9;
    };
  }

  private static void switchAssignments() {
    final int value = 2;
    String numericString;

    switch (value) {
      case 1 -> numericString = "one";
      case 2 -> numericString = "two";
      case 3 -> numericString = "three";
      default -> numericString = "N/A";
    }

    System.out.println("value:" + value + " as String:" + numericString);
  }

  private static void switchBreakReturnValuePast() {
    Color color = Color.GREEN;
    int numOfChars;

    switch (color) {
      case RED:
        numOfChars = 3;
        break;
      case BLUE:
        numOfChars = 4;
        break;
      case GREEN:
        numOfChars = 5;/* break; UPS: FALL-THROUGH */
      case YELLOW:
        numOfChars = 6;
        break;
      case ORANGE:
        numOfChars = 6;
        break;
      default:
        numOfChars = -1;
        break;
    }
    System.out.println("OLD: " + numOfChars);
  }

  private static void switchBreakReturnValue() {
    Color color = Color.GREEN;

    int numOfChars =
      switch (color) {
        case RED:
          yield 3;
        case BLUE:
          yield 4;
        case GREEN:
          yield 5;
        case YELLOW, ORANGE:
          yield 6;
        default:
          yield -1;
      };
    System.out.println("color:" + color + " ==>" + numOfChars);
  }

  enum DayOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY,
  }

  enum Color {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    ORANGE,
  }
}
