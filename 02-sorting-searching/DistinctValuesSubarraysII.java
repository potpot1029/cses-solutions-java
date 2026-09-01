import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.InputMismatchException;

public class DistinctValuesSubarraysII {
  public static void main(String[] args) throws Exception {
    FastIO io = new FastIO();

    int n = io.nextInt(), k = io.nextInt();
    int[] x = new int[n];
    for (int i = 0; i < n; i++) x[i] = io.nextInt();

    HashMap<Integer, Integer> num2Cnt = new HashMap<>();
    int left = 0, right = 1, prevEnd = -1, distinctCnt = 1;
    long numSubarrays = 0;
    num2Cnt.put(x[left], 1);
    while (left < right) {
      while (right < n) {
        int curCnt = num2Cnt.getOrDefault(x[right], 0);
        if (curCnt == 0) {
          if (distinctCnt == k) break;
          else distinctCnt++;
        }
        num2Cnt.put(x[right], curCnt + 1);
        right++;
      }

      long range = right - left;
      long overlapRange = 0;
      if (prevEnd > left) overlapRange = prevEnd - left;
      numSubarrays += (range + 1) * range / 2;
      numSubarrays -= (overlapRange + 1) * overlapRange / 2;

      prevEnd = right;

      if (right == n) break;
      if (right < n) {
        while (distinctCnt == k) {
          int curCnt = num2Cnt.getOrDefault(x[left], 0);
          if (curCnt == 1) distinctCnt--;
          num2Cnt.put(x[left], curCnt - 1);
          left++;
        }
        num2Cnt.put(x[right], num2Cnt.getOrDefault(x[right], 0) + 1);
        distinctCnt++;
        right++;
      }
    }

    io.println(numSubarrays);

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
