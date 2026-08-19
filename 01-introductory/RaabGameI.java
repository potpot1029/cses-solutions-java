import java.util.Scanner;

public class RaabGameI {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int t = sc.nextInt();
    for (int tc = 0; tc < t; tc++) {
      int n = sc.nextInt();
      int a = sc.nextInt();
      int b = sc.nextInt();

      if ((a == 0 || b == 0) && a + b > 0) {
        System.out.println("NO");
        continue;
      }
      if (a + b > n) {
        System.out.println("NO");
        continue;
      }

      System.out.println("YES");
      for (int i = 1; i <= n; i++) {
        System.out.print(i + " ");
      }
      System.out.println();

      int right = a + b;
      for (int i = 0; i < a + b; i++) {
        System.out.print(((i + (right - b)) % right + 1) + " ");
      }
      for (int i = a + b + 1; i <= n; i++) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}
