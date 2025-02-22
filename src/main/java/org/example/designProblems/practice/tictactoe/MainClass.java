package org.example.designProblems.practice.tictactoe;

import org.example.designProblems.practice.tictactoe.abstractclass.Iplayer;
import org.example.designProblems.practice.tictactoe.enums.PlayerType;
import org.example.designProblems.practice.tictactoe.factory.PlayerFactory;
import org.example.designProblems.practice.tictactoe.interfaces.Game;

public class MainClass {

    public static void main(String[] args) {


        Iplayer player1 = PlayerFactory.createPlayer(PlayerType.TIC_TAC_TOE,  'X',"Alice");
        Iplayer player2 = PlayerFactory.createPlayer(PlayerType.TIC_TAC_TOE, 'O',"Bot" );
        Game game = new TicTacToe(player1,player2);
        game.start();

    }
}
