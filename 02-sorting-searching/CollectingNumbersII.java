import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class CollectingNumbersII {
  static final int MAX_NUMBER = (int) 2e5;

  public static void main(String[] args) {
    FastIO io = new FastIO();

    int n = io.nextInt(), numQueries = io.nextInt();
    int[] num2Idx = new int[MAX_NUMBER + 1], x = new int[n];
    int numRounds = 1;
    for (int i = 0; i < n; i++) {
      x[i] = io.nextInt();
      if (x[i] > 1 && num2Idx[x[i] - 1] == 0) numRounds++;
      num2Idx[x[i]] = i + 1;
    }

    for (int i = 0; i < numQueries; i++) {
      int idxA = io.nextInt(), idxB = io.nextInt();

      if (idxA > idxB) {
        int tmp = idxA;
        idxA = idxB;
        idxB = tmp;
      }

      int a = x[idxA - 1], b = x[idxB - 1];
      if (a + 1 <= MAX_NUMBER && a + 1 != b && idxA < num2Idx[a + 1] && idxB > num2Idx[a + 1])
        numRounds++;
      if (a - 1 >= 1 && a - 1 != b && idxA > num2Idx[a - 1] && idxB < num2Idx[a - 1]) numRounds++;

      if (a + 1 <= MAX_NUMBER && a + 1 != b && idxA > num2Idx[a + 1] && idxB < num2Idx[a + 1])
        numRounds--;
      if (a - 1 >= 1 && a - 1 != b && idxA < num2Idx[a - 1] && idxB > num2Idx[a - 1]) numRounds--;

      if (b + 1 <= MAX_NUMBER && b + 1 != a && idxB < num2Idx[b + 1] && idxA > num2Idx[b + 1])
        numRounds++;
      if (b - 1 >= 1 && b - 1 != a && idxB > num2Idx[b - 1] && idxA < num2Idx[b - 1]) numRounds++;

      if (b + 1 <= MAX_NUMBER && b + 1 != a && idxB > num2Idx[b + 1] && idxA < num2Idx[b + 1])
        numRounds--;
      if (b - 1 >= 1 && b - 1 != a && idxB < num2Idx[b - 1] && idxA > num2Idx[b - 1]) numRounds--;

      if (a + 1 == b) numRounds++;
      if (b + 1 == a) numRounds--;

      num2Idx[a] = idxB;
      num2Idx[b] = idxA;

      x[idxA - 1] = b;
      x[idxB - 1] = a;

      io.println(numRounds);
    }

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
