package org.example.designProblems.practice.tictactoe.factory;

import org.example.designProblems.practice.tictactoe.TictactoeIplayer;
import org.example.designProblems.practice.tictactoe.abstractclass.Iplayer;
import org.example.designProblems.practice.tictactoe.enums.PlayerType;

public class PlayerFactory {

    public static  Iplayer createPlayer(PlayerType playerType, char symbol,String name){
        if(playerType.equals(PlayerType.TIC_TAC_TOE)){
            return new TictactoeIplayer(name,symbol);
        }else{
            return null;
        }
    }
}
