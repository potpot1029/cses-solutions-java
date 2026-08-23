import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class SumOfTwoValues {
  public static void main(String[] args) {
    FastIO io = new FastIO();

    int n = io.nextInt(), target = io.nextInt();
    int[] a = new int[n];
    HashMap<Integer, ArrayList<Integer>> numbers2Idx = new HashMap<>();
    for (int i = 0; i < n; i++) {
      a[i] = io.nextInt();
      if (!numbers2Idx.containsKey(a[i])) numbers2Idx.put(a[i], new ArrayList<>());
      numbers2Idx.get(a[i]).add(i + 1);
    }

    boolean ok = false;
    for (int i = 0; i < n; i++) {
      int want = target - a[i];
      int minOccurrences = (want == a[i] ? 2 : 1);
      if (numbers2Idx.containsKey(want) && numbers2Idx.get(want).size() >= minOccurrences) {
        int curIdx = i + 1;
        int anotherIdx = -1;
        for (int idx : numbers2Idx.get(want)) {
          if (idx != curIdx) {
            anotherIdx = idx;
            break;
          }
        }
        io.println(curIdx + " " + anotherIdx);
        ok = true;
        break;
      }
    }

    if (!ok) io.println("IMPOSSIBLE");

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
