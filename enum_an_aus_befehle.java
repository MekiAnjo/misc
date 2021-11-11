/**
 * enum_an_aus_befehle
 */

//  credit: https://www.java-forum.org/thema/wann-sollte-man-enum-nutzen.143481/
public class enum_an_aus_befehle {

  public enum BefehleEnum {
    AUS(0),
    LAUTER(1),
    LEISER(2),
    AN(3);

    private final short befehlsCode;

    private BefehleEnum(int befehlsCode) {
      this.befehlsCode = (short) befehlsCode;
    }

    public short getBefehlsCode() {
      return this.befehlsCode;
    }
  }
}
