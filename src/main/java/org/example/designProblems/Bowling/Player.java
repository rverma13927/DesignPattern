package org.example.designProblems.Bowling;


import org.example.designProblems.Bowling.Exception.PlayerCreationException;

import javax.validation.constraints.NotNull;

public class Player {

    private int id;
    @NotNull
    private String name;
    private PlayerScoreBoard playerScoreBoard;
    // private constructor so nobody can initialize the player without name ,id and score
    private Player() {
    }

    public Player(int id, String name, PlayerScoreBoard playerScoreBoard)  {
//        this.validate(id,name,playerScoreBoard);
        this.id = id;
        this.name = name;
        this.playerScoreBoard = playerScoreBoard;
    }

    private void validate(int id, String name, PlayerScoreBoard playerScoreBoard) throws PlayerCreationException {
        String message="";
        if(id<=0)  message="Id cannot be less than 0!";
        else if( name ==null || "".equals(name)){
            message ="Name is not valid!";
        }
        else if(playerScoreBoard==null){
            message ="PLayerScoreBoard is not valid!";
        }
        if(!message.equals("")){
            throw new PlayerCreationException(message);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
