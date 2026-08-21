import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DistinctNumbers {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    int[] x = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      x[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(x);
    int ans = 0;
    int cur = -1;
    for (int i = 0; i < n; i++) {
      if (x[i] != cur) {
        ans++;
        cur = x[i];
      }
    }

    System.out.println(ans);
  }
}
