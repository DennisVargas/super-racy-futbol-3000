package com.dv.superracyfutbol3000;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreKeeperTest {
    ScoreKeeper score_keeper;

    @BeforeEach
    void Setup(){
        score_keeper = new ScoreKeeper();
    }

    @Test
    void CreateScoreKeeper(){
        score_keeper = new ScoreKeeper();
        Assertions.assertNotNull(score_keeper);
    }

    @Test
    void GetRedScoreFromKeeper() {
        //  invalid score
        int score = -1;
        score = score_keeper.getRedScore();
        Assertions.assertEquals(0,score);
    }

    @Test
    void GetBlueScoreFromKeeper() {
        //  invalid score
        int score = -1;
        score = score_keeper.getBlueScore();
        Assertions.assertEquals(0,score);
    }

    @Test
    void SetRedScore(){
        // set score to 1
        score_keeper.setRedScore(1);
        int score;
        score = score_keeper.getRedScore();
        Assertions.assertEquals(1,score);
    }

    @Test
    void SetBlueScore(){
        // set score to 1
        score_keeper.setBlueScore(1);
        int score;
        score = score_keeper.getBlueScore();
        Assertions.assertEquals(1,score);
    }

    @Test
    void IncrementRedScore(){
        //  set score to 1
        score_keeper.setRedScore(1);
        //   increment by 1
        score_keeper.IncrementRedScore(1);
        //  assert redscore == 2
        int score = score_keeper.getRedScore();
        Assertions.assertEquals(2,score);
    }

    @Test
    void IncrementBlueScore(){
        //  set score to 1
        score_keeper.setBlueScore(1);
        //   increment by 1
        score_keeper.IncrementBlueScore(1);
        //  assert blue_score == 2
        int score = score_keeper.getBlueScore();
        Assertions.assertEquals(2,score);
    }

    @Test
    void IsRedWinner() {
        //  set red score to 1
        score_keeper.setRedScore(1);
        //  set blue to 0
        score_keeper.setBlueScore(0);

        boolean isWin = score_keeper.IsBlueWinner();
        isWin = score_keeper.IsRedWinner();
    }
}