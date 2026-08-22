import java.util.Scanner;

public class DigitQueries {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int q = sc.nextInt();

    for (int tc = 0; tc < q; tc++) {
      long k = sc.nextLong();
      long numDigits = 1, numNumbers = 9, start = 1;
      while (k > numDigits * numNumbers) {
        k -= numDigits * numNumbers;

        numNumbers *= 10;
        numDigits++;
        start *= 10;
      }

      // k = 12 -> k = 3 -> 10 + 1 = 11 (1st digit)
      long number = start + (k - 1) / numDigits;
      long dist = numDigits - ((k - 1) % numDigits);
      long ans = 0;
      while (dist > 0) {
        ans = number % 10;
        number /= 10;
        dist--;
      }
      System.out.println(ans);
    }
  }
}
