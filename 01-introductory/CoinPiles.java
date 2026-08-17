import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CoinPiles {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int t = Integer.parseInt(br.readLine());

    for (int tc = 0; tc < t; tc++) {
      // x = # of 1st op, y = # of 2nd op
      // 2x + y = a, x + 2y = b
      // y = a - 2x
      // x = (2a - b) / 3
      StringTokenizer st = new StringTokenizer(br.readLine());
      long a = Long.parseLong(st.nextToken()), b = Long.parseLong(st.nextToken());
      long x = (2 * a - b) / 3, y = a - 2 * x;

      boolean valid = x >= 0 && y >= 0 && (2 * x + y == a) && (x + 2 * y == b);

      pw.println((valid ? "YES" : "NO"));
    }

    pw.close();
  }
}
