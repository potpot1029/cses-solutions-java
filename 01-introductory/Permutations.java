import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Permutations {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int n = Integer.parseInt(br.readLine());
    if (n == 1) {
      pw.println(1);
    } else if (n < 4) {
      pw.println("NO SOLUTION");
    } else {
      int cur = 2;
      for (int i = 0; i < n; i++) {
        pw.print(cur + " ");
        cur += 2;
        if (cur > n) cur = 1;
      }
      pw.println();
    }

    pw.close();
  }
}
