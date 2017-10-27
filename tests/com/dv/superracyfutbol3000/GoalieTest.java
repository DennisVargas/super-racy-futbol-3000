package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.Vector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.newdawn.slick.AppGameContainer;

import static org.junit.jupiter.api.Assertions.*;

class GoalieTest{

    AppGameContainer app;
    Goalie goalie;
    Ball ball;
    long start_time, diff_time;
    int time_out, diff_seconds;

    @BeforeAll
    public static void init(){
        Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE);
    }

    @BeforeEach
    void setUp() {
        //  set timeout to 5 seconds
        time_out = 5;
////         red goalie in center of field
        goalie = new Goalie(300, 360, true);
        ball = new Ball();
    }

    @Test
    void testTrackBallYMid() {

        //  test when goalie current position is less than the middle
        goalie.setPosition(goalie.getX(), 187f);

        //  test ball y value equal 360 the goalie mid
        goalie.TrackBallSetDirection(new Vector(45f,360f), new Vector(-0.5f,0.5f));

        //  goalie should move in pos direction to get to mid point in this case
        assertEquals(1,goalie.getNext_direction().getY());

        //  test when goalie current position is greater than the middle
        goalie.setPosition(goalie.getX(), 480f);

        // test ball y = 360
        goalie.TrackBallSetDirection(new Vector(45f,360f), new Vector(-0.5f,0.5f));

        //  goalie should move in negative direction to get to mid point in this case
        assertEquals(-1,goalie.getNext_direction().getY());

    }
    @Test
    void testTrackBallYMax(){
        // test y value greateer than 360 goalie mid value
        goalie.TrackBallSetDirection(new Vector(45,450),new Vector(-0.5f,0.5f));
        //  test if goalie direction is positive
        assertEquals(1,goalie.getNext_direction().getY());
    }
    @Test
    void testTrackBallYMin() {
        //  test y value less than 360
        goalie.TrackBallSetDirection(new Vector(45,187), new Vector(-0.5f,0.5f));
        //  test if goalie direction is negative
        assertEquals(-1, goalie.getNext_direction().getY());
    }
    @Test
    void testBallImmediatelyStuck() {
        //  test for function that detects if the ball has not moved
//            Goalie()
        //  need a difference in time to measure for time out.
        start_time = System.nanoTime();
        diff_time = System.nanoTime() - start_time;
        diff_seconds = (int)diff_time / 1000000000;

        //  test if the ball is detected as stuck before timeout
//        System.out.println("Stuck < " + goalie.getIs_stuck_time_out() + ": "
//                + assertFalse(goalie.IsBallStuck(ball.getPosition(), diff_seconds)))
    }
    @Test
    void testBallStuckAfterTimeOut() {
        //  ball registers the timer for stuck
        assertFalse(goalie.IsBallStuck(ball.getPosition(), 0));
        //  test the timer after 5 seconds should be true
        //  timer resets
        assertTrue(goalie.IsBallStuck(ball.getPosition(), 5));

        //  Next call should be false since ball was reset after the first.
        assertFalse(goalie.IsBallStuck(ball.getPosition(),6));
    }
    @Test
    void testBallStuckAfterMoving() {
        //  ball is in the same position timer starts
        assertFalse(goalie.IsBallStuck(ball.getPosition(), 0));
        // ball moves
        ball.setPosition(1f,1f);
        //  timer shuold be reset from first call
        assertFalse(goalie.IsBallStuck(ball.getPosition(), 1));
        //  This is five seconds since move should reprot stuck
        assertTrue(goalie.IsBallStuck(ball.getPosition(), 5));
    }
    @Test
    void testUpdateGoalie() {
        //  test the resulting value of the goalies position after the translate vector is applied

    }
    @Test
    void testGenerateNextGoalieTranslationSameDirection(){
        Vector trans_before, trans_after;
        goalie.setNext_direction(new Vector(0f,1f));
        trans_before = goalie.getNext_translation();

        //  test the results of the Generate Next Translation
        goalie.GenerateNextTranslation();
        trans_after = goalie.getNext_translation();


        // will succeed when the resulting vector Y
        // component differs from the original after translation
        Assertions.assertNotEquals(trans_before.getY(),trans_after.getY(),
                "\nGoalie Failed Generating Translation in Y\n");

    }

    @Test
    void testGenerateNextGoalieTranslationChangeDirection1to0(){
        Vector trans_before, trans_after;
        //  set direction to positive y
        goalie.setNext_direction(new Vector(0f,1));
        //  generate a next translation for update
        goalie.GenerateNextTranslation();
        trans_before = goalie.getNext_translation();

        //  change the direction to zero
        goalie.setNext_direction(new Vector(0,0));

        // Generate Next Translation of direction 0
        goalie.GenerateNextTranslation();
        //
        trans_after = goalie.getNext_translation();

        // will succeed when the resulting vector Y
        // component equals the original after translation
        Assertions.assertEquals(0,trans_after.getY(),
                "\nGoalie Failed Generating Translation in Y\n");

    }

    @Test
    void testUpdateGoaliePositionMoveUp() {
        Vector prev_goalie_position = goalie.getPosition();
        //  Set a moving direction for goalie
        goalie.setNext_direction(new Vector(0,1));
        //  Generate a translation
        goalie.GenerateNextTranslation();
        //  Apply translation
        goalie.UpdateGoaliePosition();
        //  Check if new position is in the positive y direction away from previous position
        //  Check they are not the same
        Vector new_goalie_position = goalie.getPosition();
        if ((new_goalie_position.getY() - prev_goalie_position.getY()) > 0)
            assertEquals(1,1);
        else {
            assertEquals(1, 0, "\nFailed testUpdateGoaliePositionMoveUp" +
                    " Goalie Didn't move in positive Direction");
        }
    }

    @Test
    void testUpdateGoaliePositionMoveDwn() {
        Vector prev_goalie_position = goalie.getPosition();
        //  Set a moving direction for goalie
        goalie.setNext_direction(new Vector(0,-1));
        //  Generate a translation
        goalie.GenerateNextTranslation();
        //  Apply translation
        goalie.UpdateGoaliePosition();
        //  Check if new position is in the positive y direction away from previous position
        //  Check they are not the same
        Vector new_goalie_position = goalie.getPosition();
        if (( prev_goalie_position.getY() - new_goalie_position.getY()) > 0)
            assertEquals(1,1);
        else {
            assertEquals(-1, 0, "\nFailed testUpdateGoaliePositionMoveDwn" +
                    " Goalie Didn't move in negative Direction");
        }
    }

    @Test
    void testUpdateGoaliePositionLimitsYmin() {
        //  set goalie at its minimum y position of 192
        goalie.setPosition(new Vector(goalie.getX(), 192f));

        //  save the goalie position
        Vector prev_goalie_position = goalie.getPosition();

        //  Set a moving direction for goalie in the negative minimum direction
        goalie.setNext_direction(new Vector(0,-1));

        //  Generate a translation
        goalie.GenerateNextTranslation();

        //  Apply translation
        goalie.UpdateGoaliePosition();

        //  Check if new position is in the yMin position still
        //  Check previous and new position are the same
        Vector new_goalie_position = goalie.getPosition();
        if (( prev_goalie_position.getY() - new_goalie_position.getY()) == 0)
            assertEquals(1,1);
        else {
            assertEquals(-1, 0, "\nFailed testUpdateGoaliePositionMovedLimitsYmin" +
                    " Goalie moved past the Y-Min limit");
        }
    }

    @Test
    void testUpdateGoaliePositionLimitsYmax() {
        //  set goalie at its maximum y position of 512
        goalie.setPosition(new Vector(goalie.getX(), 512f));

        //  save the goalie position
        Vector prev_goalie_position = goalie.getPosition();

        //  Set a moving direction for goalie in the positive direction
        goalie.setNext_direction(new Vector(0,1));

        //  Generate a translation
        goalie.GenerateNextTranslation();

        //  Apply translation
        goalie.UpdateGoaliePosition();

        //  Check if new position is in the yMin position still
        //  Check previous and new position are the same
        Vector new_goalie_position = goalie.getPosition();
        if (( prev_goalie_position.getY() - new_goalie_position.getY()) == 0)
            assertEquals(1,1);
        else {
            assertEquals(1, 0, "\nFailed testUpdateGoaliePositionMovedLimitsYmax" +
                    " Goalie moved past the Y-Max limit");
        }

    }

    @Test
    void testUpdateGoaliePositionZeroTrans() {
        goalie.setNext_direction(new Vector(0,1));
        goalie.GenerateNextTranslation();
        goalie.UpdateGoaliePosition();
        goalie.setNext_direction(new Vector(0,0));
        goalie.GenerateNextTranslation();
        float prev_Y = goalie.getY();
        goalie.UpdateGoaliePosition();
        float after_y = goalie.getY();
        assertEquals(prev_Y, after_y);
    }

    @Test
    void testTrackBallSameY() {
        //  test if when the ball y and the goalie y are the same the direction moved is zero
        //  goalie shoudl then stay in place
        //  this could also solve the pregame jitters of the goalies
        //  no they actually jitter in place at the moment 10/25/17

        //  couldn't goalie just take on the balls y direction?
        //  this is super fast though but everyone is paused.
        goalie.setPosition(goalie.getX(),400);
        Vector result =
                goalie.TrackBallSetDirection(new Vector (120f,400), new Vector(-1,0));

        //  when the goalieY == ballY the direction of movement should evaluate zero
        assertEquals(0, result.getY());
    }
}