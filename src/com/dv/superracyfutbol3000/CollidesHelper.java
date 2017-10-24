package com.dv.superracyfutbol3000;

import jig.Vector;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

import static com.dv.superracyfutbol3000.SuperRacyFutbol3000.isWallDebug;
import static java.lang.StrictMath.sqrt;


public abstract class CollidesHelper {
    enum CollisionType {Wall,WallBall, WallCar, CarCar, CarBall,CarGoalie,CarGoal, BallGoalie, BallGoal, None}

    public class CollisionReport{
        private Cars car1;
        private Cars car2;
        private Ball ball;
        CollisionType collision_type;
        //            Goalie goalie;
//            Goal goal;
        public CollisionReport() {
            car1 = null;
            car2 = null;
            ball = null;
            collision_type = CollisionType.None;
        }
    }

    public static void CheckWorldCollisions(Teams teams, Ball ball, Ellipse left_boundary, Ellipse right_boundary,
                                            Rectangle center_boundary){
        //  for each car check if it is gonna run into another car, ball, or wall....
        // todo: goalies and goal areas will be added later to collision detection

        //  Teams should have each team in a list
        //  not in seperate variables
        //  todo: change Teams to house red_team blue_team as a list
        ArrayList<Cars> red_team = teams.getRed_team();
        ArrayList<Cars> blue_team = teams.getBlue_team();
        ArrayList<ArrayList> teams_list = new ArrayList<>();
        teams_list.add(red_team);
        teams_list.add(blue_team);

        for(ArrayList<Cars> team: teams_list){
            for(Cars car:team){
                //  Check if  car collides with a wall
                //  check if the car is on the left or right field boundary.
                //  this determines the ellipse to pass into the function.
                if(car.getNext_move_location().getX() < SuperRacyFutbol3000.WIDTH/2){

                    if(WallCollide(car.getNext_move_location(),
                            left_boundary,center_boundary)){
                        //  don't keep this behavior but it works for now
                        car.setX(car.getX()+10f);
                        if(car.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT/2)
                            car.setY(car.getY()+10f);
                        else
                            car.setY(car.getY()-10f);
                        //car.getTranslate_next_move().setY(28f);
                    }
                }else{
                    if(WallCollide(car.getNext_move_location(),
                            right_boundary,center_boundary)){
                        //  don't keep this behavior but it works for now
                        car.setX(car.getX()-10f);
                        if(car.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT/2)
                            car.setY(car.getY()+10f);
                        else
                            car.setY(car.getY()-10f);
                        //car.getTranslate_next_move().setY(28f);
                    }





                }

            }
        }
    }



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
        float center_x, center_y, radius_b, radius_a,x_center_diff, y_center_diff, radius_b_sq, radius_a_sq;
        //  simpler formulas
        //  from: ((x-c)^2)/b^2 +((y-d)^2)/a^2 = 1
        //  q = a^2/b^2
        //  a>0 and q>0
        //  (x-c_x)^2 + q(y-c_y)^2 = a^2
        
//      Find the angle of an object and center of ellipse
//  ==================================



        double y_diff = v.getY() - 360;
        double y_diff_by_a = y_diff/362;
        double theta = Math.asin(y_diff_by_a);
   //   ======================================

        //  Is v in the rectangle
        if (rect.contains(v.getX(), v.getY())) {
            if(isWallDebug)System.out.println("RRRREctangle COLLIDE");
            return false;   // not a collision if v is in the rect
        }
        else {
            float a = e.getWidth()/2;
            float b = e.getHeight()/2;
            boolean y_axis_bigger = false;
            //  formulas for ellipses call the biggest dimension a
            if(b>a){
                a = b;
                b = e.getWidth()/2;
                y_axis_bigger=true;
            }
            center_x = e.getCenterX();//    center of ellipse x coord
            center_y = e.getCenterY();//    center of ellipse y coord
            radius_a = e.getRadius2();//    major radius
            radius_b = e.getRadius1();//    minor radius


            radius_a_sq = radius_a*radius_a;//  square major radius
            radius_b_sq = radius_b*radius_b;//  square minor radius


            x_center_diff = (v.getX()-center_x)*(v.getX()-center_x);//  square the difference in the x direction
            y_center_diff = (v.getY()-center_y)*(v.getY()-center_y);//  square the difference in y direction

            float x_in_ellipse = x_center_diff / radius_b_sq;// is
            float y_in_ellipse = y_center_diff / radius_a_sq;
            float q = radius_a_sq/radius_b_sq;

            //  calculate the distance to the center of the ellipse
            float in_ellipse_calc = (x_in_ellipse + y_in_ellipse);
            float simp_in_ellipse_calc = (x_center_diff)+q*(y_center_diff);
            //System.out.println("Distance to center of ellipse: " + d_center);


//            if(sqrt(simp_in_ellipse_calc) <= sqrt(radius_y_sq)){
//                if(isWallDebug)System.out.println("Simple ellipse Point Test: "
//                        +simp_in_ellipse_calc + " < radius_x^2"+(radius_y_sq));
//                return false;
//            }
//              if v isn't in the rectangle. Is v in ellipse.
            if ((in_ellipse_calc <= 1f)) {
//                if(isWallDebug)System.out.println("ellipse Point Test: "+in_ellipse_calc);
//                if(isWallDebug)System.out.println("Simple ellipse Point Test: "
//                        +simp_in_ellipse_calc + " < radius_x^2"+radius_b_sq);
                return false;
            }

            //  v isn't in the rectange or the ellipse
            //  therefore its a wall collision.
            if(isWallDebug)System.out.println("Out of Ellipse");
//            if(isWallDebug)System.out.println("xterm: "+x_in_ellipse);
//            if(isWallDebug)System.out.println("yterm: "+y_in_ellipse);
            return true;
        }
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
