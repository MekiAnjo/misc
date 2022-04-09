package kata._4kyu.burrowsheelertransformation;

import static java.lang.Character.codePointAt;
import static java.util.stream.IntStream.range;
import static java.util.stream.Stream.concat;
import static java.util.stream.Stream.iterate;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Tevans {

  public static BWT encode(String input) {
    // Special case: empty string
    if (input.isEmpty()) {
      return new BWT("", -1);
    }

    // General case
    final char[] chars = input.toCharArray();
    final int length = chars.length;

    IndexFinder indexFinder = new IndexFinder(input);

    final int[] cipherCodePoints = concat(
      Stream.of(input),
      range(1, length).mapToObj(i -> new ShiftedSegment(i, chars))
    )
      .sorted(CharSequence::compare)
      .peek(indexFinder)
      .mapToInt(s -> codePointAt(s, length - 1))
      .toArray();

    return new BWT(
      new String(cipherCodePoints, 0, length),
      indexFinder.foundIndex
    );
  }

  public static String decode(final String cipherText, final int entryPoint) {
    // Special case: empty string
    if (cipherText.isEmpty()) {
      return "";
    }

    // General case
    final int length = cipherText.length();
    final CharTagger tagger = new CharTagger();
    final TaggedChar[] transitions = cipherText
      .codePoints()
      .mapToObj(tagger::tag)
      .sorted(TaggedChar.BY_CODE_POINT)
      .toArray(TaggedChar[]::new);
    final int[] plainCodePoints = iterate(
      transitions[entryPoint],
      tc -> transitions[tc.tag]
    )
      .limit(length)
      .mapToInt(tc -> tc.codePoint)
      .toArray();

    return new String(plainCodePoints, 0, length);
  }
}

// Helper classes

class CharTagger {

  private int nextTag = 0;

  TaggedChar tag(final int codePoint) {
    return new TaggedChar(codePoint, this.nextTag++);
  }
}

class IndexFinder implements Consumer<CharSequence> {

  int foundIndex = 0;
  private final String toFind;
  private boolean found = false;

  IndexFinder(final String toFind) {
    this.toFind = toFind;
  }

  @Override
  public void accept(CharSequence c) {
    if (!this.found) {
      if (this.toFind == c) {
        this.found = true;
      } else {
        this.foundIndex++;
      }
    }
  }
}

class ShiftedSegment implements CharSequence {

  private final int shift;
  private final char[] chars;

  ShiftedSegment(final int shift, final char[] chars) {
    this.shift = shift;
    this.chars = chars;
  }

  @Override
  public char charAt(int index) {
    index += this.shift;
    if (index >= this.chars.length) {
      index -= this.chars.length;
    }
    return this.chars[index];
  }

  @Override
  public int length() {
    return this.chars.length;
  }

  @Override
  public CharSequence subSequence(int start, int end) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    throw new UnsupportedOperationException();
  }
}

class TaggedChar {

  static final Comparator<TaggedChar> BY_CODE_POINT = (t1, t2) ->
    Integer.compare(t1.codePoint, t2.codePoint);

  final int codePoint;
  final int tag;

  TaggedChar(final int codePoint, final int tag) {
    this.codePoint = codePoint;
    this.tag = tag;
  }
}

class BWT {

  String res;
  int index;

  BWT(String res, int index) {
    this.res = res;
    this.index = index;
  }

  public boolean equals(BWT b) {
    System.out.println(b);
    return this.res.equals(b.res) && this.index == b.index;
  }

  public String toString() {
    return "obj :" + res + ":" + index;
  }
}
