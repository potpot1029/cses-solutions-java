import java.util.Scanner;

public class GrayCode {
  static String int2Binary(int bit, int x) {
    char[] res = new char[bit];
    for (int i = 0; i < bit; i++) res[i] = '0';

    int idx = bit - 1;
    while (x > 0) {
      res[idx] = (char) ('0' + x % 2);
      x /= 2;
      idx--;
    }

    return new String(res);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    int total = (int) Math.pow(2, n);

    boolean[] hasAppeared = new boolean[total];
    int cur = 0, cnt = 0;
    while (cnt < total) {
      System.out.println(int2Binary(n, cur));
      hasAppeared[cur] = true;
      cnt++;

      for (int i = 0; i < n; i++) {
        int nxtBit = (int) Math.pow(2, i);
        int nxt = cur ^ nxtBit;
        if (hasAppeared[nxt]) continue;

        cur = nxt;
        break;
      }
    }
  }
}
