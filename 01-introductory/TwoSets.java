import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TwoSets {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    int n = Integer.parseInt(br.readLine());
    if (n == 1) {
      pw.println("NO");
      pw.close();
      return;
    }

    long sum = 0;
    for (int i = 1; i <= n; i++) {
      sum += i;
    }

    if (sum % 2 != 0) {
      pw.println("NO");
      pw.close();
      return;
    }

    ArrayList<Integer> set1 = new ArrayList<>(), set2 = new ArrayList<>();
    long target = sum / 2;
    long sum1 = 0, sum2 = 0;
    for (int i = n; i >= 1; i--) {
      if (sum1 < target && sum1 + i <= target) {
        set1.add(i);
        sum1 += (long) i;
      } else {
        set2.add(i);
        sum2 += (long) i;
      }
    }

    pw.println("YES");
    pw.println(set1.size());
    for (int el : set1) {
      pw.print(el + " ");
    }
    pw.println();
    pw.println(set2.size());
    for (int el : set2) {
      pw.print(el + " ");
    }

    pw.close();
  }
}
