package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.Vector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.abs;
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

    @Test
    void atantest() {
        //  atan2(y,x)
        double x, y,x_point,y_point,center_x,center_y;
        //  points in screen space
        x_point = 1280;
        y_point = 0;
        center_x = 640;
        center_y = 360;
        //  difference between point and center of screen
        x = x_point-center_x;
        y = center_y-y_point;

        double result_angle =Math.atan2(y,x);
        //  positive angle is a left turn
        //  neg angle is a right turn is closest
        if(result_angle < 0)
            result_angle += 2*PI;

        System.out.println( 180/PI*result_angle + " degrees");
        System.out.println(Math.atan2(y,x) + 2*Math.PI + " rads");

        double turn_direction = PI/4;
        double turn_increment = PI/16;

        double angle_diff = result_angle - turn_direction;
        double neg_result_angle = result_angle - 2*PI;
        System.out.println("angle diff to face point rads: "+angle_diff+" radians");
        System.out.println("angle diff to face point degs: "+180/PI*angle_diff+" degs");
        System.out.println("left turns till i get there: "+(angle_diff)/turn_increment+" radians");
        System.out.println("negative angle: " + 180/PI*neg_result_angle);
        System.out.println("angle diff to face point degs: "+180/PI*angle_diff+" degs");
        System.out.println("right turns till i get there: "+((int)abs(neg_result_angle)/turn_increment)+" radians");



        //  hoiw many PI/16 turns do i need to get there?

    }

}