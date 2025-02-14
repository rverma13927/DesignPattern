package org.example.designProblems.TicTacToe;

import TicTacToe.model.Player;

public class Main {

    public static void main(String[] args) {
        TicTacToeGame ticTacToeGame= new TicTacToeGame(new Player(1,"Deepak"),new Player(2,"Rahul"),3);
        ticTacToeGame.start();
    }
}
