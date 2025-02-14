package org.example.designProblems.Bowling;

public abstract class Game {
    private final String gameName;
    abstract void start();

    public Game(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

}
