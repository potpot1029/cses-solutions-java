import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

public class MovieFestival {
  public static void main(String[] args) {
    FastIO io = new FastIO();

    int numMovies = io.nextInt();
    int[][] moviesStartEndTimes = new int[numMovies][2];
    for (int i = 0; i < numMovies; i++) {
      moviesStartEndTimes[i][0] = io.nextInt();
      moviesStartEndTimes[i][1] = io.nextInt();
    }

    Arrays.sort(
        moviesStartEndTimes, Comparator.comparingInt((int[] a) -> a[1]).thenComparing(a -> a[0]));

    int ans = 1;
    int startTime = moviesStartEndTimes[0][0], endTime = moviesStartEndTimes[0][1];
    for (int i = 1; i < numMovies; i++) {
      int nxtStartTime = moviesStartEndTimes[i][0];
      int nxtEndTime = moviesStartEndTimes[i][1];
      if (nxtStartTime >= endTime) {
        ans++;
        startTime = nxtStartTime;
        endTime = nxtEndTime;
      }
    }

    io.println(ans);

    io.close();
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
