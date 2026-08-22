import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class GridPathDescription {

  static char[] dirChar = {'D', 'U', 'L', 'R'};
  static int[][] dir = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
  static boolean[][] blocked = new boolean[9][9];
  static int[] description = new int[48];

  static int bruteForce(int curPathPos, int x, int y) {
    if (x == 7 && y == 1) {
      if (curPathPos == description.length) return 1;
      else return 0;
    }
    if (curPathPos == description.length) return 0;
    if ((blocked[x][y + 1] && blocked[x][y - 1]) && (!blocked[x + 1][y] && !blocked[x - 1][y]))
      return 0;
    if ((!blocked[x][y + 1] && !blocked[x][y - 1]) && (blocked[x + 1][y] && blocked[x - 1][y]))
      return 0;

    blocked[x][y] = true;

    int cnt = 0;
    if (description[curPathPos] < 4) {
      int dirIdx = description[curPathPos];
      int dx = x + dir[dirIdx][0];
      int dy = y + dir[dirIdx][1];

      if (!blocked[dx][dy]) cnt += bruteForce(curPathPos + 1, dx, dy);
    } else if (y > 2
        && blocked[x][y - 2]
        && (blocked[x - 1][y - 1] || blocked[x + 1][y - 1])
        && !blocked[x][y - 1]) {
      int dx = x;
      int dy = y - 1;
      cnt += bruteForce(curPathPos + 1, dx, dy);
    } else if (x > 2 && blocked[x - 2][y] && blocked[x - 1][y - 1] && !blocked[x - 1][y]) {
      int dx = x - 1;
      int dy = y;
      cnt += bruteForce(curPathPos + 1, dx, dy);
    } else if (y < 6
        && blocked[x][y + 2]
        && (blocked[x - 1][y + 1] || blocked[x + 1][y + 1])
        && !blocked[x][y + 1]) {
      int dx = x;
      int dy = y + 1;
      cnt += bruteForce(curPathPos + 1, dx, dy);
    } else {
      for (int i = 0; i < dir.length; i++) {
        int dx = x + dir[i][0];
        int dy = y + dir[i][1];

        if (blocked[dx][dy]) continue;

        cnt += bruteForce(curPathPos + 1, dx, dy);
      }
    }

    blocked[x][y] = false;
    return cnt;
  }

  public static void main(String[] args) throws Exception {
    FastIO io = new FastIO();

    for (int i = 0; i < 9; i++) {
      blocked[i][0] = true;
      blocked[i][8] = true;
      blocked[0][i] = true;
      blocked[8][i] = true;
    }
    for (int i = 1; i <= 7; i++) {
      for (int j = 1; j <= 7; j++) {
        blocked[i][j] = false;
      }
    }

    String s = io.next();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      description[i] = 4;
      for (int j = 0; j < 4; j++) {
        if (dirChar[j] == c) description[i] = j;
      }
    }

    int ans = bruteForce(0, 1, 1);

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
