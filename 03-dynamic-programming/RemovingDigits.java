import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class RemovingDigits {

  static ArrayList<Integer> num2Digits(int num) {
    ArrayList<Integer> res = new ArrayList<>();
    while (num > 0) {
      res.add(num % 10);
      num /= 10;
    }
    return res;
  }

  public static void main(String[] args) throws Exception {
    FastIO io = new FastIO();

    int n = io.nextInt();
    int[] dp = new int[n + 1];
    dp[n] = 0;
    for (int i = 0; i < n; i++) dp[i] = Integer.MAX_VALUE;

    for (int curN = n; curN >= 0; curN--) {
      if (dp[curN] == Integer.MAX_VALUE) continue;

      ArrayList<Integer> digits = num2Digits(curN);
      for (int digit : digits) {
        int newN = curN - digit;
        if (newN >= 0) dp[newN] = Math.min(dp[newN], dp[curN] + 1);
      }
    }

    io.println(dp[0]);

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
