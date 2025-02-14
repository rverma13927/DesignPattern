package org.example.designProblems.Bowling;

public class PlayerScoreBoard {
     private Integer score;

     public PlayerScoreBoard() {
          this.score = 0;
     }

     public Integer getScore() {
          return score;
     }

     public void addScore(Integer score) {
          this.score +=  score;
     }

}
