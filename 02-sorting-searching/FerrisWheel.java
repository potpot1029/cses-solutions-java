import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FerrisWheel {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());

    long[] p = new long[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      p[i] = Long.parseLong(st.nextToken());
    }

    int ans = 0;
    Arrays.sort(p);
    int left = 0;
    for (int right = n - 1; right >= left; right--) {
      ans++;
      if (p[left] + p[right] <= x) left++;
    }

    System.out.println(ans);
  }
}
