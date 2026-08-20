import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class GridColoringI {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(System.out);
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
    char[][] original = new char[n][m];

    for (int i = 0; i < n; i++) {
      String line = br.readLine();
      for (int j = 0; j < m; j++) {
        original[i][j] = line.charAt(j);
      }
    }

    char[][] ans = new char[n][m];
    int[][] move = {
      {1, 0}, {0, 1}, {-1, 0}, {0, -1},
    };
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        boolean[] ok = {true, true, true, true};
        for (int k = 0; k < move.length; k++) {
          int di = i + move[k][0];
          int dj = j + move[k][1];
          if (di < 0 || di >= n || dj < 0 || dj >= m) continue;
          if (ans[di][dj] == 0) continue;

          ok[ans[di][dj] - 'A'] = false;
        }
        ok[original[i][j] - 'A'] = false;

        boolean found = false;
        for (int k = 0; k < 4; k++) {
          if (ok[k]) {
            found = true;
            ans[i][j] = (char) ('A' + k);
            break;
          }
        }

        if (!found) {
          pw.println("IMPOSSIBLE");
          pw.close();
          return;
        }
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        pw.print(ans[i][j]);
      }
      pw.println();
    }

    pw.close();
  }
}
