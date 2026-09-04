import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Random;

public class JosephusProblemII {
  public static void main(String[] args) throws Exception {
    FastIO io = new FastIO();

    int n = io.nextInt(), k = io.nextInt();
    Treap circle = new Treap();
    for (int i = 1; i <= n; i++) {
      circle.insert(i);
    }

    int pos = 0;
    for (int i = 0; i < n; i++) {
      pos = (pos + k) % circle.size();
      int toKill = circle.kth(pos);
      io.print(toKill + " ");
      circle.erase(toKill);
    }

    io.println();
    io.close();
  }

  static class Treap {
    static final Random RNG = new Random();

    class Node {
      int value, priority;
      int size = 1;
      Node left, right;

      Node(int value) {
        this.value = value;
        this.priority = RNG.nextInt();
      }
    }

    private Node root;

    private void pull(Node node) {
      if (node == null) return;
      node.size = 1 + size(node.left) + size(node.right);
    }

    private Node[] split(Node node, int value) {
      if (node == null) return new Node[] {null, null};

      if (node.value < value) {
        Node[] parts = split(node.right, value);
        node.right = parts[0];
        pull(node);
        return new Node[] {node, parts[1]};
      } else {
        Node[] parts = split(node.left, value);
        node.left = parts[1];
        pull(node);
        return new Node[] {parts[0], node};
      }
    }

    private Node merge(Node left, Node right) {
      if (left == null) return right;
      if (right == null) return left;

      if (left.priority > right.priority) {
        left.right = merge(left.right, right);
        pull(left);
        return left;
      } else {
        right.left = merge(left, right.left);
        pull(right);
        return right;
      }
    }

    private Node erase(Node node, int value) {
      if (node == null) return null;

      if (value < node.value) node.left = erase(node.left, value);
      else if (value > node.value) node.right = erase(node.right, value);
      else return merge(node.left, node.right);

      pull(node);
      return node;
    }

    private int kth(Node node, int k) {
      int leftSize = size(node.left);
      if (k > leftSize) return kth(node.right, k - leftSize - 1);
      else if (k == leftSize) return node.value;
      else return kth(node.left, k);
    }

    private int size(Node node) {
      return node == null ? 0 : node.size;
    }

    void insert(int value) {
      Node[] parts = split(root, value);
      root = merge(merge(parts[0], new Node(value)), parts[1]);
    }

    void erase(int value) {
      root = erase(root, value);
    }

    int kth(int k) {
      return kth(root, k);
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
