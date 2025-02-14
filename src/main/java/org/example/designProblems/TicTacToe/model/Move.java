package org.example.designProblems.TicTacToe.model;

public class Move {
    private final int col;
    private final int row;
    private final Player player;

    public Move(int row, int col, Player player) {
        this.col = col;
        this.row = row;
        this.player = player;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Player getPlayer() {
        return player;
    }
}
