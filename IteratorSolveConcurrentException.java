import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Java program to illustrate
// ConcurrentModificationException

/**
 * IteratorSolveConcurrentException
 */
public class IteratorSolveConcurrentException {

  public static void main(String[] args) {
    // Creating an object of ArrayList
    // Declaring object of Integer type
    List<Integer> list = new ArrayList<>();

    // Adding element to ArrayList object created above
    // using the add() method
    // Custom input elements
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);

    // display all the elements of ArrayList object
    System.out.println("List Value Before Iteration: " + list);

    // anscheinend verursacht diese Methode keine Exception
    list.removeIf(value -> value.equals(3));
    // Print and display the value of ArrayList object
    System.out.println("List Value After Iteration: " + list);
  }
}
/*
import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;

// Java program to illustrate
// ConcurrentModificationException

*/
/**
 * IteratorSolveConcurrentException
 *//*

public class IteratorSolveConcurrentException {

  public static void main(String[] args) {
    // Creating an object of ArrayList
    // Declaring object of Integer type
    List<Integer> list = new ArrayList<>();

    // Adding element to ArrayList object created above
    // using the add() method
    // Custom input elements
    list.add(1);
    list.add(2);
    list.add(3);
    list.add(4);
    list.add(5);

    // display all the elements of ArrayList object
    System.out.println("List Value Before Iteration: " + list);

    // Creating an iterator object to
    //  iterate over the ArrayList
    Iterator<Integer> it = list.iterator();

    // Condition check
    // It holds true till there is single element
    // remaining in the List
    while (it.hasNext()) {
      Integer value = it.next();

      // Here we are trying to remove the one entry of
      // ArrayList based on the if condition and hence

      // We will get Concurrent ModificationException
      if (value.equals(3)) {
        it.remove(); // * This one will work
        // list.remove(value); // ! This one will throw an exception
      }
    }
    // Print and display the value of ArrayList object
    System.out.println("List Value After Iteration: " + list);
  }
}
*/
