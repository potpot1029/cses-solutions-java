import java.util.Scanner;

public class TwoKnights {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    for (long i = 1; i <= n; i++) {
      // total possible piece placements: iC2
      long ans = (i * i) * (i * i - 1) / 2;
      // total knight placements * 2x3 blocks
      ans -= 2 * ((i - 1) * (i - 2));
      // total knight placements * 3x2 blocks
      ans -= 2 * ((i - 1) * (i - 2));
      System.out.println(ans);
    }
  }
}
