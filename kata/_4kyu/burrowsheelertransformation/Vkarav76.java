package kata._4kyu.burrowsheelertransformation;

import java.util.Arrays;

/**
 * Vkarav76
 * https://www.codewars.com/kata/reviews/5c63eb15eeaf750001ea1199/groups/5de3ef3114c71200010c9ed8
 */
public class Vkarav76 {

  public static BWT encode(String s) {
    if (s.isEmpty()) return new BWT("", 0);
    int ln = s.length();
    CharIndex[] charS = new CharIndex[ln];
    for (int i = 0; i < s.length(); i++) charS[i] =
      new CharIndex(s.charAt(i), i);
    Arrays.sort(
      charS,
      (t1, t2) -> {
        int i1 = t1.getIndex(), i2 = t2.getIndex();
        for (int k = 0; k < ln && s.charAt(i1) == s.charAt(i2); k++) {
          i1 = i1 == ln - 1 ? 0 : i1 + 1;
          i2 = i2 == ln - 1 ? 0 : i2 + 1;
        }
        return Character.compare(s.charAt(i1), s.charAt(i2));
      }
    );
    StringBuilder encodeS = new StringBuilder();
    int idx, start = 0;
    for (int i = 0; i < ln; i++) {
      idx = charS[i].getIndex();
      idx = idx == 0 ? ln - 1 : idx - 1;
      encodeS.append(s.charAt(idx));
      if (charS[i].getIndex() == 0) start = i;
    }
    return new BWT(encodeS.toString(), start);
  }

  public static String decode(String s, int n) {
    if (s.isEmpty()) return "";
    int ln = s.length();
    CharIndex[] charS = new CharIndex[ln];
    for (int i = 0; i < s.length(); i++) charS[i] =
      new CharIndex(s.charAt(i), i);
    Arrays.sort(
      charS,
      (t1, t2) -> {
        return Character.compare(t1.getCh(), t2.getCh());
      }
    );
    StringBuilder decodeS = new StringBuilder();
    decodeS.append(s.charAt(n));
    for (int i = 1; i < ln; i++) {
      int k;
      for (k = 0; k < ln && charS[k].getIndex() != n; k++);
      n = k;
      decodeS.append(s.charAt(n));
    }
    return decodeS.reverse().toString();
  }
}

class CharIndex {

  private char ch;
  int index;

  CharIndex(char ch, int index) {
    this.ch = ch;
    this.index = index;
  }

  public char getCh() {
    return ch;
  }

  public int getIndex() {
    return index;
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
