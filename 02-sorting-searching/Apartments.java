import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Apartments {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    long k = Long.parseLong(st.nextToken());
    long[] a = new long[n], b = new long[m];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      a[i] = Long.parseLong(st.nextToken());
    }
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < m; i++) {
      b[i] = Long.parseLong(st.nextToken());
    }

    int ans = 0;
    Arrays.sort(a);
    Arrays.sort(b);
    int ptrA = 0;
    for (int i = 0; i < m; i++) {
      while (ptrA < n && a[ptrA] < b[i] - k) ptrA++;
      if (ptrA == n) break;
      if (a[ptrA] <= b[i] + k) {
        ans++;
        ptrA++;
      }
    }

    System.out.println(ans);
  }
}
