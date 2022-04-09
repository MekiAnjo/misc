package kata._4kyu.burrowsheelertransformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://www.codetd.com/en/article/12512964

public class BurrowsWheelerTransformation {

  private static StringBuilder builder = new StringBuilder();

  /*
    Because the longer the text, it is impossible to enumerate all the strings, so

    1.  First obtain all the shifted head characters and the string index corresponding to the head character to form a head character set

    2.  Sort according to the set of head characters, string sorting is to compare and sort each character according to the ASCII code table

    3.  After the first character set is sorted, take the index of the string corresponding to the first character in the set in order, then obtain the last character after shifting, and record the index of the original array.
  */

  public static BWT encode(String s) {
    builder.setLength(0);
    int len = s.length(), index = 0;
    char[] chs = s.toCharArray();
    boolean[] map = new boolean[256];
    Map<Character, List<Integer>> indexMap = new HashMap<>();
    for (int i = 0; i < len; map[chs[i]] = true, i++) { // 先 hash 排序一次, 并记录索引
      List<Integer> list = indexMap.getOrDefault(chs[i], new ArrayList<>());
      list.add(i);
      indexMap.put(chs[i], list);
    }
    for (int i = 0; i < 256; i++) {
      if (!map[i]) continue;
      List<Integer> list = indexMap.get((char) i);

      list.sort((o1, o2) -> {
        int n = 1;
        for (; n < len && chs[(o1 + n) % len] == chs[(o2 + n) % len]; n++);
        return chs[(o1 + n) % len] > chs[(o2 + n) % len] ? 1 : -1;
      });

      for (int l : list) if (l == 0) { // 拼最后一个, 并记录原始位置
        index = builder.length();
        builder.append(chs[len - 1]);
      } else builder.append(chs[l - 1]); // 拼前一个
    }
    return new BWT(builder.toString(), len == 0 ? -1 : index);
  }

  /*
    1.  Sort the input strings first to get the first row of strings. The first row of strings are considered to be the characters behind the last row.

    2.  According to the last row and the first row of strings, classify and rank.

    3.  Because all strings are sorted according to encoding, then starting from the original index, find the ranking of the first row,
        according to the ranking (the same character, the last row and the first row are ranked in the same order)
        Find the same rank in the last row and get the index.

    4.  Finally, arrange the strings from beginning to end.  
  */

  public static String decode(String s, int n) {
    if (n < 0) return "";
    builder.setLength(0);
    int len = s.length();
    char[] preArr = s.toCharArray(), nxtArr = s.toCharArray();
    Arrays.sort(nxtArr);
    Map<Character, List<Integer>> preMap = new HashMap<>(), nxtMap = new HashMap<>();
    for (int i = 0; i < len; i++) { // 按字符, 将前缀和后继索引存放
      List<Integer> prelist = preMap.getOrDefault(
        preArr[i],
        new ArrayList<>()
      ), nxtlist = nxtMap.getOrDefault(nxtArr[i], new ArrayList<>());
      prelist.add(i);
      nxtlist.add(i);
      preMap.put(preArr[i], prelist);
      nxtMap.put(nxtArr[i], nxtlist);
    }
    while (len-- > 0) {
      char temp = nxtArr[n];
      builder.append(temp); // 元素
      n = nxtMap.get(temp).indexOf(n); // 排名
      n = preMap.get(temp).get(n); // 索引位置
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println("Encode");
    System.out.println(
      new BWT("nnbbraaaa", 4)
        .equals(BurrowsWheelerTransformation.encode("bananabar"))
    );
    System.out.println(
      new BWT("e emnllbduuHB", 2)
        .equals(BurrowsWheelerTransformation.encode("Humble Bundle"))
    );
    System.out.println(
      new BWT("ww MYeelllloo", 1)
        .equals(BurrowsWheelerTransformation.encode("Mellow Yellow"))
    );
    System.out.println();

    System.out.println("Decode");
    System.out.println(
      "bananabrbanrrabarr".equals(
          BurrowsWheelerTransformation.decode("rnnbbbrraaaaarrbna", 6)
        )
    );
    System.out.println(
      "bananabar".equals(BurrowsWheelerTransformation.decode("nnbbraaaa", 4))
    );
    System.out.println(
      "Humble Bundle".equals(
          BurrowsWheelerTransformation.decode("e emnllbduuHB", 2)
        )
    );
    System.out.println(
      "Mellow Yellow".equals(
          BurrowsWheelerTransformation.decode("ww MYeelllloo", 1)
        )
    );
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
