package hashmap_sorting;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HashmapSorting
 */
public class HashmapSorting {

  //implementation of HashMap
  Map<String, Integer> map = new HashMap<String, Integer>();

  public static void main(String[] args) {
    HashmapSorting sv = new HashmapSorting();
    sv.createMap();
    System.out.println("Sorting values in ascending order:");
    sv.sortByValue(true);
    System.out.println("Sorting values in  descending order");
    sv.sortByValue(false);
  }

  //method to add elements in the HashMap
  void createMap() {
    map.put("Apple", 65000);
    map.put("HP", 20000);
    map.put("Dell", 32000);
    map.put("Asus", 21478);
    map.put("Samsung", 36546);
    map.put("Lenovo", 19990);
    System.out.println("Before sorting: ");
    printMap(map);
  }

  //sort elements by values
  void sortByValue(boolean order) {
    //convert HashMap into List
    var list = new LinkedList<Entry<String, Integer>>(map.entrySet());
    //sorting the list elements
    Collections.sort(
      list,
      new Comparator<Entry<String, Integer>>() {
        public int compare(
          Entry<String, Integer> o1,
          Entry<String, Integer> o2
        ) {
          if (order) {
            //compare two object and return an integer
            return o1.getValue().compareTo(o2.getValue());
          } else {
            return o2.getValue().compareTo(o1.getValue());
          }
        }
      }
    );
    //prints the sorted HashMap
    Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
    for (Entry<String, Integer> entry : list) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    printMap(sortedMap);
  }

  //method for printing the elements
  public void printMap(Map<String, Integer> map) {
    System.out.println("Company\t Price ");
    for (Entry<String, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + "\t" + entry.getValue());
    }
    System.out.println("\n");
  }
}
