import java.util.Scanner;

public class MissingNumber {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int ans = 0;
    for (int i = 1; i <= n; i++) {
      ans ^= i;
    }

    for (int i = 0; i < n - 1; i++) {
      int a = sc.nextInt();
      ans ^= a;
    }

    System.out.println(ans);
  }
}
