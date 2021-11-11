package CSVSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * highscoreCSVSort
 */
public class highscoreCSVSort {

  private static final String CSV_PATH = "CSVSort\\highscore.csv";
  private static boolean append = false;
  private static List<String> highscoreList = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    readAllLinesfromFile(CSV_PATH);
    System.out.println("Unsorted:\n");
    for (String highscoreString : highscoreList) {
      System.out.println(highscoreString);
    }

    highscoreList.add("");

    List<Player> players = convertToPlayers(highscoreList);
    System.out.println("SORTED:\n");
    for (Player player : players) {
      System.out.println(player);
    }

    writeToFile(CSV_PATH, players);
  }

  private static List<String> readAllLinesfromFile(String path)
    throws IOException {
    FileReader fileReader = new FileReader(path);
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    String line = null;
    while ((line = bufferedReader.readLine()) != null) {
      highscoreList.add(line);
    }
    bufferedReader.close();

    return highscoreList;
  }

  private static List<Player> convertToPlayers(List<String> playerStrings) {
    List<Player> players = new ArrayList<>();
    playerStrings.remove(0);
    for (String playerString : playerStrings) {
      String[] parts = playerString.split(",");
      String nickname = parts[0];
      int score = Integer.parseInt(parts[1]);
      players.add(new Player(nickname, score));
    }
    // Collections.sort(
    //   players,
    //   new Comparator<Player>() {
    //     @Override
    //     public int compare(Player o1, Player o2) {
    //       return o1.compareTo(o2);
    //     }
    //   }
    // );

    // players.sort((Player p1, Player p2) -> p1.compareTo(p2)); // short

    // players.sort((p1, p2) -> p1.compareTo(p2)); // shorter

    // players.sort(Player::compareTo); // shortest?

    // No need to implement the compare Interface anymore
    players.sort(Player::compareByScoreThenNickname); // shortest?

    for (Player player : players) {
      player.setPlace(players.indexOf(player) + 1);
    }

    return players;
  }

  private static void writeAllLinesToFile(
    String path,
    List<String> highscoreList
  ) throws IOException {
    FileWriter fileWriter = new FileWriter(path, append);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    for (String lines : highscoreList) {
      printWriter.printf("%s%n", lines);
    }
    printWriter.close();
  }

  private static void writeToFile(String path, List<Player> playerList)
    throws IOException {
    FileWriter fileWriter = new FileWriter(path, append);
    PrintWriter printWriter = new PrintWriter(fileWriter);
    printWriter.println("nickname,score");
    for (Player part : playerList) {
      printWriter.printf(
        "%s" + "," + "%d" + "%n",
        part.getNickname(),
        part.getScore()
      );
    }
    printWriter.close();
  }
}
