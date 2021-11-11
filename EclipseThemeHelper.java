/**
 * EclipseThemeHelper
 */
public class EclipseThemeHelper {

  private static final String CONSTANT = "String";
  private Object o;
  private static String name = "Eclipse";

  /**
   * Creates a new demo.
   * @param o The object to demonstrate.
   */

  public EclipseThemeHelper(Object o) {
    this.o = o;
    String s = CONSTANT;
    int i = 1;
    System.out.println();
    i = (int) Math.PI;
  }

  public static void main(String[] args) {
    EclipseThemeHelper themeHelper = new EclipseThemeHelper(new Object());
    helper(CONSTANT, themeHelper);
  }

  public static void helper(String s, Object o) {}

  enum Color {
    RED,
  }
}
