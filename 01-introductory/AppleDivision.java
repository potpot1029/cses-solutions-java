import java.util.Scanner;

public class AppleDivision {

  static long ans = Long.MAX_VALUE;

  static void bruteForce(int n, int curPos, long p[], long sum1, long sum2) {
    if (n == curPos) {
      ans = Math.min(ans, (long) Math.abs(sum1 - sum2));
      return;
    }

    bruteForce(n, curPos + 1, p, sum1 + p[curPos], sum2);
    bruteForce(n, curPos + 1, p, sum1, sum2 + p[curPos]);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    long[] p = new long[n];
    for (int i = 0; i < n; i++) {
      p[i] = sc.nextLong();
    }

    bruteForce(n, 0, p, 0, 0);

    System.out.println(ans);
  }
}
