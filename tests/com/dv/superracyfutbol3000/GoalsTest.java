package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoalsTest {
    Goals red_goal;
    Goals blue_goal;
    @BeforeAll
    public static void init() {
        Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE);
    }
    @BeforeEach
    void setUp() {
        red_goal = new Goals(true);
        blue_goal = new Goals(false);
        red_goal.setGoalRectangle();
        blue_goal.setGoalRectangle();
    }

    @Test
    void CreateRedGoal() {
        boolean isRed = true;
        Goals goal = new Goals(isRed);
        Assertions.assertTrue(goal.isRed());
    }

    @Test
    void CreateBlueGoal() {
        //  blue goals are a false isRed goal
        boolean isRed = false;
        Goals goal = new Goals(isRed);
        Assertions.assertFalse(goal.isRed());
    }

    @Test
    void CreateRedGoalAtRed() {
        boolean isRed = true;
        Goals goal = new Goals(isRed);
        goal.setGoalRectangle();
        Assertions.assertEquals(goal.getRedGoalCENTER().getX(), goal.getRectangle().getCenterX());
        Assertions.assertEquals(goal.getRedGoalCENTER().getY(), goal.getRectangle().getCenterY());
    }

    @Test
    void CreateBlueGoalAtBlue() {
        //blue_goal = new Goals(false);
        blue_goal.setGoalRectangle();
        Assertions.assertEquals( blue_goal.getBlueGoalCENTER().getX(),  blue_goal.getRectangle().getCenterX());
        Assertions.assertEquals( blue_goal.getBlueGoalCENTER().getY(),  blue_goal.getRectangle().getCenterY());
    }

    @Test
    void testIsGoalScoreOnRedCenter(){
        //  create a red goal
        Vector ball_pos = new Vector(32,352);
        float radius = 20f;
        //  Returns a score of 1 if the position is a goal within that goal
        Assertions.assertEquals(1, red_goal.IsGoal(ball_pos, radius));
    }

    @Test
    void testIsGoalScoreOnBlueCenter(){
        //  create a blue goal
        Vector ball_pos = new Vector(1248,352);
        float radius = 20f;
        //  Returns a score of 1 if the position is a goal within that goal
        Assertions.assertEquals(1,blue_goal.IsGoal(ball_pos, radius));
    }

    @Test
    void testIsGoalScoreOnRedNotGoal() {
        //  width plus the center point should bush the ball out
        Vector ball_pos = new Vector(red_goal.getRectangle().getWidth()+red_goal.getRectangle().getCenterX(),
                                        red_goal.getRectangle().getHeight()+red_goal.getRectangle().getCenterY());
        float radius = 20f;
        Assertions.assertEquals(0, red_goal.IsGoal(ball_pos, radius));
    }
    @Test
    void testIsGoalScoreOnBlueNotGoal() {
        Vector ball_pos = new Vector(blue_goal.getRectangle().getWidth()+blue_goal.getRectangle().getCenterX(),
                blue_goal.getRectangle().getHeight()+blue_goal.getRectangle().getCenterY());
        float radius = 20f;
        Assertions.assertEquals(0, blue_goal.IsGoal(ball_pos, radius));
    }

    @Test
    void testIsGoalScoreOnRedXEdgeGoal() {
        Vector ball_pos = new Vector(-blue_goal.getRectangle().getWidth()/2+blue_goal.getRectangle().getCenterX(),
                blue_goal.getRectangle().getHeight()/2+blue_goal.getRectangle().getCenterY());
        float radius = 20f;
        Assertions.assertEquals(0, blue_goal.IsGoal(ball_pos, radius));
    }

}