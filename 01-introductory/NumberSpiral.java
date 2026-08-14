import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NumberSpiral {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int t = Integer.parseInt(br.readLine());
    for (int tc = 0; tc < t; tc++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      long y = Long.parseLong(st.nextToken()), x = Long.parseLong(st.nextToken());

      long layer = Math.max(y, x);

      long ans = (layer - 1) * (layer - 1);
      long prev = ans;
      // if layer is odd the layer start on bottom left (y=layer): it goes down and then left
      // otherwise, layer start on top right (x=layer): it goes right and then up
      if (layer % 2 == 0) {
        if (x == layer) ans += y;
        else ans += (layer - x) + layer;
      } else {
        if (y == layer) ans += x;
        else ans += (layer - y) + layer;
      }

      pw.println(ans);
    }

    pw.close();
  }
}
