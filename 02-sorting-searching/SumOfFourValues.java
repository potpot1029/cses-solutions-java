import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;

public class SumOfFourValues {
  public static void main(String[] args) {
    FastIO io = new FastIO();

    int n = io.nextInt();
    int target = io.nextInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++) a[i] = io.nextInt();

    HashMap<Integer, int[]> sum2PairIdxs = new HashMap<>();
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int newTarget = target - a[i] - a[j];
        if (sum2PairIdxs.containsKey(newTarget)) {
          int k = sum2PairIdxs.get(newTarget)[0];
          int l = sum2PairIdxs.get(newTarget)[1];
          io.println((i + 1) + " " + (j + 1) + " " + (k + 1) + " " + (l + 1));
          io.close();
          return;
        }
      }

      for (int j = 0; j < i; j++) {
        sum2PairIdxs.put(a[i] + a[j], new int[] {i, j});
      }
    }

    io.println("IMPOSSIBLE");

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

    public long nextLong() { // nextLong() would be implemented similarly
      long c;
      do {
        c = nextByte();
      } while (c <= ' ');

      long sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = nextByte();
      }

      long res = 0;
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
