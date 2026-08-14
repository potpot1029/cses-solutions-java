import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IncreasingArray {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int n = Integer.parseInt(br.readLine());

    int[] x = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      x[i] = Integer.parseInt(st.nextToken());
    }

    long ans = 0l;
    for (int i = 1; i < x.length; i++) {
      if (x[i] < x[i - 1]) {
        ans += x[i - 1] - x[i];
        x[i] = x[i - 1];
      }
    }

    pw.println(ans);

    pw.close();
  }
}
