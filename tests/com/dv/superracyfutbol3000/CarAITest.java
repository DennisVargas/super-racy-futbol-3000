package com.dv.superracyfutbol3000;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CarAITest {
    CarAI carAI;
    @BeforeEach
    void setup(){
        carAI= new CarAI();
    }

    @Test
    void testSetAndGetPlayState(){
        carAI.setPlayState(new PlayState(0));
        Assertions.assertNotNull(carAI.getPlayState());
    }

    @Test
    void testGenerateGotoBall() {
        CarAI carAI = new CarAI();
        //  Generate Move Order for getting to the ball.
//        carAI.GenerateGotoBall();
    }

    @Test
    void decideMove() {
    }

    @Test
    void whereBoost() {
    }

    @Test
    void getMyGoal() {
    }

    @Test
    void getTheirGoal() {
    }

}