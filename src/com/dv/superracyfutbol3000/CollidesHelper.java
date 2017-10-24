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
        //  from: ((x-c)^2)/b^2 +((y-d)^2)/a^2 = 1
        //  q = a^2/b^2
        //  a>0 and q>0
        //  (x-c_x)^2 + q(y-c_y)^2 = a^2

        center_x = e.getCenterX();
        center_y = e.getCenterY();
        radius_x = e.getRadius1();
        radius_y = e.getRadius2();
        radius_x_sq = radius_x*radius_x;
        radius_y_sq = radius_y*radius_y;
        x_center_diff = (v.getX()-center_x)*(v.getX()-center_x);
        y_center_diff = (v.getY()-center_y)*(v.getY()-center_y);
        float x_in_ellipse = x_center_diff / radius_x_sq;
        float y_in_ellipse = y_center_diff / radius_y_sq;
        float q = radius_y_sq/radius_x_sq;

        //  calculate the distance to the center of the ellipse
        float in_ellipse_calc = (x_in_ellipse + y_in_ellipse);
        float simp_in_ellipse_calc = (x_center_diff)+q*(y_center_diff);
        //System.out.println("Distance to center of ellipse: " + d_center);

        //  Is v in the rectangle
        if (rect.contains(v.getX(), v.getY())) {
            if(isWallDebug)System.out.println("RRRREctangle COLLIDE");
            return false;
        } else {
//            if(sqrt(simp_in_ellipse_calc) <= sqrt(radius_y_sq)){
//                if(isWallDebug)System.out.println("Simple ellipse Point Test: "
//                        +simp_in_ellipse_calc + " < radius_x^2"+(radius_y_sq));
//                return false;
//            }
//              if v isn't in the rectangle. Is v in ellipse.
            if ((in_ellipse_calc <= 1.030f)) {
                if(isWallDebug)System.out.println("ellipse Point Test: "+in_ellipse_calc);
                if(isWallDebug)System.out.println("Simple ellipse Point Test: "
                        +simp_in_ellipse_calc + " < radius_x^2"+radius_x_sq);
                return false;
            }

            //  v isn't in the rectange or the ellipse
            //  therefore its a wall collision.
            if(isWallDebug)System.out.println("Out of Ellipse");
            if(isWallDebug)System.out.println("xterm: "+x_in_ellipse);
            if(isWallDebug)System.out.println("yterm: "+y_in_ellipse);
            return true;
        }
    }
    public static void CheckWorldCollisions(Teams teams, Ball ball, Ellipse left_boundary, Ellipse right_boundary,
                                            Rectangle center_boundary){

    }
//  runs through all the collision types
    public static CollisionType CheckCollisions(Vector v, Ellipse ellipse, Rectangle rect) {
        //  Check collide with wall
        //  Check collide with goalie
        //  Check collide with ball
        //  Check collide with cars
        //  Check collide with goals
        if( WallCollide(v, ellipse, rect))
            return CollisionType.Wall;
        else//  more else if to come for other collisions
            return CollisionType.None;
    }

    //  testing main for collideshelper
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
