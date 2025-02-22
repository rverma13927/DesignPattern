package org.example.designProblems.practice.tictactoe.interfaces;

import org.example.designProblems.practice.tictactoe.abstractclass.Iplayer;

public interface IBoard<T> {

    boolean makeMove(T move);
    boolean win(Iplayer iplayer);


    boolean draw();
    void printBoard();

}
