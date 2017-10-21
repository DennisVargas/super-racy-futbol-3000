package com.dv.superracyfutbol3000;

import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import static java.lang.StrictMath.sqrt;

public abstract class CollidesHelper {

    //  takes the x and y of the car and subtract the center x and center y of an ellipse
    //  this ellipse will be on the far goal end of the field.
    //  when the car crosses this threshold return true
    private static boolean WallCollide(float x, float y, Ellipse e, Rectangle rect) {
        // calculate if r < sqrt((x-c_x)^2+(y-c_y)^2)
        // then no collision
        //  so do the opposite
        //  decide in play game state which ellipse it needs to pass in.
        //  if its outside of the ellipse then test the rectangle
        float center_x,center_y,radius_x,radius_y;
        center_x = e.getCenterX(); center_y =e.getCenterY();
        radius_x = e.getRadius1(); radius_y = e.getRadius2();

        //  calculate the distance to the center of the ellipse
        double d_center = sqrt((x-center_x)*(x-center_x)+(y-center_y)*(y-center_y));
        System.out.println(d_center);
        if((radius_x < d_center)){
        //    System.out.println("radiusX");
            return true;
        }

        else if((radius_y < d_center)){
      //      System.out.println("radiusY");
            return true;
        }
        else if (rect.contains(x,y)){
            return true;
        }
        else return false;
    }

//  runs through all the collision types
    public boolean CheckCollisons(float newX, float newY, Ellipse ellipse, Rectangle rect) {
        //  Check collide with wall
        //  Check collide with goalie
        //  Check collide with ball
        //  Check collide with cars
        //  Check collide with goals
        return WallCollide(newX, newY, ellipse, rect);
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
