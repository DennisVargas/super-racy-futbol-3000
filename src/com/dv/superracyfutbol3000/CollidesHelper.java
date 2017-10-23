package com.dv.superracyfutbol3000;

import jig.Vector;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import static com.dv.superracyfutbol3000.SuperRacyFutbol3000.isWallDebug;
import static java.lang.StrictMath.sqrt;


public abstract class CollidesHelper {
    enum CollisionType {Wall, Goalie, Car, Ball, Goal, None}


    //  takes the x and y of the car and subtract the center x and center y of an ellipse
    //  this ellipse will be on the far goal end of the field.
    //  when the car crosses this threshold return true
    private static boolean WallCollide(Vector v, Ellipse e, Rectangle rect) {
        // calculate if r < sqrt((x-c_x)^2+(y-c_y)^2)
        // then no collision
        //  so do the opposite
        //  decide in play game state which ellipse it needs to pass in.
        //  if its outside of the ellipse then test the rectangle
        if(isWallDebug)System.out.println("vector_X: "+v.getX()+" vector_y: "+v.getY());
        float center_x, center_y, radius_x, radius_y,x_center_diff, y_center_diff, radius_x_sq, radius_y_sq;
        //  simpler formulas
        //  from: ((x-c)^2)/a^2 +((y-d)^2)/b^2 = 1
        //  q = a^2/b^2
        //  a>0 and q>0
        //  (x-c_x)^2 + q(y-c_y)^2 = a^2

        center_x = e.getCenterX();
        center_y = e.getCenterY();
        radius_x = e.getRadius1();
        radius_y = e.getRadius2();
        radius_x_sq = radius_x*radius_x;
        radius_y_sq = radius_y*radius_y;
        x_center_diff = (x-center_x)*(x-center_x);
        y_center_diff = (y-center_y)*(y-center_y);
        float x_in_ellipse = x_center_diff / radius_x_sq;
        float y_in_ellipse = y_center_diff / radius_y_sq;

        //  calculate the distance to the center of the ellipse
        double d_center_circ = sqrt((x - center_x) * (x - center_x) + (y - center_y) * (y - center_y));
        double in_ellipse = (x_in_ellipse + y_in_ellipse);
        //System.out.println("Distance to center of ellipse: " + d_center);
        if(isWallDebug)System.out.println("ellipse Point Test: "+in_ellipse);
        if (rect.contains(x, y)) {
            if(isWallDebug)System.out.println("rectangle");
            return false;
        } else {

            if ((in_ellipse > 1f)) {
//                System.out.println("radiusX:" + radius_x + " <" + d_center);

                if(isWallDebug)System.out.println("Out of Ellipse");
                if(isWallDebug)System.out.println("xterm: "+x_in_ellipse);
                if(isWallDebug)System.out.println("yterm: "+y_in_ellipse);
                return true;
            }
            return false;
        }

    }

//  runs through all the collision types
    public static CollisionType CheckCollisons(float newX, float newY, Ellipse ellipse, Rectangle rect) {
        //  Check collide with wall
        //  Check collide with goalie
        //  Check collide with ball
        //  Check collide with cars
        //  Check collide with goals
        if( WallCollide(newX, newY, ellipse, rect))
            return CollisionType.Wall;
        else//  more else if to come for other collisions
            return CollisionType.None;
    }

//    public static void main(String args[]){
//        while(true){
//            if(WallCollide(170,450,127,400,50,500)){
//                System.out.println("It's out circle Collision ");
//            }else{
//                System.out.println("It's in circle GOOD");
//            }
//        }
//    }

}
