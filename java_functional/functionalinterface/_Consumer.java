package java_functional.functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * _Consumer
 * Takes 1 input and returns nothing
 */
public class _Consumer { // Consumer behaves like a void function

  public static void main(String[] args) {
    // Normal java function
    Customer maria = new Customer("Maria", "99999");
    greetCustomer(maria);
    greetCustomerV2(maria, true);

    // Consumer Functional interface
    greetCustomerConsumer.accept(maria);
    greetCustomerConsumerV2.accept(maria, false);
  }

  static BiConsumer<Customer, Boolean> greetCustomerConsumerV2 = (customer, showPhoneNumber) ->
    System.out.println(
      "Hello " +
      customer.customerName +
      ", thanks for registering phone number " +
      (showPhoneNumber ? customer.customerPhoneNumber : "*********")
    );

  static Consumer<Customer> greetCustomerConsumer = customer ->
    System.out.println(
      "Hello " +
      customer.customerName +
      ", thanks for registering phone number " +
      customer.customerPhoneNumber
    );

  static void greetCustomer(Customer customer) {
    System.out.println(
      "Hello " +
      customer.customerName +
      ", thanks for registering phone number " +
      customer.customerPhoneNumber
    );
  }

  static void greetCustomerV2(Customer customer, boolean showPhoneNumber) {
    System.out.println(
      "Hello " +
      customer.customerName +
      ", thanks for registering phone number " +
      (
        Boolean.TRUE.equals(showPhoneNumber) // bessere boolean abfrage weil keine Nullpointerexception auftreten kann
          ? customer.customerPhoneNumber
          : "*********"
      )
    );
  }

  static class Customer {

    private final String customerName;
    private final String customerPhoneNumber;

    Customer(String customerName, String customerPhoneNumber) {
      this.customerName = customerName;
      this.customerPhoneNumber = customerPhoneNumber;
    }
  }
}
