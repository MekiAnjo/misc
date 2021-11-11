package kata._6kyu;

import static java.util.Arrays.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * FindTheOddInt
 */
public class FindOdd {

  //  Given an array of integers, find the one that appears an odd number of times.
  //  There will always be only one integer that appears an odd number of times.
  public static void main(String[] args) {
    var test = new int[] {
      20,
      1,
      -1,
      2,
      -2,
      3,
      3,
      5,
      5,
      1,
      2,
      4,
      20,
      4,
      -1,
      -2,
      5,
    };

    System.out.println(findIt(test));
    System.out.println(findItFunction(test));
  }

  public static int findIt(int[] a) {
    Map<Integer, Integer> myMap = new HashMap<>();
    for (int i : a) {
      var value = 1;
      if (myMap.containsKey(i)) {
        myMap.put(i, myMap.get(i) + 1);
      } else {
        myMap.put(i, value);
      }
    }
    for (Map.Entry<Integer, Integer> i : myMap.entrySet()) {
      int key = i.getKey();
      int value = i.getValue();

      if (value % 2 != 0) {
        return key;
      }
    }
    return -1;
  }

  public static int findItFunction(int[] a) {
    // Array in eine Liste umwandeln um Collections.frequency anwenden zu können
    List<Integer> intList = Arrays
      .stream(a)
      .boxed() // boxed() wandelt primitiven Datentypen in sein Objekt um (int -> Integer)
      .collect(Collectors.toList());
    for (int i : a) {
      // findet die erste Zahl mit ungerader Häufigkeit
      if (Collections.frequency(intList, i) % 2 != 0) {
        return i;
      }
    }
    return -1;
  }

  public static int findItXOR(int[] a) {
    int xor = 0;
    for (int i = 0; i < a.length; i++) {
      xor ^= a[i];
    }
    return xor;
  }

  public static int findItFunctionXOR(int[] a) {
    // return Arrays.stream(a).reduce(0, (x, y) -> x ^ y); equivalent zu dem unteren, ohne static import
    return stream(a).reduce(0, (x, y) -> x ^ y);
    // 0 ^ a[o] ^ a[1] ^ ... ^ a[a.length - 1] , wo 0 die identity der Reduktion ist
    // Bie XOR heben sich alle Paare der gleichen Nummer auf und es bleibt nur noch die ungerade Nummer
  }
}
