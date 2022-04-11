package kata._4kyu.burrowsheelertransformation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class _BWT {

  public static BWT encode(String s) {
    Integer[] suffix = IntStream
      .range(0, s.length())
      .boxed()
      .toArray(Integer[]::new);

    Arrays.sort(
      suffix,
      (o1, o2) -> {
        int compVal = 0;
        for (int i = 0; compVal == 0 && i < s.length(); i++) {
          compVal = Character.compare(s.charAt(o1), s.charAt(o2));
          o1 = (o1 + 1) % s.length();
          o2 = (o2 + 1) % s.length();
        }
        return compVal;
      }
    );

    int n = 0;
    char[] encoded = new char[s.length()];
    for (int i = 0; i < s.length(); i++) {
      int ix = suffix[i];
      if (ix == 0) {
        n = i;
        ix = s.length();
      }
      encoded[i] = s.charAt(ix - 1);
    }

    return new BWT(new String(encoded), n);
  }

  public static String decode(String s, int n) {
    String sorted = new String(s.chars().sorted().toArray(), 0, s.length());

    Integer[] suffix = IntStream
      .range(0, s.length())
      .boxed()
      .toArray(Integer[]::new);

    Arrays.sort(suffix, Comparator.comparingInt(s::charAt));

    char[] decoded = new char[s.length()];
    for (int i = 0, x = n; i < decoded.length; i++) {
      decoded[i] = sorted.charAt(x);
      x = suffix[x];
    }

    return new String(decoded);
  }

  public static void main(String[] args) {
    System.out.println("Encode");
    System.out.println(
      new BWT("nnbbraaaa", 4).equals(_BWT.encode("bananabar"))
    );
    // System.out.println(
    //   new BWT("e emnllbduuHB", 2).equals(MekiAnjo.encode("Humble Bundle"))
    // );
    // System.out.println(
    //   new BWT("ww MYeelllloo", 1).equals(MekiAnjo.encode("Mellow Yellow"))
    // );
    System.out.println();

    System.out.println("Decode");
    // System.out.println(
    //   "bananabrbanrrabarr".equals(MekiAnjo.decode("rnnbbbrraaaaarrbna", 6))
    // );
    System.out.println("bananabar".equals(_BWT.decode("nnbbraaaa", 4)));
    // System.out.println(
    //   "Humble Bundle".equals(MekiAnjo.decode("e emnllbduuHB", 2))
    // );
    // System.out.println(
    //   "Mellow Yellow".equals(MekiAnjo.decode("ww MYeelllloo", 1))
    // );
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
