package com.dv.superracyfutbol3000;

import jig.Collision;
import jig.Vector;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.FastTrig;

import java.util.ArrayList;

import static com.dv.superracyfutbol3000.SuperRacyFutbol3000.isWallDebug;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.StrictMath.sqrt;


public abstract class CollidesHelper {
    enum CollisionType {Wall,WallBall, WallCar, CarCar, CarBall,CarGoalie,CarGoal, BallGoalie, BallGoal, None}
    enum CarExtentNames {minXY, maxXY, maxMinXY, minMaxXY}

    private CarExtentNames GetCollidePointName(int index) {
        switch(index){
            case 0:
                return CarExtentNames.minXY;
            case 1:
                return CarExtentNames.maxXY;
            case 2:
                return CarExtentNames.maxMinXY;
            case 3:
                return CarExtentNames.minMaxXY;

            default:
                return null;
        }
    }

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
                                            Rectangle center_boundary) {
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

        //  for each team in teams_list
        for (ArrayList<Cars> team : teams_list) {
            //  for each car in team
            for (Cars car : team) {
                //  Check if  car collides with a wall
                //  check if the car is on the left or right field boundary.
                //  this determines the ellipse to pass into the function.

                //  get the array of extreme points from its globally transformed shape
                float[] car_points = car.getGloballyTransformedShapes().getFirst().getPoints();

                //  set y iterator to be 1 ahead of x iterator
                int j = 1;
                //  iterate through the list two at a time until reaching length-1
                for (int i = 0; i < car_points.length - 1; i += 2) {

                    //  create a new vector point for the new extent point
                    Vector new_extent_move = new Vector(car_points[i] + car.getTranslate_next_move().getX(),
                            car_points[j] + car.getTranslate_next_move().getY());
                    //  increment the y iterator
                    j += 2;

                    //  check if new extent move is less than rectangle boundary
                    if (new_extent_move.getX() <= center_boundary.getMinX()) {

                        //  Send left ellipse to wall collide and evaluate extent
                        if (WallCollide(new_extent_move,
                                left_boundary, center_boundary)) {
                            //  don't keep this behavior but it works for now
                            //  translate in positive x direction to the right if wall hit
                            car.setX(car.getX() + 10f);

                            //  if y coordinate is less than screen middle increase y
                            if (car.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT / 2)
                                car.setY(car.getY() + 10f);
                            else//  decrease y its in the lower half
                                car.setY(car.getY() - 10f);
                            //car.getTranslate_next_move().setY(28f);
                        }
                    }
                    //  X is on the right side of the field since its larger than rectangle bound max
                    else if (car.getNext_move_location().getX() > center_boundary.getMaxX()) {
                        //  evaluate point with ellipse 2 right boundary
                        if (WallCollide(new_extent_move, right_boundary, center_boundary)) {
                            //  don't keep this behavior but it works for now
                            car.setX(car.getX() - 10f);
                            if (car.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT / 2)
                                car.setY(car.getY() + 10f);
                            else
                                car.setY(car.getY() - 10f);
                            //car.getTranslate_next_move().setY(28f);
                        }
                    } else if (center_boundary.getMinX() <= new_extent_move.getX() && new_extent_move.getX() <= center_boundary.getMaxX()) {
//                      point is not in the rectangle it is trying to get out the top or bottom
                        if (!center_boundary.contains(new_extent_move.getX(), new_extent_move.getY())) {
                            if (new_extent_move.getY() > SuperRacyFutbol3000.HEIGHT / 2)
                                car.setY(car.getY() - 10f);
                            else
                                car.setY(car.getY() + 10f);
                        }
                    }
                }
                //  check for car collisions with ball.
                Collision collision = null;
                collision = ball.collides(car);
                if(collision!=null){
                    System.out.println("Stop TOUCHGING ME");

                   // ball.setNext_move_direction(ball.getNext_move_direction().bounce(80)) ;
                    //ball.setTranslateNextMove(ball.getNext_move_direction().add(car.getTranslate_next_move()),1);

                    //  Gets a direction multiplied by the current magnitude but the car force is not added
//                    Vector result_bounced_direction = ball.getNext_move_direction().bounce(90-car.getTurn_degs()%180);
                    //  adds the car direction to the ball direction
                    Vector result_bounced_direction = ball.getNext_move_direction().add(car.getNext_move_direction());
                    //  sets ball direction to the resulting direction after the collision
                    ball.setNext_move_direction(result_bounced_direction);

                    System.out.println("Bounced dir x: "+result_bounced_direction.getX());
                    System.out.println("Bounced dir y: "+result_bounced_direction.getY());
                    //  sets ball speed to 1.5 of the cars in hopes it will move out in front and not collide again.
                    //  todo: may need a top speed
                    ball.setSpeed(ball.getSpeed()+abs(car.getSpeed()));//car speed carries direction forward and back

                    //  create a translation vector multiplying speed*direction
                    Vector result_translate = new Vector(ball.getSpeed()*result_bounced_direction.getX(),
                            ball.getSpeed()*result_bounced_direction.getY());
                    System.out.println("translate dir x: "+result_translate.getX());
                    System.out.println("translate dir y: "+result_translate.getY());

                    //  set the ball next_translation to be the result translate.
                    //  it can then be verified for wall collisions
                    ball.setTranslateNextMove(result_translate);

                    //  takes all translation vector and generates a potential next move
                    //  this will be used to test against wall collisions, goals, and goalie conditions
                    ball.GenerateNextMove();

                    //ball.setTranslateNextMove();
//                    ball.setPosition(ball.getX()+ball.getNext_move_direction().getX()*ball.getSpeed(),
//                            ball.getY()+ball.getNext_move_direction().getY()*ball.getSpeed());

                  //  ball.setSpeed(ball.getSpeed()+car.getSpeed());
                    /*ball.setTranslateNextMove(car.getTranslate_next_move(),
                            car.getAcceleration()*5);*/
                }

            }
        }
        // check ball collisions with walls

        //  check if new extent move is less than rectangle boundary
        if (ball.getNext_move_location().getX() <= center_boundary.getMinX()) {

            //  Send left ellipse to wall collide and evaluate extent
            if (WallCollide(ball.getNext_move_location(),
                    left_boundary, center_boundary)) {
                //  don't keep this behavior but it works for now
                //  translate in positive x direction to the right if wall hit
                ball.setX(ball.getX() + 200f);
                //  if y coordinate is less than screen middle increase y
                if (ball.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT / 2)
                    ball.setY(ball.getY() + 200f);
                else//  decrease y its in the lower half
                    ball.setY(ball.getY() - 200f);
                //car.getTranslate_next_move().setY(28f);
            }
        }
        //  X is on the right side of the field since its larger than rectangle bound max
        else if (ball.getNext_move_location().getX() >= center_boundary.getMaxX()) {
            //  evaluate point with ellipse 2 right boundary
            if (WallCollide(ball.getNext_move_location(), right_boundary, center_boundary)) {
                //  don't keep this behavior but it works for now
            //    ball.setX(ball.getX() - 300f);
                ball.setNext_move_direction(ball.getNext_move_direction().bounce(90));
                ball.setSpeed(10);
                if (ball.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT / 2)
                    ball.setY(ball.getY() + 10f);
                else
                    ball.setY(ball.getY() - 10f);
                //car.getTranslate_next_move().setY(28f);
            }

        }
        //  ball in rectangle
        else if (center_boundary.getMinX() <= ball.getNext_move_location().getX() &&
                ball.getNext_move_location().getX()<= center_boundary.getMaxX()) {
//                      point is not in the rectangle it is trying to get out the top or bottom
            //  Ball Rectangle collision
            if (!center_boundary.contains(ball.getNext_move_location().getX(), ball.getNext_move_location().getY())) {
                if (ball.getNext_move_location().getY() >= SuperRacyFutbol3000.HEIGHT / 2f){
                    // set direction to bottom of screen positive direction
                    ball.setNext_move_direction(new Vector(ball.getNext_move_direction().getX(),1f));



                }
                //                    ball.setY(ball.getY() - 10f);
                else{
                    // set direction to bottom of screen positive direction
                    ball.setNext_move_direction(new Vector(ball.getNext_move_direction().getX(),1f));
//                    ball.setY(ball.getY() + 10f);
                }
                //  Adjust speed - none for now
                // recalc ball next move
                ball.GenerateNextMove();
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
            if(isWallDebug)System.out.println("RRRREctangle CONtained");
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
