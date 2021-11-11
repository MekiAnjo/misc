package kata._5kyu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * PrimeDecomp
 * 
 * https://www.codewars.com/kata/54d512e62a5e54c96200019e/train/java
 * 
 * 
 * 
 * Given a positive number n > 1 find the prime factor decomposition of n. The result will be a string with the following form :

  "(p1**n1)(p2**n2)...(pk**nk)"

  with the p(i) in increasing order and n(i) empty if n(i) is 1.

  Example: n = 86240 should return "(2**5)(5)(7**2)(11)"

  https://github.com/krewast/Tutorium-PG2-Java/blob/master/Zu%2003%20-%20Operatoren%2C%20Schleifen%20und%20Verzweigungen/3.7%20-%20Primfaktorzerlegung/src/Primfaktorzerlegung.java
 */
public class PrimeDecomp {

  public static void main(String[] args) {
    System.out.println(factors(86240));
  }

  public static String factors(int n) {
    StringBuilder sb = new StringBuilder();
    List<Integer> factors = new ArrayList<>();
    for (int i = 2; i < n; i++) {
      if (n % i == 0) {
        // Suche nach ganzzahligen Teiler von n
        factors.add(i);
        n /= i;
        i--;
        // falls n nochmal durch die gleiche Zahl geteilt werden kann
      }
    }
    if (n != 0) {
      factors.add(n);
    }
    List<Integer> distinctFactorsList = factors
      .stream()
      .distinct()
      .collect(Collectors.toList());
    for (int i = 0; i < distinctFactorsList.size(); i++) {
      sb.append("(");
      int temp = distinctFactorsList.get(i);
      int potenz = 0;
      sb.append(distinctFactorsList.get(i));
      for (int j = 0; j < factors.size(); j++) {
        if (factors.get(j) == temp) potenz++;
      }
      if (i < distinctFactorsList.size() - 1 && potenz > 1) {
        sb.append("**" + potenz);
      }
      sb.append(")");
    }
    return sb.toString();
  }

  public static String factors2(int lst) {
    String result = "";
    for (int fac = 2; fac <= lst; ++fac) {
      int count;
      for (count = 0; lst % fac == 0; ++count) {
        lst /= fac;
      }
      if (count > 0) {
        result += "(" + fac + (count > 1 ? "**" + count : "") + ")";
      }
    }
    return result;
  }

  public static String factors3(int n) {
    // your code

    int denom = 2;
    Map<Integer, Integer> arr = new TreeMap<>();

    while (n != 1) {
      if (n % denom == 0) {
        arr.put(denom, arr.getOrDefault(denom, 0) + 1);
        n /= denom;
      } else {
        denom++;
      }
    }

    String result = "";

    for (Integer i : arr.keySet()) {
      if (arr.get(i) == 1) {
        result = result + "(" + i + ")";
      } else {
        result = result + "(" + i + "**" + arr.get(i) + ")";
      }
    }

    return result;
  }

  public static String factors4(int n) {
    ArrayList<Integer> result = PrimeDecomp.defactor(n);
    Iterator<Integer> itre = result.iterator();
    int k;
    String finals;
    int temp = 0;
    int count = 1;
    StringBuffer stringResult = new StringBuffer();
    temp = itre.next(); // 2 2 4 4 4 3 6
    stringResult.append('(');
    stringResult.append(temp);
    while (itre.hasNext()) {
      k = itre.next();
      if (k == temp) {
        count++;
        continue;
      }
      if (count > 1) {
        stringResult.append('*');
        stringResult.append('*');
        stringResult.append(count);
      }
      stringResult.append(')');
      stringResult.append('(');
      stringResult.append(k);
      count = 1;
      temp = k;
    }
    stringResult.append(')');
    finals = stringResult.toString();
    return finals;
  }

  public static boolean isPrime(int n) {
    if (n == 2) return true;
    if (n % 2 == 0) return false;
    for (int i = 3; i * i <= n; i += 2) if (n % i == 0) return false;
    return true;
  }

  public static int primeBeforeRoot(int n) {
    int k = (int) Math.sqrt(n);
    //System.out.println(k);
    for (int i = k; i > 1; i--) {
      if (n % i == 0 && PrimeDecomp.isPrime(i)) return i;
    }
    return -1;
  }

  public static ArrayList<Integer> defactor(int n) {
    int temp = 0;
    ArrayList<Integer> result = new ArrayList<Integer>();
    while (primeBeforeRoot(n) != -1) {
      temp = PrimeDecomp.primeBeforeRoot(n);
      result.add(temp);
      n = n / temp;
    }
    result.add(n);
    Collections.sort(result);
    return result;
  }

  public static String factors5(int n) {
    String result = "";

    for (int p : PrimeSieve.getInstance()) {
      if (n % p != 0) continue;

      int c = 0;
      while (n % p == 0) {
        n /= p;
        c++;
      }

      if (c == 1) {
        result += "(" + p + ")";
      } else {
        result += "(" + p + "**" + c + ")";
      }

      if (n == 1) break;
    }

    return result;
  }
}

// Not strictly necessary to pass, but if you have it laying around...
class PrimeSieve implements Iterable<Integer> {

  private static PrimeSieve singleton;
  private LinkedList<Integer> primes;

  private PrimeSieve() {
    primes = new LinkedList<Integer>();
    primes.add(2);
    primes.add(3);
  }

  public static PrimeSieve getInstance() {
    if (singleton == null) {
      singleton = new PrimeSieve();
    }
    return singleton;
  }

  @Override
  public Iterator<Integer> iterator() {
    return new PrimesIterator();
  }

  // Generate the next prime number
  public synchronized int findNext() {
    int next = primes.getLast();
    outer:while (true) {
      next += 2;
      int max = (int) Math.pow(next, 0.5);
      for (int prime : primes) {
        if (prime > max) {
          break;
        }
        if (next % prime == 0) {
          continue outer;
        }
      }
      primes.add(next);
      return next;
    }
  }

  private class PrimesIterator implements Iterator<Integer> {

    private Iterator<Integer> iterator;

    private PrimesIterator() {
      iterator = primes.iterator();
    }

    @Override
    public boolean hasNext() {
      return true;
    }

    @Override
    public Integer next() {
      if (iterator == null) {
        return findNext();
      }
      if (iterator.hasNext()) {
        return iterator.next();
      } else {
        iterator = null;
        return findNext();
      }
    }
  }

  public static String factors6(int n) {
    List<String> l = new ArrayList<String>();
    for (int i = 2; i <= n; i++) {
      int times = 0;
      while (n % i == 0) {
        n /= i;
        times++;
      }
      if (times == 1) l.add(Integer.toString(i)); else if (times > 1) l.add(
        String.format("%d**%d", i, times)
      );
    }
    return l.stream().collect(Collectors.joining(")(", "(", ")"));
  }
}
