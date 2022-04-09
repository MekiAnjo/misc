package kata._4kyu.burrowsheelertransformation;

import java.util.Arrays;

public class Test {

  public static BWT encode(String s) {
    String[] data = new String[s.length()];

    for (int i = 0; i < data.length; i++) {
      data[i] = s.substring(i) + s.substring(0, i);
    }

    Arrays.sort(data);

    int rowOfString = 0;
    for (int i = 0; i < data.length; i++) {
      if (data[i].equals(s)) {
        rowOfString = i;
        break;
      }
    }

    StringBuilder result = new StringBuilder(data.length);
    for (String datum : data) {
      result.append(datum.charAt(data.length - 1));
    }

    return new BWT(result.toString(), s.length() == 0 ? -1 : rowOfString);
  }

  public static String decode(String s, int n) {
    String sorted = new String(s.chars().sorted().toArray(), 0, s.length());
    StringBuilder result = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      int which = 0;
      for (int j = 0; j <= n; j++) {
        if (sorted.charAt(j) == sorted.charAt(n)) {
          which++;
        }
      }

      for (int j = 0; j < s.length(); j++) {
        if (s.charAt(j) == sorted.charAt(n)) {
          which--;
        }
        if (which == 0) {
          result.append(sorted.charAt(n));
          n = j;
          break;
        }
      }
    }
    return result.toString();
  }

  static class BWT {

    String res;
    int index;

    BWT(String res, int index) {
      this.res = res;
      this.index = index;
    }

    public boolean equals(BWT b) {
      System.out.println(b);
      return this.res.equals(b.res) && this.index == b.index;
    }

    public String toString() {
      return "obj :" + res + ":" + index;
    }
  }
}
