package kata._4kyu.burrowsheelertransformation;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MekiAnjo {

  public static BWT encode(String s) {
    Integer[] indices = IntStream
      .range(0, s.length())
      .boxed()
      .toArray(Integer[]::new);

    System.out.printf("Indices begin:      %s%n", Arrays.toString(indices));
    String input = Arrays
      .stream(indices)
      .map(s::charAt)
      .map(Object::toString)
      .collect(Collectors.joining(", ", "Indices mapped to s [", "]"));
    System.out.println(input);

    Arrays.sort(
      indices,
      (o1, o2) -> {
        int x1 = o1;
        int x2 = o2;

        System.out.println();
        System.out.println("-".repeat(50));
        int compVal = 0;
        for (int i = 0; compVal == 0 && i < s.length(); i++) {
          System.out.printf("Indices before: %s%n", Arrays.toString(indices));
          System.out.printf(
            "Überprüfe x1 = %d(%s) und x2 = %d(%s)%n",
            x1,
            s.charAt(x1),
            x2,
            s.charAt(x2)
          );
          compVal = Character.compare(s.charAt(x1), s.charAt(x2));
          System.out.printf(
            "compVal = %d => %s%n",
            compVal,
            compVal > 0 || compVal < 0 ? "tauschen" : "nicht tauschen"
          );
          System.out.printf("Indices after:  %s%n", Arrays.toString(indices));

          x1 = (x1 + 1) % s.length();
          x2 = (x2 + 1) % s.length();
        }
        System.out.println("-".repeat(50));

        return compVal;
      }
    );

    System.out.printf("%nIndices after sort: %s%n", Arrays.toString(indices));
    String output = Arrays
      .stream(indices)
      .map(s::charAt)
      .map(Object::toString)
      .collect(Collectors.joining(", ", "Indices mapped to s [", "]"));
    System.out.println(output);

    int n = 0;
    char[] encoded = new char[s.length()];
    for (int i = 0; i < s.length(); i++) {
      int ix = indices[i];
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

    Integer[] indices = IntStream
      .range(0, s.length())
      .boxed()
      .toArray(Integer[]::new);

    Arrays.sort(indices, Comparator.comparingInt(s::charAt));

    char[] decoded = new char[s.length()];
    for (int i = 0, x = n; i < decoded.length; i++) {
      decoded[i] = sorted.charAt(x);
      x = indices[x];
    }

    return new String(decoded);
  }

  public static void main(String[] args) {
    System.out.println("Encode");
    System.out.println(
      new BWT("nnbbraaaa", 4).equals(MekiAnjo.encode("bananabar"))
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
    System.out.println("bananabar".equals(MekiAnjo.decode("nnbbraaaa", 4)));
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
