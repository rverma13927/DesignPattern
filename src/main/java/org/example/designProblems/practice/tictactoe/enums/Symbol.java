package org.example.designProblems.practice.tictactoe.enums;

public enum Symbol {
    EMPTY('-'),
    CROSS('X'),
    CIRCLE('O');

    private final char value; // Or private final Character value;

    Symbol(char value) {
        this.value = value;
    }

    public char getValue() { // Or public Character getValue()
        return value;
    }
}
