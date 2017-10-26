package com.dv.superracyfutbol3000;

import jig.Entity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoalsTest {

    @BeforeAll
    public static void init() {
        Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE);
    }
    @BeforeEach
    void setUp() {

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
        Assertions.assertEquals(goal.getRedGoalCENTER().getX(), goal.getRectangle().getX());
        Assertions.assertEquals(goal.getRedGoalCENTER().getY(), goal.getRectangle().getY());
    }

    @Test
    void CreateBlueGoalAtBlue() {
        boolean isRed = false;
        Goals goal = new Goals(isRed);
        goal.setGoalRectangle();
        Assertions.assertEquals(goal.getBlueGoalCENTER().getX(), goal.getRectangle().getX());
        Assertions.assertEquals(goal.getBlueGoalCENTER().getY(), goal.getRectangle().getY());
    }

    

}