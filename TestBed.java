import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.text.Normalizer.Form;

public class TestBed {

  public static void main(String[] args) {
    // String umlaute = "äöüÄÖÜß";

    // System.out.println(umlaute);

    // //normalized String
    // String normalized = Normalizer.normalize(umlaute, Form.NFD);

    // System.out.println(normalized);

    System.out.println(
      new String("ä".getBytes(), StandardCharsets.UTF_8).length()
    );

    String s2 = Normalizer.normalize("ä", Normalizer.Form.NFD);

    System.out.println(s2 + " is normalized" + s2.length());


    // var lists = List.of(List.of(1), List.of(2, 3), List.<Integer>of());

    // var result = lists
    //   .stream()
    //   .mapMulti((list, consumer) -> list.forEach(consumer))
    //   .collect(Collectors.toList());

    // System.out.println(result);

    // var result2 = lists
    //   .stream()
    //   .flatMap(Collection::stream)
    //   .collect(Collectors.toList());

    // System.out.println(result2);

    // List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
    // double percentage = .01;
    // List<Double> evenDoubles = integers
    //   .stream()
    //   .<Double>mapMulti((integer, consumer) -> {
    //     if (integer % 2 == 0) {
    //       consumer.accept((double) integer * (1 + percentage));
    //     }
    //   })
    //   .collect(Collectors.toList());

    // System.out.println(evenDoubles);

    // List<Integer> integers2 = Arrays.asList(1, 2, 3, 4, 5);
    // double percentage2 = .01;
    // List<Double> evenDoubles2 = integers
    //   .stream()
    //   .filter(integer -> integer % 2 == 0)
    //   .<Double>map(integer -> ((double) integer * (1 + percentage)))
    //   .collect(Collectors.toList());

    // System.out.println(evenDoubles2);

    // HashMap<Integer, Double> map = new HashMap<>();
  }
}
