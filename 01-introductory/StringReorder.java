import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class StringReorder {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);

    String s = br.readLine();

    char[] original = s.toCharArray();
    int n = original.length;
    int[] freq = new int[26];
    for (char c : original) {
      freq[c - 'A']++;
    }

    char[] ans = new char[n];
    for (int i = 0; i < n; i++) {
      boolean done = false;
      for (int j = 0; j < 26; j++) {
        if (freq[j] == 0) continue;
        if (i > 0 && j == ans[i - 1] - 'A') continue;

        int mxCharCnt = 0;
        for (int k = 0; k < 26; k++) {
          int curCharCnt = freq[k];
          if (k == j) curCharCnt--;
          mxCharCnt = Math.max(mxCharCnt, curCharCnt);
        }

        if ((n - i) / 2 >= mxCharCnt) {
          ans[i] = (char) ('A' + j);
          freq[j]--;
          done = true;
          break;
        }
      }

      if (!done) {
        pw.println(-1);
        pw.close();
        return;
      }
    }

    for (char c : ans) {
      pw.print(c);
    }
    pw.println();

    pw.close();
  }
}
