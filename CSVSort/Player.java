package CSVSort;

/**
 * player
 */
public class Player {

  private String nickname;
  private int place;
  private int score;

  public Player(String nickname, int score, int place) {
    this.nickname = nickname;
    this.score = score;
    this.place = place;
  }

  public int getPlace() {
    return this.place;
  }

  public void setPlace(int place) {
    this.place = place;
  }

  public Player(String nickname, int score) {
    this.nickname = nickname;
    this.score = score;
  }

  public String getNickname() {
    return this.nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public int getScore() {
    return this.score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return (
      "Player{" +
      " nickname='" +
      getNickname() +
      "'" +
      ", score='" +
      getScore() +
      "'" +
      ", place='" +
      getPlace() +
      "'" +
      "}"
    );
  }

  // @Override
  // public int compareTo(Player p) {
  //   if (this.score == p.score) {
  //     return this.nickname.compareTo(p.nickname);
  //   }
  //   return (p.score - this.score) > 0 ? 1 : -1;
  // }

  public int compareByScoreThenNickname(Player p) {
    if (this.score == p.score) {
      return this.nickname.compareTo(p.nickname);
    }
    return (p.score - this.score) > 0 ? 1 : -1;
  }
}
