package com.dv.superracyfutbol3000;

import org.newdawn.slick.geom.Ellipse;

import static java.lang.StrictMath.sqrt;

public abstract class CarCollides {

    //  takes the x and y of the car and subtract the center x and center y of an ellipse
    //  this ellipse will be on the far goal end of the field.
    //  when the car crosses this threshold return true
    public static boolean WallCollide(float car_x, float car_y, float center_x, float center_y, float radius_x, float radius_y) {
        // calculate if r < sqrt((x-c_x)^2+(y-c_y)^2)
        // then no collision
        //  so do the opposite
        double distance_to_center = sqrt((car_x-center_x)*(car_x-center_x)+(car_y-center_y)*(car_y-center_y));
        System.out.println(distance_to_center);
        if((radius_x < distance_to_center)){
        //    System.out.println("radiusX");
            return true;
        }

        else if((radius_y < distance_to_center)){
      //      System.out.println("radiusY");
            return true;
        }

        else return false;
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
