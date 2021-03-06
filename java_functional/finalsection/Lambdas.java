package java_functional.finalsection;

import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * Lambdas
 */
public class Lambdas {

  public static void main(String[] args) {
    BiFunction<String, Integer, String> upperCaseName = (name, age) -> {
      // logic
      if (name.isBlank()) throw new IllegalArgumentException("");
      System.out.println(age);
      return name.toUpperCase();
    };

    System.out.println(upperCaseName.apply("Alex", 20));
  }

  class Service {

    Consumer<String> blah = s -> {};
  }
}
