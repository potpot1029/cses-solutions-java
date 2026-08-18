import java.util.Arrays;
import java.util.Scanner;

public class ChessboardAndQueens {

  static int ans = 0;

  static boolean validPos(int r, int c) {
    return r >= 0 && r < 8 && c >= 0 && c < 8;
  }

  static void placeQueen(int r, int c, char[][] chessboard) {
    // horizontal + vertical
    for (int i = 0; i < 8; i++) {
      chessboard[r][i] = '*';
      chessboard[i][c] = '*';
    }
    // diagonal
    for (int i = 0; i < 8; i++) {
      if (validPos(r + i, c + i)) chessboard[r + i][c + i] = '*';
      if (validPos(r - i, c + i)) chessboard[r - i][c + i] = '*';
      if (validPos(r - i, c - i)) chessboard[r - i][c - i] = '*';
      if (validPos(r + i, c - i)) chessboard[r + i][c - i] = '*';
    }
  }

  static void copyChessboard(char[][] source, char[][] dest) {
    for (int i = 0; i < 8; i++) {
      dest[i] = Arrays.copyOf(source[i], source[i].length);
    }
  }

  static void bruteForce(int curRow, char[][] chessboard) {
    if (curRow == 8) {
      ans++;
      return;
    }

    for (int i = 0; i < 8; i++) {
      if (chessboard[curRow][i] == '.') {
        char[][] newChessboard = new char[8][8];
        copyChessboard(chessboard, newChessboard);
        placeQueen(curRow, i, newChessboard);
        bruteForce(curRow + 1, newChessboard);
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    char[][] initChessboard = new char[8][8];
    for (int i = 0; i < 8; i++) {
      String row = sc.next();
      for (int j = 0; j < 8; j++) {
        initChessboard[i][j] = row.charAt(j);
      }
    }

    bruteForce(0, initChessboard);

    System.out.println(ans);
  }
}
