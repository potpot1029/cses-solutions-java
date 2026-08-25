/*
 * passed on CF gym: https://codeforces.com/gym/102961/submission/388370628
 * but can't pass on CSES judge (TLE)
 */
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class TrafficLights {
  public static void main(String[] args) {
    FastIO io = new FastIO();

    long lenStreet = io.nextLong();
    int numTrafficLights = io.nextInt();

    PriorityQueue<Long> passageLens = new PriorityQueue<>(Collections.reverseOrder());
    PriorityQueue<Long> passageLensToDelete = new PriorityQueue<>(Collections.reverseOrder());
    passageLens.add(lenStreet);

    TreeSet<Long> trafficLightPositions = new TreeSet<>();
    trafficLightPositions.add((long) 0);
    trafficLightPositions.add(lenStreet);

    for (int i = 0; i < numTrafficLights; i++) {
      long newPos = io.nextLong();

      long left = trafficLightPositions.lower(newPos);
      long right = trafficLightPositions.higher(newPos);

      passageLensToDelete.add(right - left);
      passageLens.add(newPos - left);
      passageLens.add(right - newPos);

      while (!passageLensToDelete.isEmpty()
          && (long) passageLens.peek() == (long) passageLensToDelete.peek()) {
        passageLens.poll();
        passageLensToDelete.poll();
      }

      io.print(passageLens.peek() + " ");

      trafficLightPositions.add(newPos);
    }

    io.println();

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
