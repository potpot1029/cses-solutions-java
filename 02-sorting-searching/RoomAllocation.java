import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

public class RoomAllocation {
  public static void main(String[] args) {
    FastIO io = new FastIO();

    int n = io.nextInt();
    Customer[] customers = new Customer[n];
    int[] roomNos = new int[n];
    for (int i = 0; i < n; i++) {
      int a = io.nextInt(), b = io.nextInt();
      customers[i] = new Customer(i, a, b);
    }

    Arrays.sort(
        customers,
        Comparator.comparingInt((Customer a) -> a.arrivalDay).thenComparing(a -> a.departureDay));

    PriorityQueue<Room> rooms =
        new PriorityQueue<>(Comparator.comparingInt((Room a) -> a.departureDay));
    for (int i = 0; i < n; i++) {
      if (rooms.size() == 0 || rooms.peek().departureDay >= customers[i].arrivalDay) {
        rooms.add(new Room(rooms.size() + 1, customers[i].departureDay));
        roomNos[customers[i].id] = rooms.size();
      } else {
        Room roomToTake = rooms.poll();
        roomToTake.departureDay = customers[i].departureDay;
        rooms.add(roomToTake);
        roomNos[customers[i].id] = roomToTake.id;
      }
    }

    io.println(rooms.size());
    for (int i = 0; i < n; i++) {
      io.print(roomNos[i] + " ");
    }

    io.close();
  }

  static class Customer {
    private int id;
    private int arrivalDay;
    private int departureDay;

    Customer(int id, int arrivalDay, int departureDay) {
      this.id = id;
      this.arrivalDay = arrivalDay;
      this.departureDay = departureDay;
    }
  }

  static class Room {
    private int id;
    private int departureDay;

    Room(int id, int departureDay) {
      this.id = id;
      this.departureDay = departureDay;
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
