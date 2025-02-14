package org.example.designProblems.TicTacToe.model;

public class Player {
    private final Integer id;
    private final String name;

    public Player(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
