public class JavaQuine {

  public static void main(String[] args) {
    String text = "public class JavaQuine {" +
      "  public static void main(String[] args) {" +
      "    String text = %s;" +
      "    System.out.printf(text, (char) 34 + text + (char) 34);" +
      "  }" +
      "}";
    System.out.printf(text, (char) 34 + text + (char) 34);
  }
}
