import java.util.Scanner;

public class TrailingZeros {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();

    long ans = 0;
    long cnt5Factors = (long) Math.floor(Math.log(n) / Math.log(5));
    long prevRemFactor = 0;
    for (int i = (int) cnt5Factors; i >= 1; i--) {
      long remFactor = (long) (n / Math.pow(5, i));
      ans += i * (remFactor - prevRemFactor);
      prevRemFactor = remFactor;
    }

    System.out.println(ans);
  }
}
