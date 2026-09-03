import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class NestedRangesCheck {
  public static void main(String[] args) {
    FastIO io = new FastIO();

    int numRanges = io.nextInt();
    Range[] ranges = new Range[numRanges];
    for (int i = 0; i < numRanges; i++) {
      ranges[i] = new Range(i, io.nextInt(), io.nextInt());
    }

    Arrays.sort(
        ranges,
        Comparator.comparingInt((Range a) -> a.start)
            .thenComparing(a -> a.end, Comparator.reverseOrder()));

    boolean[] contains = new boolean[numRanges];
    boolean[] isContained = new boolean[numRanges];

    Range curRange = ranges[0];
    for (int i = 1; i < numRanges; i++) {
      if (ranges[i].start == curRange.start) isContained[ranges[i].id] = true;
      else if (curRange.end >= ranges[i].end) isContained[ranges[i].id] = true;
      else curRange = ranges[i];
    }
    curRange = ranges[numRanges - 1];
    for (int i = numRanges - 2; i >= 0; i--) {
      if (ranges[i].start == curRange.start) contains[ranges[i].id] = true;
      else if (curRange.end <= ranges[i].end) contains[ranges[i].id] = true;
      else curRange = ranges[i];
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numRanges; i++) {
      sb.append(contains[i] ? 1 : 0).append(" ");
    }
    sb.append("\n");
    for (int i = 0; i < numRanges; i++) {
      sb.append(isContained[i] ? 1 : 0).append(" ");
    }
    io.print(sb);

    io.close();
  }

  static class Range {
    private int id;
    private int start;
    private int end;

    Range(int id, int start, int end) {
      this.id = id;
      this.start = start;
      this.end = end;
    }
  }

  // from https://usaco.guide/general/fast-io?lang=java
  static class FastIO extends PrintWriter {
    private InputStream stream;
    private byte[] buf = new byte[1 << 16];
    private int curChar;
    private int numChars;

    // standard input
    public FastIO() {
      this(System.in, System.out);
    }

    public FastIO(InputStream i, OutputStream o) {
      super(o);
      stream = i;
    }

    // file input
    public FastIO(String i, String o) throws IOException {
      super(new FileWriter(o));
      stream = new FileInputStream(i);
    }

    // throws InputMismatchException() if previously detected end of file
    private int nextByte() {
      if (numChars == -1) {
        throw new InputMismatchException();
      }
      if (curChar >= numChars) {
        curChar = 0;
        try {
          numChars = stream.read(buf);
        } catch (IOException e) {
          throw new InputMismatchException();
        }
        if (numChars == -1) {
          return -1; // end of file
        }
      }
      return buf[curChar++];
    }

    // to read in entire lines, replace c <= ' '
    // with a function that checks whether c is a line break
    public String next() {
      int c;
      do {
        c = nextByte();
      } while (c <= ' ');

      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = nextByte();
      } while (c > ' ');
      return res.toString();
    }

    public int nextInt() { // nextLong() would be implemented similarly
      int c;
      do {
        c = nextByte();
      } while (c <= ' ');

      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = nextByte();
      }

      int res = 0;
      do {
        if (c < '0' || c > '9') {
          throw new InputMismatchException();
        }
        res = 10 * res + c - '0';
        c = nextByte();
      } while (c > ' ');
      return res * sgn;
    }

    public double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}
