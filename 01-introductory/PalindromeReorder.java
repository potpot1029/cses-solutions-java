import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PalindromeReorder {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());

    String s = st.nextToken();
    int n = s.length();

    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
      freq[c - 'A']++;
    }

    char[] ans = new char[n];
    for (int i = 0; i < n / 2 + (n % 2); i++) {
      int targetFreq = 2;
      if (i == n / 2) targetFreq = 1;

      for (int j = 0; j < 26; j++) {
        if (freq[j] >= targetFreq) {
          ans[i] = (char) ('A' + j);
          ans[n - i - 1] = (char) ('A' + j);
          freq[j] -= targetFreq;
          break;
        }
      }
    }

    for (int i = 0; i < 26; i++) {
      if (freq[i] > 0) {
        pw.println("NO SOLUTION");
        pw.close();
        return;
      }
    }
    pw.println(new String(ans));
    pw.close();
  }
}
