package org.example.designProblems.practice.tictactoe;

import org.example.designProblems.practice.tictactoe.abstractclass.Iplayer;

public class TictactoeIplayer extends Iplayer {

    char symbol;

    public TictactoeIplayer(String name, char symbol) {
        super(name);
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}

