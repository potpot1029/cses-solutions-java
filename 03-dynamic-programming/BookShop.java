import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class BookShop {

  public static void main(String[] args) throws Exception {
    FastIO io = new FastIO();

    int numBooks = io.nextInt(), maxTotalPrice = io.nextInt();
    int[] bookPrices = new int[numBooks];
    for (int i = 0; i < numBooks; i++) bookPrices[i] = io.nextInt();
    int[] bookPages = new int[numBooks];
    for (int i = 0; i < numBooks; i++) bookPages[i] = io.nextInt();

    int[] maxPagesByPrice = new int[maxTotalPrice + 1];
    maxPagesByPrice[0] = 0;
    for (int i = 1; i <= maxTotalPrice; i++) maxPagesByPrice[i] = -1;

    for (int bookIdx = 0; bookIdx < numBooks; bookIdx++) {
      for (int curPrice = maxTotalPrice - bookPrices[bookIdx]; curPrice >= 0; curPrice--) {
        if (maxPagesByPrice[curPrice] == -1) continue;

        int newPrice = curPrice + bookPrices[bookIdx];
        maxPagesByPrice[newPrice] =
            Math.max(maxPagesByPrice[newPrice], maxPagesByPrice[curPrice] + bookPages[bookIdx]);
      }
    }

    int ans = 0;
    for (int i = 0; i <= maxTotalPrice; i++) ans = Math.max(ans, maxPagesByPrice[i]);

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
