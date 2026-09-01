import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class ArrayDivision {
  public static void main(String[] args) throws Exception {
    FastIO io = new FastIO();

    int n = io.nextInt(), targetNumDivisions = io.nextInt();
    int[] x = new int[n];
    long mxSum = 0, mnSum = Long.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      x[i] = io.nextInt();
      mxSum += (long) x[i];
      mnSum = Math.min(mnSum, (long) x[i]);
    }

    long left = mnSum, right = mxSum;
    while (left < right) {
      long mid = (left + right) / 2;

      int numDivisions = 1;
      long curSum = 0;
      for (int i = 0; i < n; i++) {
        if (curSum + (long) x[i] > mid) {
          numDivisions++;
          curSum = 0;
        }
        curSum += (long) x[i];
      }

      if (numDivisions > targetNumDivisions) left = mid + 1;
      else if (numDivisions < targetNumDivisions) right = mid - 1;
      else right = mid;
    }

    long realMxSum = 0, curSum = 0;
    for (int i = 0; i < n; i++) {
      if (curSum + (long) x[i] > left) {
        realMxSum = Math.max(realMxSum, curSum);
        curSum = 0;
      }
      curSum += (long) x[i];
    }
    realMxSum = Math.max(realMxSum, curSum);

    io.println(realMxSum);

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
