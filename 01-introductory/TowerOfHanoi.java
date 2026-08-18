import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TowerOfHanoi {

  static List<int[]> solution = new ArrayList<>();

  static void solveHanoi(int n, int start, int middle, int target) {
    if (n == 1) {
      solution.add(new int[] {start, target});
      return;
    }
    if (n == 2) {
      solution.add(new int[] {start, middle});
      solution.add(new int[] {start, target});
      solution.add(new int[] {middle, target});
      return;
    }

    solveHanoi(n - 1, start, target, middle);
    solution.add(new int[] {start, target});
    solveHanoi(n - 1, middle, start, target);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int n = sc.nextInt();
    solveHanoi(n, 1, 2, 3);

    System.out.println(solution.size());
    for (int[] move : solution) {
      System.out.println(move[0] + " " + move[1]);
    }
  }
}
