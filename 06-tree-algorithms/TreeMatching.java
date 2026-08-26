import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.InputMismatchException;

public class TreeMatching {
  static ArrayList<Integer>[] graph;
  static int[][] dp;

  // TLE :(
  static void dfs(int v, int from) {
    for (int neighbor : graph[v]) {
      if (neighbor == from) continue;

      dfs(neighbor, v);

      // don't take edge v-neighbor
      dp[v][1] += Math.max(dp[neighbor][0], dp[neighbor][1]);
    }

    for (int neighbor : graph[v]) {
      if (neighbor == from) continue;

      // take edge v-neighbor
      dp[v][0] =
          Math.max(
              dp[v][0],
              dp[neighbor][1] + dp[v][1] - Math.max(dp[neighbor][0], dp[neighbor][1]) + 1);
    }
  }

  public static void main(String[] args) throws Exception {
    FastIO io = new FastIO();

    int n = io.nextInt();
    graph = new ArrayList[n + 1];
    dp = new int[n + 1][2];
    for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
    for (int i = 0; i < n - 1; i++) {
      int u = io.nextInt(), v = io.nextInt();
      graph[u].add(v);
      graph[v].add(u);
    }

    // dfs(1, 0);

    Deque<Integer> stack = new ArrayDeque<>();
    boolean[] visited = new boolean[n + 1];
    int[] parent = new int[n + 1], order = new int[n + 1];
    int orderPtr = 0;
    visited[1] = true;
    stack.add(1);

    while (!stack.isEmpty()) {
      int v = stack.pop();
      order[orderPtr++] = v;

      for (int neighbor : graph[v]) {
        if (visited[neighbor]) continue;
        visited[neighbor] = true;

        stack.add(neighbor);
        parent[neighbor] = v;
      }
    }

    for (int i = orderPtr - 1; i >= 0; i--) {
      int v = order[i];
      for (int neighbor : graph[v]) {
        if (neighbor == parent[v]) continue;

        // don't take edge v-neighbor
        dp[v][1] += Math.max(dp[neighbor][0], dp[neighbor][1]);
      }

      for (int neighbor : graph[v]) {
        if (neighbor == parent[v]) continue;

        // take edge v-neighbor
        dp[v][0] =
            Math.max(
                dp[v][0],
                dp[neighbor][1] + dp[v][1] - Math.max(dp[neighbor][0], dp[neighbor][1]) + 1);
      }
    }

    io.println(Math.max(dp[1][0], dp[1][1]));

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
