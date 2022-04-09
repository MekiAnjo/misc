package kata._4kyu.burrowsheelertransformation;

import java.util.Arrays;
import java.util.Comparator;

// https://www.codewars.com/kata/reviews/5c63eb15eeaf750001ea1199/groups/5dda619c9fe71d000166b5c4

public class Pdobszai {

  public static BWT encode(String s) {
    if (s == null || s.isEmpty()) {
      return new BWT(s, -1);
    }

    Integer[] suffixes = new Integer[s.length()];
    for (int i = 0; i < suffixes.length; i++) {
      suffixes[i] = i;
    }
    // before sorting:
    // suffixes[0] -> original text
    // suffixes[1] -> shifted once -> s[length-1] + s[1-]
    // suffixes[2] -> shifted twice -> s[length-2-] + s[2-]
    // ...
    Arrays.sort(
      suffixes,
      (i1, i2) -> {
        //starting points
        int x1 = i1.intValue();
        int x2 = i2.intValue();

        int diff = 0;
        for (int i = 0; diff == 0 && i < s.length(); i++) {
          diff = Character.compare(s.charAt(x1), s.charAt(x2));
          x1 = x1 + 1 < s.length() ? x1 + 1 : 0;
          x2 = x2 + 1 < s.length() ? x2 + 1 : 0;
        }

        return diff;
      }
    );

    int n = 0;
    char[] encoded = new char[s.length()];
    for (int i = 0; i < s.length(); i++) {
      int ix = suffixes[i];
      if (ix == 0) {
        n = i;
        ix = s.length();
      }
      encoded[i] = s.charAt(ix - 1);
    }

    return new BWT(new String(encoded), n);
  }

  public static String decode(String s, int n) {
    if (s == null || s.isEmpty() || n < 0) {
      return s;
    }
    //first column is the sorted version of the encoded string
    char[] firstCol = s.toCharArray();
    Arrays.sort(firstCol);

    Integer[] next = new Integer[s.length()];
    for (int i = 0; i < next.length; i++) {
      next[i] = i;
    }

    Arrays.sort(
      next,
      (i1, i2) -> Character.compare(s.charAt(i1), s.charAt(i2))
    );

    int x = n;
    char[] decoded = new char[firstCol.length];
    for (int i = 0; i < firstCol.length; i++) {
      decoded[i] = firstCol[x];
      x = next[x];
    }

    return new String(decoded);
  }
}

class BWT {

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
