package hashmap_sorting;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HashmapSortByValue
 */
public class HashmapSortByValue {

  Map<String, Integer> map = new HashMap<>();

  public static void main(String[] args) {
    var dummyObject = new HashmapSortByValue();
    dummyObject.createMap();
    System.out.println("Sorting values in ascending order:");
    dummyObject.sortByValue(true);
    System.out.println("Sorting values in descending order:");
    dummyObject.sortByValue(false);
  }

  private void createMap() {
    map.put("Apple", 65000);
    map.put("HP", 20000);
    map.put("Dell", 32000);
    map.put("Asus", 21478);
    map.put("Samsung", 36546);
    map.put("Lenovo", 19990);
    System.out.println("Before sorting: ");
    printMap(map);
  }

  private void sortByValue(boolean order) {
    List<Entry<String, Integer>> list = new LinkedList<>(map.entrySet());
    list.sort(
      (o1, o2) -> {
        if (order) {
          return o1.getValue().compareTo(o2.getValue());
        } else {
          return o2.getValue().compareTo(o1.getValue());
        }
      }
    );

    Map<String, Integer> sortedMap = new LinkedHashMap<>();
    for (Entry<String, Integer> entry : list) {
      sortedMap.put(entry.getKey(), entry.getValue());
    }
    printMap(sortedMap);
  }

  private void printMap(Map<String, Integer> map) {
    System.out.println("Company\t Price ");
    for (Entry<String, Integer> entry : map.entrySet()) {
      System.out.println(entry.getKey() + "\t" + entry.getValue());
    }
    System.out.println("\n");
  }
}
