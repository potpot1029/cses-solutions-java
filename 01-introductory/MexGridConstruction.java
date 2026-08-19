import java.util.Scanner;

public class MexGridConstruction {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int mx = (int) Math.pow(4, (double) Math.round(Math.log(n) / Math.log(4)) + 1);
    boolean[][] didAppearCol = new boolean[n][mx];

    for (int i = 0; i < n; i++) {
      boolean[] didAppearRow = new boolean[mx];

      for (int j = 0; j < n; j++) {
        int mex;
        for (mex = 0; ; mex++) {
          if (didAppearCol[j][mex] || didAppearRow[mex]) continue;
          break;
        }
        System.out.print(mex + " ");
        didAppearRow[mex] = didAppearCol[j][mex] = true;
      }
      System.out.println();
    }
  }
}
