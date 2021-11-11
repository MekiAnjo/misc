package java_functional.combinatorpattern;

import static java_functional.combinatorpattern.CustomerRegistrationValidator.ValidationResult;
import static java_functional.combinatorpattern.CustomerRegistrationValidator.ValidationResult.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

/**
 * CustomerRegistrationValidator
 */
public interface CustomerRegistrationValidator
  extends Function<Customer, ValidationResult> { // Function<input datentyp, Output Datentyp>
  static CustomerRegistrationValidator isEmailValid() {
    return customer -> { // '{}' werden benötigt falls ein Codeblock ausgeführt werden muss anstatt eine Codezeile
      System.out.println("running email validation");
      return customer.getEmail().contains("@")
        ? ValidationResult.SUCCESS
        : EMAIL_NOT_VALID;
    };
  }

  static CustomerRegistrationValidator isPhoneNumberValid() {
    return customer ->
      customer.getPhoneNumber().startsWith("+0")
        ? ValidationResult.SUCCESS
        : PHONE_NUMBER_NOT_VALID;
  }

  static CustomerRegistrationValidator isAdult() {
    return customer ->
      Period.between(customer.getDob(), LocalDate.now()).getYears() > 16
        ? ValidationResult.SUCCESS
        : IS_NOT_AN_ADULT;
  }

  default CustomerRegistrationValidator and( // This is where the magic happens (chaining functions)
    CustomerRegistrationValidator other
  ) {
    return customer -> {
      ValidationResult result = this.apply(customer);
      return result.equals(SUCCESS) ? other.apply(customer) : result; // solange der Wert SUCCESS ist wird weiter gechaint andernfalls wird das momentane Ergebnis zurückgegeben
    };
  }

  enum ValidationResult {
    SUCCESS,
    PHONE_NUMBER_NOT_VALID,
    EMAIL_NOT_VALID,
    IS_NOT_AN_ADULT,
  }
}
