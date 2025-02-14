package org.example.designProblems.TicTacToe;


import org.example.designProblems.TicTacToe.enums.GameResult;
import org.example.designProblems.TicTacToe.model.Move;

public class Board {
    private final int n;
    private int board[][];
    private int filled = 0;
    private int rowSum[];
    private int colSum[];
    private int diagonalSum;
    private int reverseDiagonalSum;

    Board(int n) {
        this.n = n;
        this.board = new int[n][n];
        this.rowSum = new int[n];
        this.colSum = new int[n];
        this.diagonalSum = 0;
        this.reverseDiagonalSum = 0;
        initialize();
    }

    private void initialize() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) board[i][j] = 0;
            rowSum[i] = 0;
            colSum[i] = 0;
            diagonalSum = 0;
            reverseDiagonalSum = 0;
        }
    }

    public int[][] getBoard() {
        return board;
    }

    /**
     * Make move makes the move of the player and return 1 if he is a winner
     * @param move contains row, col and player who is making the current move
     * @return -1 if board is filled and draw , 1 if we get the current player as winner ,0 if some places are remaining and to be filled
     * @throws IllegalAccessException if move is illegal
     */

    public GameResult makeMode(Move move) throws IllegalAccessException {

        int row = move.getRow();
        int col = move.getCol();

        if (row < 0 || row > n || col < 0 || col > n) throw new IllegalAccessException("Enter valid move!");
        if (filled != 9 && board[row][col] != 0) throw new IllegalAccessException("This place is already filled!");


        int value = move.getPlayer().getId() == 1 ? 1 : -1;
        board[row][col] = value;
        filled+=1;

        rowSum[row] += value;
        colSum[col] += value;
        if (row == col)
            diagonalSum += value;
        if (row + col == n - 1)
            reverseDiagonalSum += value;

        if (rowSum[row] == n || colSum[col] == n || diagonalSum == n || reverseDiagonalSum == n) {
            return GameResult.WINNER;
        }
        if (filled == 9) {
            return GameResult.DRAW;
        }
        return GameResult.GAME_NOT_COMPLETED;
    }

    public void printBoard() {

        System.out.println("---------------------------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((board[i][j] == 1 ? "X" : (board[i][j] == -1 ? "O " : "-")) + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }
}
