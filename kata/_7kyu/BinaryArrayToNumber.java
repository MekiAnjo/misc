package kata._7kyu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * BinaryArrayToNumber
 */

/* 
  https://www.codewars.com/kata/578553c3a1b8d5c40300037c/train/java

  Given an array of ones and zeroes, convert the equivalent binary value to an integer.

  Eg: [0, 0, 0, 1] is treated as 0001 which is the binary representation of 1.

  Examples:

  Testing: [0, 0, 0, 1] ==> 1
  Testing: [0, 0, 1, 0] ==> 2
  Testing: [0, 1, 0, 1] ==> 5
  Testing: [1, 0, 0, 1] ==> 9
  Testing: [0, 0, 1, 0] ==> 2
  Testing: [0, 1, 1, 0] ==> 6
  Testing: [1, 1, 1, 1] ==> 15
  Testing: [1, 0, 1, 1] ==> 11
  
  However, the arrays can have varying lengths, not just limited to 4. 

*/
public class BinaryArrayToNumber {

  public static void main(String[] args) {
    System.out.println(ConvertBinaryArrayToInt(Arrays.asList(0, 0, 0, 1)));
    System.out.println(ConvertBinaryArrayToInt(Arrays.asList(1, 1, 1, 1)));
    System.out.println(ConvertBinaryArrayToInt(Arrays.asList(0, 1, 1, 1)));
    System.out.println(ConvertBinaryArrayToInt(Arrays.asList(1, 0, 0, 1)));
    System.out.println();
    System.out.println(
      ConvertBinaryArrayToIntFunction(Arrays.asList(0, 0, 0, 1))
    );
    System.out.println(
      ConvertBinaryArrayToIntFunction(Arrays.asList(1, 1, 1, 1))
    );
    System.out.println(
      ConvertBinaryArrayToIntFunction(Arrays.asList(0, 1, 1, 1))
    );
    System.out.println(
      ConvertBinaryArrayToIntFunction(Arrays.asList(1, 0, 0, 1))
    );
  }

  public static int ConvertBinaryArrayToInt(List<Integer> binary) {
    int decimal = 0;
    Collections.reverse(binary);
    for (int i = 0; i < binary.size(); i++) {
      decimal += binary.get(i) * Math.pow(2, i);
    }
    return decimal;
  }

  // ------------------------------------------------------------------------------------
  public static int ConvertBinaryArrayToIntFunction(List<Integer> binary) {
    return binary.stream().reduce((x, y) -> x * 2 + y).get();
  }

  /*  https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html

    reduce((x, y) -> x * 2 + y)


    For the first element:
    - x is 0 since there is the partial result yet. // glaube der meint das da noch kein partial result existiert (identity) 
    - y is the element (1 or 0)
    - so we have 0 * 2 + bitvalue

    For every other element:
    - x is the partial result
    - y is the element (1 or 0)
    - so for 101 we'd get: ((0 * 2 + 1) * 2 + 0) * 2 + 1
      Bits:                          ^ 1st    ^ 2nd    ^ 3rd
      
      = ((0 * 2 + a) * 2 + b) * 2 + c
      = (2 * (0 * 2 + a) * 2) + (2 * b)    + c        //https://en.wikipedia.org/wiki/Factorization (the last "2" (...) * 2 + c)     
      = (2 * 2 * (0 * 2 + a)) + (2 * b)    + c                        
      = (2^2   *          a)  + (2^1 * b)    + 2^0 * c
                          ^ 1st      ^ 2nd   ^ 3rd */
  // ------------------------------------------------------------------------------------
  public static int ConvertBinaryArrayToInt_ShiftingBits(List<Integer> binary) {
    int number = 0;
    for (int bit : binary) number = number << 1 | bit; // Basically reversing the bit order of the Array
    return number;
    /* 
    Alright, suppose the list of bits is [1, 1, 0, 1, 0, 1] and you start with int number = 0.

    Here's every operation: [1, 1, 0, 1, 0, 1] and you start with int number = 0.

    0 << 1 ⟶ 0

    0 | 1 ⟶ 1

    1 << 1 ⟶ 10

    10 | 1 ⟶ 11

    11 << 1 ⟶ 110

    110 | 0 ⟶ 110

    110 << 1 ⟶ 1100

    1100 | 1 ⟶ 1101

    1101 << 1 ⟶ 11010

    11010 | 0 ⟶ 11010

    11010 << 1 ⟶ 110100

    110100 | 1 ⟶ 110101 */
  }
}
