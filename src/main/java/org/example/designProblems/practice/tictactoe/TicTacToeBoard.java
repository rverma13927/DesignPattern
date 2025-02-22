package org.example.designProblems.practice.tictactoe;

import org.example.designProblems.practice.tictactoe.abstractclass.Iplayer;
import org.example.designProblems.practice.tictactoe.enums.Symbol;
import org.example.designProblems.practice.tictactoe.exception.InvalidMoveException;
import org.example.designProblems.practice.tictactoe.interfaces.IBoard;

import java.util.Arrays;

public class TicTacToeBoard implements IBoard<TicTacToeMove> {
    int NUMBER_OF_PLAYER =2;

    private final String INVALID_SYMBOL = "Invalid symbol";
    private final String INVALID_MOVE = "Invalid move";
    Integer SIZE =3;
    char board[][];

    public TicTacToeBoard() {
        this.board = new char [SIZE][SIZE];

        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++)board[i][j]='-';
        }
    }

    @Override
    public boolean makeMove(TicTacToeMove move) {
            int x= move.getX();
            int y = move.getY();
            TictactoeIplayer tictactoeIplayer  = (TictactoeIplayer) move.getIplayer();
            if(!validSymbol(tictactoeIplayer.getSymbol()))
                throw new InvalidMoveException(INVALID_SYMBOL);

            if(board[x][y]== Symbol.EMPTY.getValue() ){
                  board[x][y]= tictactoeIplayer.getSymbol();
                  return win(move.getIplayer());

            }else
                throw new InvalidMoveException(INVALID_MOVE);
    }

    private boolean validSymbol(char userSymbol) {
        return Arrays.stream(Symbol.values()).anyMatch(symbol -> symbol.getValue() == userSymbol);
    }

    @Override
    public boolean win(Iplayer iplayer) {
        TictactoeIplayer tictactoeIplayer = (TictactoeIplayer) iplayer;
        // ROW
        for(int i=0;i<SIZE;i++){
            boolean win = true;
            for(int j=0;j<SIZE;j++){
                if(tictactoeIplayer.getSymbol() != (board[i][j])){
                    win = false;
                    break;
                }
            }
            if(win)return win;
        }

        //COL
        for(int i=0;i<SIZE;i++){
            boolean win = true;
            for(int j=0;j<SIZE;j++){
                if(tictactoeIplayer.getSymbol() != (board[j][i])){
                    win = false;
                    break;
                }
            }
            if(win)return win;
        }
        //DIAGONAL

        int i=0,j=0;
        boolean win = true;
        while(i<SIZE && j<SIZE){
             if(board[i][j]!=tictactoeIplayer.getSymbol()){
                win = false;
             }
             i++;
             j++;
        }
        if(win)return win;
        i=SIZE-1;
        j=SIZE-1;
        while(i>=0 && j>=0){
            if(board[i][j]!=tictactoeIplayer.getSymbol()){
                win = false;
            }
            i--;
            j--;
        }
        return win;
    }

    @Override
    public boolean draw() {
        for(int i=0;i<SIZE;i++) {
            for (int j = 0; j < SIZE; j++) {
                if (Symbol.EMPTY.getValue() == (board[i][j]))
                    return false;
            }
        }
        return true;
    }

    @Override
    public void printBoard() {
        for(int i=0;i<SIZE;i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }

    }


}
