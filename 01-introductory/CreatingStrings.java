import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CreatingStrings {

  static List<String> solution = new ArrayList<>();

  static void generateStrings(int n, int curPos, char[] curStr, int[] freq) {
    if (n == curPos) {
      solution.add(String.valueOf(curStr));
      return;
    }

    for (int i = 0; i < 26; i++) {
      if (freq[i] > 0) {
        char[] newStr = Arrays.copyOf(curStr, curStr.length);
        newStr[curPos] = (char) ('a' + i);
        int[] newFreq = Arrays.copyOf(freq, freq.length);
        newFreq[i]--;
        generateStrings(n, curPos + 1, newStr, newFreq);
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    String s = sc.next();
    int n = s.length();

    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }

    generateStrings(n, 0, new char[n], freq);

    System.out.println(solution.size());
    for (String el : solution) {
      System.out.println(el);
    }
  }
}
