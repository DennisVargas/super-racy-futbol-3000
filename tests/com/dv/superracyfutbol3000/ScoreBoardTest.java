package com.dv.superracyfutbol3000;

import jig.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ScoreBoardTest {

    ScoreBoard score_board;
    @BeforeEach
    void setup(){
        score_board = new ScoreBoard();
    }
    @BeforeAll
    public static void init(){
        Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE);
    }
    @Test
    void CreateScoreBoard() {
        score_board = new ScoreBoard();
        Assertions.assertNotNull(score_board);
    }

    @Test
    void SetRedScoreLeastSigDigit(){
        score_board.setRedScore(2);
        Assertions.assertEquals(2,score_board.getRedLeastSigValue());
        Assertions.assertEquals(0,score_board.getRedMostSigValue());
    }

    @Test
    void SetRedScoreMostSigDigit(){
        score_board.setRedScore(14);
        Assertions.assertEquals(1, score_board.getRedMostSigValue());
        Assertions.assertEquals(4,score_board.getRedLeastSigValue());
    }

    @Test
    void SetBlueScoreLeastSigDigit(){
        score_board.setBlueScore(2);
        Assertions.assertEquals(2,score_board.getBlueLeastSigValue());
        Assertions.assertEquals(0,score_board.getBlueMostSigValue());
    }

    @Test
    void SetBlueScoreMostSigDigit(){
        score_board.setBlueScore(14);
        Assertions.assertEquals(1, score_board.getBlueMostSigValue());
        Assertions.assertEquals(4,score_board.getBlueLeastSigValue());
    }

    @Test
    void SetRedMostSigImage(){
        score_board.setRedScore(4);
        int return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(0, return_value);
        score_board.setRedScore(14);
        return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(1, return_value);
        score_board.setRedScore(24);
        return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(2, return_value);
        score_board.setRedScore(34);
        return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(3, return_value);
        score_board.setRedScore(44);
        return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(4, return_value);
        score_board.setRedScore(54);
        return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(5, return_value);
        score_board.setRedScore(64);
        return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(6, return_value);
        score_board.setRedScore(74);
        return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(7, return_value);
        score_board.setRedScore(84);
        return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(8, return_value);
        score_board.setRedScore(94);
        return_value = score_board.setRedScoreMostSigImage();
        Assertions.assertEquals(9, return_value);
    }

    @Test
    void SetLeastSigImage() {
        score_board.setRedScore(50);
        int return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(0, return_value);
        score_board.setRedScore(11);
        return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(1, return_value);
        score_board.setRedScore(22);
        return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(2, return_value);
        score_board.setRedScore(33);
        return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(3, return_value);
        score_board.setRedScore(44);
        return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(4, return_value);
        score_board.setRedScore(55);
        return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(5, return_value);
        score_board.setRedScore(66);
        return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(6, return_value);
        score_board.setRedScore(77);
        return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(7, return_value);
        score_board.setRedScore(88);
        return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(8, return_value);
        score_board.setRedScore(99);
        return_value = score_board.setRedScoreLeastSigImage();
        Assertions.assertEquals(9, return_value);
    }

    @Test
    void testInitDigitEntities() {
        score_board.InitDigitEntities();
        Assertions.assertNotNull(score_board.blue_least_sig);
        Assertions.assertNotNull(score_board.red_least_sig);
        Assertions.assertNotNull(score_board.blue_most_sig);
        Assertions.assertNotNull(score_board.red_most_sig);
    }

    @Test
    void testDigitPosition(){
        //  digits should be in constant location
        score_board.InitDigitEntities();
        Entity blue_least = score_board.getBlueLeastSigEntity();
        Assertions.assertEquals(1240, blue_least.getX());
        Assertions.assertEquals(69, blue_least.getY());
        Entity blue_most = score_board.getBlueMostSigEntity();
        Assertions.assertEquals(1206, blue_most.getX());
        Assertions.assertEquals(69, blue_most.getY());
        Entity red_least = score_board.getRedLeastSigEntity();
        Assertions.assertEquals(81, red_least.getX());
        Assertions.assertEquals(69, red_least.getY());
        Entity red_most = score_board.getRedMostSigEntity();
        Assertions.assertEquals(47, red_most.getX());
        Assertions.assertEquals(69, red_most.getY());
    }


}