import java.util.*;

public class TicTacToeAI {
    static char[][] board = {
        {' ', ' ', ' '},
        {' ', ' ', ' '},
        {' ', ' ', ' '}
    };

    static char human = 'X';
    static char ai = 'O';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printBoard();

        while (true) {
            // Human move
            System.out.print("Enter your move (row and col: 0-2): ");
            int row = sc.nextInt();
            int col = sc.nextInt();
            if (board[row][col] == ' ') {
                board[row][col] = human;
            } else {
                System.out.println("Invalid move! Try again.");
                continue;
            }

            if (isWinner(human)) {
                printBoard();
                System.out.println("You win!");
                break;
            }
            if (isFull()) {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            // AI move
            Move bestMove = findBestMove();
            board[bestMove.row][bestMove.col] = ai;
            System.out.println("AI plays: " + bestMove.row + " " + bestMove.col);

            printBoard();

            if (isWinner(ai)) {
                System.out.println("AI wins!");
                break;
            }
            if (isFull()) {
                System.out.println("It's a draw!");
                break;
            }
        }
        sc.close();
    }

    static void printBoard() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println();
    }

    static boolean isWinner(char player) {
        // Check rows, cols, diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) return true;
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;
        return false;
    }

    static boolean isFull() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ') return false;
        return true;
    }

    static int minimax(int depth, boolean isMax) {
        if (isWinner(ai)) return 10 - depth;
        if (isWinner(human)) return depth - 10;
        if (isFull()) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = ai;
                        best = Math.max(best, minimax(depth + 1, false));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = human;
                        best = Math.min(best, minimax(depth + 1, true));
                        board[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    static Move findBestMove() {
        int bestVal = Integer.MIN_VALUE;
        Move bestMove = new Move(-1, -1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = ai;
                    int moveVal = minimax(0, false);
                    board[i][j] = ' ';
                    if (moveVal > bestVal) {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    static class Move {
        int row, col;
        Move(int r, int c) {
            row = r; col = c;
        }
    }
}
