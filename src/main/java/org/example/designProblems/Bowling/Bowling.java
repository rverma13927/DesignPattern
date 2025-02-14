package org.example.designProblems.Bowling;

import java.util.List;

public class Bowling extends Game{
    private List<Player> players;
    private static final Integer TOTAL_ROUND =5;
    private Integer currentRound=0;

    public Bowling(String gameName) {
        super(gameName);
    }

    @Override
    void start() {

    }
}
