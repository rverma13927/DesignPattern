package org.example.designProblems.TicTacToe;



import org.example.designProblems.TicTacToe.enums.GameResult;
import org.example.designProblems.TicTacToe.model.Move;
import org.example.designProblems.TicTacToe.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class for tic-tac-toe game
 * This class contains start method from where execution of game start
 */
public class TicTacToeGame extends Game {
    private static int NUMBER_OF_PLAYER = 2;
    private final int gridSize;
    private final Player player1;
    private final Player player2;
    private Move move;
    List<Move> moveList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    /**
     * By default player 1 will make first move
     */
    private int whoseMove = 1;

    public TicTacToeGame(final Player player1, final Player player2, int gridSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.gridSize =gridSize;
    }

    @Override
    void start() {

        Board board = new Board(gridSize);

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                move = getInput();
                try {
                    GameResult result = board.makeMode(move);
                    board.printBoard();
                    if (GameResult.DRAW.equals(result)) {
                        System.out.println("Match is draw");
                        return;
                    } else if (GameResult.WINNER.equals(result)) {
                                    System.out.println("Player " + whoseMove + " win!");
                        return;
                    }
                    whoseMove = whoseMove == 1 ? 2 : 1;
                } catch (IllegalAccessException e) {
                    System.out.println(e.getMessage());
                    j--;
                }
            }
        }
    }

    private Move getInput() {
        System.out.println("Player " + whoseMove + ":Please enter row -> ");
        int row = scanner.nextInt();
        System.out.println("Player " + whoseMove + ":Please enter col -> ");
        int col = scanner.nextInt();
        return new Move(row, col, whoseMove == 1 ? player1 : player2);
    }
}
