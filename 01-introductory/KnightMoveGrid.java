import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class KnightMoveGrid {

  static int[][] knightMoves = {
    {2, 1}, {1, 2},
    {-2, 1}, {-1, 2},
    {2, -1}, {1, -2},
    {-2, -1}, {-1, -2},
  };

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    PrintWriter pw = new PrintWriter(System.out);

    int n = sc.nextInt();

    int[][] ans = new int[n][n];

    Queue<Point> pending = new LinkedList<>();
    pending.add(new Point(0, 0));
    while (!pending.isEmpty()) {
      Point cur = pending.poll();

      for (int i = 0; i < knightMoves.length; i++) {
        int nx = cur.x + knightMoves[i][0];
        int ny = cur.y + knightMoves[i][1];
        if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
        if (ans[nx][ny] != 0 || (nx == 0 && ny == 0)) continue;

        ans[nx][ny] = ans[cur.x][cur.y] + 1;
        pending.add(new Point(nx, ny));
      }
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        pw.print(ans[i][j] + " ");
      }
      pw.println();
    }

    pw.close();
  }

  static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
