package org.example.designProblems.practice.tictactoe;

import org.example.designProblems.practice.tictactoe.abstractclass.Iplayer;
import org.example.designProblems.practice.tictactoe.interfaces.Game;

import java.util.Scanner;

public class TicTacToe implements Game {

    private final TicTacToeBoard ticTacToeBoard;
    private final Iplayer iplayer1;
    private final Iplayer iplayer2;

    private Iplayer  cur;
    private Iplayer next;

    public TicTacToe( Iplayer iplayer1, Iplayer iplayer2) {
        this.ticTacToeBoard = new TicTacToeBoard();
        this.iplayer1 = iplayer1;
        this.iplayer2 = iplayer2;
    }

    @Override
    public void start() {

        cur= iplayer1;
        next = iplayer2;

        Scanner sc = new Scanner(System.in);

        while(true){
            ticTacToeBoard.printBoard();
            System.out.print("Enter x: ");
            int x = sc.nextInt();  // Reads a token (usually a word)

            System.out.print("Enter y: ");
            int y = sc.nextInt();
            if(x<0 || y<0 || x>2 || y>2){
                System.out.println("Invalid coordinates");
                continue;
            }

            TicTacToeMove ticTacToeMove  = new TicTacToeMove(x,y,cur);

            boolean result = ticTacToeBoard.makeMove(ticTacToeMove);

            if(result){
                System.out.println(cur.getName()+ " won the game");
                break;
            }else  if(ticTacToeBoard.draw()){
                System.out.println("Draw");
                break;
            }
            Iplayer temp = cur;
            cur =next;
            next= temp;
        }

    }
}
