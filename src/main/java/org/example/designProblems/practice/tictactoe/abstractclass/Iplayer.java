package org.example.designProblems.practice.tictactoe.abstractclass;

import java.util.Objects;

public abstract class Iplayer {
    protected String name;
    protected String email;

    public Iplayer(String name) {
        this.name = name;
    }

    public Iplayer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Iplayer iplayer = (Iplayer) o;
        return Objects.equals(email, iplayer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
