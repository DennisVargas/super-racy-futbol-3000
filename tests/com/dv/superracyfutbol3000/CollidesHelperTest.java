package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollidesHelperTest {
    Cars carA;
    Cars carB;
    @BeforeAll
    public static void init(){
        Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE);

    }

    @BeforeEach
    void setup(){


    }

    @Test
    void testIsCarCarCollision() {
        //  Does carA hit car BCollidesHelper.CarCarCollide(carA, carB);
        //  carA next move hits carB curPosition.
        //  At most

        //  getting back an array list with the two resulting direction vectors would be useful
        Assertions.assertNotNull(CollidesHelper.CarCarCollides(carA, carB));
    }
}