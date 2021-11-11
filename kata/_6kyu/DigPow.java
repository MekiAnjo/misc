package kata._6kyu;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * DigPow
 */

/* https://www.codewars.com/kata/5552101f47fc5178b1000050/train/java
 
    Some numbers have funny properties. For example:

    89 --> 8¹ + 9² = 89 * 1

    695 --> 6² + 9³ + 5⁴= 1390 = 695 * 2

    46288 --> 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51

    Given a positive integer n written as abcd... (a, b, c, d... being digits) and a positive integer p

    we want to find a positive integer k, if it exists, such as the sum of the digits of n taken to the successive powers of p is equal to k * n.
    In other words:

    Is there an integer k such as : (a ^ p + b ^ (p+1) + c ^(p+2) + d ^ (p+3) + ...) = n * k

    If it is the case we will return k, if not return -1.

    Note: n and p will always be given as strictly positive integers.

    digPow(89, 1) should return 1 since 8¹ + 9² = 89 = 89 * 1
    digPow(92, 1) should return -1 since there is no k such as 9¹ + 2² equals 92 * k
    digPow(695, 2) should return 2 since 6² + 9³ + 5⁴= 1390 = 695 * 2
    digPow(46288, 3) should return 51 since 4³ + 6⁴+ 2⁵ + 8⁶ + 8⁷ = 2360688 = 46288 * 51
 
 
 */
public class DigPow {

  public static void main(String[] args) {
    System.out.println(digPow(46288, 3));
  }

  public static long digPow(int n, int p) {
    int start = n;
    String temp = String.valueOf(n);
    int sum = 0;
    int potenz = temp.length() + p - 1;

    for (int i = 0; i < temp.length(); i++) {
      int digit = n % 10;
      sum += Math.pow(digit, potenz);
      potenz--;
      n /= 10;
    }

    return (sum % start == 0) ? sum / start : -1;
  }

  public static long digPow2(int n, int p) {
    String intString = String.valueOf(n);
    long sum = 0;
    for (int i = 0; i < intString.length(); ++i, ++p) sum +=
      Math.pow(Character.getNumericValue(intString.charAt(i)), p);
    return (sum % n == 0) ? sum / n : -1;
  }

  public static long digPow3(int n, int p) {
    List<Integer> digits = new ArrayList<>();
    int temp = n;
    while (temp > 0) {
      digits.add(0, temp % 10);
      temp /= 10;
    }
    final AtomicInteger value = new AtomicInteger(p);
    int sum = (int) digits
      .stream()
      .mapToDouble(Integer::doubleValue)
      .map(i -> Math.pow(i, value.getAndIncrement()))
      .sum();
    if (sum % n == 0) return sum / n;
    return -1;
  }

  public static long digPowFunction(int n, int p) {
    int[] digits = String
      .valueOf(n)
      .chars()
      .map(Character::getNumericValue)
      .toArray();
    int sum = IntStream
      .range(0, digits.length)
      .map(i -> (int) Math.pow(digits[i], i + p))
      .sum();
    return sum % n == 0 ? sum / n : -1;
  }
}
