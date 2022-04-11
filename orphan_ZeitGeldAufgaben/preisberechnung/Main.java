package orphan_ZeitGeldAufgaben.preisberechnung;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {

  public static void main(String[] args) {
    BigDecimal total = BigDecimal.valueOf(123.45);
    BigDecimal discount = total
      .multiply(BigDecimal.valueOf(0.01))
      .setScale(2, RoundingMode.HALF_UP);
    BigDecimal beforeTax = total
      .subtract(discount)
      .setScale(2, RoundingMode.HALF_UP);
    BigDecimal salesTax = beforeTax
      .multiply(BigDecimal.valueOf(0.19))
      .setScale(2, RoundingMode.HALF_UP);
    BigDecimal result = beforeTax
      .add(salesTax)
      .setScale(2, RoundingMode.HALF_UP);
    System.out.println("Zwischensumme ohne Rabatt: " + total);
    System.out.println("Rabatt: " + discount);
    System.out.println("Zwischensumme mit Rabatt: " + beforeTax);
    System.out.println("MwSt: " + salesTax);
    System.out.println("Summe: " + result);
  }
}
