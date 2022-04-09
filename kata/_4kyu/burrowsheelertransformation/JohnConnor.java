package kata._4kyu.burrowsheelertransformation;

import java.util.Arrays;
import java.util.Comparator;

// https://www.codewars.com/kata/reviews/5c63eb15eeaf750001ea1199/groups/5f6a7db7a3d52600013ebc48

public class JohnConnor {

  private final String s;
  private final int m;
  private final Integer[] perm;
  private final char[] output;

  public JohnConnor(String s, boolean encoder) {
    this.s = s;
    m = s.length();
    perm = new Integer[m];
    Arrays.setAll(perm, i -> i);
    Arrays.sort(perm, encoder ? permStrComp() : charComp());
    output = new char[m];
  }

  private char charAt(int permIndex, int chIndex) {
    int i = chIndex - permIndex;
    return s.charAt(i >= 0 ? i : i + m);
  }

  private Comparator<Integer> permStrComp() {
    return new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        int i1 = o1;
        int i2 = o2;
        for (int j = 0; j < m; j++) {
          int d = charAt(i1, j) - charAt(i2, j);
          if (d != 0) return d;
        }
        return 0;
      }
    };
  }

  private Comparator<Integer> charComp() {
    return new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return s.charAt(o1) - s.charAt(o2);
      }
    };
  }

  private void setOutChar(int outIndex, int permIndex, int chIndex) {
    output[outIndex] = charAt(permIndex, chIndex);
  }

  @Override
  public String toString() {
    return new String(output);
  }

  public static BWT encode(String s) {
    JohnConnor bw = new JohnConnor(s, true);
    int m = bw.m;
    int n = 0;
    m--;
    for (int i = 0; i <= m; i++) {
      int j = bw.perm[i];
      bw.setOutChar(i, j, m);
      if (j == 0) n = i;
    }
    return new BWT(bw.toString(), n);
  }

  public static String decode(String s, int n) {
    JohnConnor bw = new JohnConnor(s, false);
    int m = bw.m;
    int j = n;
    for (int i = 0; i < m; i++) {
      j = bw.perm[j];
      bw.setOutChar(i, 0, j);
    }
    return bw.toString();
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
