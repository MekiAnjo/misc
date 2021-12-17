package CSVSort;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Testnio {

  public static void main(String[] args) throws IOException {
    Path utfFile = Files.createTempFile("some", ".txt");
    Files.writeString(utfFile, "this is my string ää öö üü");
    System.out.println("utfFile = " + utfFile);
  }
}
