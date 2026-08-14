import java.util.Scanner;

public class Repetition {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String dna = sc.nextLine();

    char lastChar = 'X';
    int curLen = 0, maxLen = 0;
    for (char c : dna.toCharArray()) {
      if (c == lastChar) curLen++;
      else {
        curLen = 1;
        lastChar = c;
      }
      maxLen = Math.max(maxLen, curLen);
    }

    System.out.println(maxLen);
  }
}
