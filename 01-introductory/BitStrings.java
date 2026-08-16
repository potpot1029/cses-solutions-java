import java.util.Scanner;

public class BitStrings {
  static final int MOD = (int) 1e9 + 7;

  static long binaryExponentiation(long a, long b) {
    long res = 1;
    while (b > 0) {
      if (b % 2 == 1) res = (res * a) % MOD;
      a = (a * a) % MOD;
      b /= 2;
    }
    return res;
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    System.out.println(binaryExponentiation(2, n));
  }
}
