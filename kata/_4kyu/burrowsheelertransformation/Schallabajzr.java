package kata._4kyu.burrowsheelertransformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Schallabajzr {

  public static void main(String[] args) {
    BWT a = encode("");
    // decode(a.s, a.n);
  }

  public static BWT encode(String s) {
    List<String> circShift = generateCircShift(s);
    circShift.sort(String::compareTo);
    return new BWT(
      circShift
        .stream()
        .map(s1 -> s1.substring(s1.length() - 1))
        .collect(Collectors.joining()),
      circShift.indexOf(s)
    );
  }

  private static List<String> generateCircShift(String string) {
    ArrayList<String> result = new ArrayList<>();
    for (int i = 0; i < string.length(); i++) {
      String leftRotate = leftRotate(string, 1);
      result.add(leftRotate);
      string = leftRotate;
    }
    return result;
  }

  private static String leftRotate(String str, int d) {
    return str.substring(d) + str.substring(0, d);
  }

  public static String decode(String s, int n) {
    if (n == -1) return "";
    if (n > s.length() - 1) {
      return "";
    }

    ArrayList<String> strings = new ArrayList<>();
    //init first column
    String[] split = s.split("");
    Collections.addAll(strings, split);
    strings.sort(String::compareTo);

    //append the rest of the columns
    for (int i = 0; i < s.length() - 1; i++) {
      for (int j = 0; j < strings.size(); j++) {
        strings.set(j, s.charAt(j) + strings.get(j));
      }
      strings.sort(String::compareTo);
    }
    return strings.get(n);
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
