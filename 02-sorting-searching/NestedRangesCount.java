// passed on codeforces: https://codeforces.com/gym/102961/submission/389377418; not on CSES because
// of TLE
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Random;

public class NestedRangesCount {
  public static void main(String[] args) {
    FastIO io = new FastIO();

    int numRanges = io.nextInt();
    Range[] ranges = new Range[numRanges];
    for (int i = 0; i < numRanges; i++) {
      ranges[i] = new Range(i, io.nextInt(), io.nextInt());
    }

    Arrays.sort(
        ranges,
        Comparator.comparingInt((Range a) -> a.start)
            .thenComparing(a -> a.end, Comparator.reverseOrder()));

    int[] contains = new int[numRanges];
    int[] isContained = new int[numRanges];

    Treap prevEnds = new Treap();
    prevEnds.insert(ranges[numRanges - 1].end);
    for (int i = numRanges - 2; i >= 0; i--) {
      contains[ranges[i].id] = prevEnds.orderOfValue(ranges[i].end);
      prevEnds.insert(ranges[i].end);
    }
    Treap prevEndsRe = new Treap();
    prevEndsRe.insert(ranges[0].end);
    for (int i = 1; i < numRanges; i++) {
      isContained[ranges[i].id] =
          prevEndsRe.size() - prevEndsRe.orderOfValueExclusive(ranges[i].end);
      prevEndsRe.insert(ranges[i].end);
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < numRanges; i++) {
      sb.append(contains[i]).append(" ");
    }
    sb.append("\n");
    for (int i = 0; i < numRanges; i++) {
      sb.append(isContained[i]).append(" ");
    }
    io.print(sb);

    io.close();
  }

  static class Range {
    private int id;
    private int start;
    private int end;

    Range(int id, int start, int end) {
      this.id = id;
      this.start = start;
      this.end = end;
    }
  }

  static class Treap {
    static final Random RNG = new Random();

    class Node {
      int value;
      int priority;
      int size = 1, frequency = 1;
      Node left, right;

      Node(int value) {
        this.value = value;
        this.priority = RNG.nextInt();
      }
    }

    private Node root;

    private void pull(Node node) {
      if (node == null) return;
      node.size = node.frequency + size(node.left) + size(node.right);
    }

    private Node insert(Node node, int value) {
      if (node == null) return new Node(value);

      if (value == node.value) {
        node.frequency++;
        pull(node);
        return node;
      } else if (value < node.value) {
        node.left = insert(node.left, value);

        if (node.left.priority > node.priority) {
          Node child = node.left;

          node.left = child.right;
          child.right = node;

          pull(node);
          pull(child);

          return child;
        }
      } else {
        node.right = insert(node.right, value);

        if (node.right.priority > node.priority) {
          Node child = node.right;

          node.right = child.left;
          child.left = node;

          pull(node);
          pull(child);

          return child;
        }
      }

      pull(node);
      return node;
    }

    private int orderOfValue(Node node, int value) {
      int result = 0;

      while (node != null) {
        if (value < node.value) {
          node = node.left;
        } else {
          result += size(node.left) + node.frequency;
          node = node.right;
        }
      }

      return result;
    }

    private int orderOfValueExclusive(Node node, int value) {
      int result = 0;

      while (node != null) {
        if (value <= node.value) {
          node = node.left;
        } else {
          result += size(node.left) + node.frequency;
          node = node.right;
        }
      }

      return result;
    }

    private int size(Node node) {
      return node == null ? 0 : node.size;
    }

    void insert(int value) {
      root = insert(root, value);
    }

    int orderOfValue(int value) {
      return orderOfValue(root, value);
    }

    int orderOfValueExclusive(int value) {
      return orderOfValueExclusive(root, value);
    }

    int size() {
      return size(root);
    }
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
