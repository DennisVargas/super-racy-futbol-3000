package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Assertions.assertTrue(goal.isRed());
    }

    @Test
    void CreateBlueGoalAtBlue() {

    }
}