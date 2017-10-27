package com.dv.superracyfutbol3000;

import jig.Collision;
import jig.Vector;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

import static com.dv.superracyfutbol3000.SuperRacyFutbol3000.isWallDebug;


public abstract class CollidesHelper {
    enum CollisionType {Wall,WallBall, WallCar, CarCar, CarBall,CarGoalie,CarGoal, BallGoalie, BallGoal, None}
    enum CarExtentNames {minXY, maxXY, maxMinXY, minMaxXY}
    private int previous_check_time = 0;

    public int getPrevious_check_time() {
        return previous_check_time;
    }

    public void setPrevious_check_time(int previous_check_time) {
        this.previous_check_time = previous_check_time;
    }

    public static Vector CarCarCollides(Cars carA, Cars carB){
        float car_hit= 0.9f;
        Vector resultA = carA.getTranslate_next_move();
        Vector resultB;
        Collision collide;
        collide = carA.collides(carB);
        //  If carB hits CarA then add its direction to the direction of car B
        //  this should probably be dot product but whatever
        //  That would be the velocities to get the speed.
        //  i should take the unit vector of the translation and get the dot prodcut if i want the speed?
        //  figure it out later...just do this.
        if (collide != null){
            resultB = carB.getTranslate_next_move();
            //  increase car B magnitude of repulsion
            float speed_reduction =0.5f;
//            Vector carA_collision_translation = new Vector(carA.getTranslate_next_move().getX(),
//                    carB.getTranslate_next_move().getY());
//            Vector carB_collision_translation = new Vector(carB.getTranslate_next_move().getX()*speed_reduction,
//                carB.getTranslate_next_move().getY()*speed_reduction);

            //  get both resulting translation vectors for A and B
            resultA = carA.getTranslate_next_move().add(carB.getTranslate_next_move());
            resultB = carB.getTranslate_next_move().add(carA.getTranslate_next_move());
//            resultA = carA_collision_translation.add(carB_collision_translation);
            //  if carb is standing still reflect car A none the less
//            if (abs(carB_collision_translation.getY()) == 0||abs(carB_collision_translation.getX()) == 0 )
//                resultA = new Vector (resultA.getX()*-1f, resultA.getY()*-1f);

           // carA.setSpeed(carA.getSpeed()-carB.getSpeed()*0.125f);

            carA.setNextTranslation(resultA);
            carB.setNextTranslation(resultB);
            BounceCars(carA,carB);

            carA.ReduceHealth(car_hit);
            carB.ReduceHealth(car_hit);
            return resultA;
        }
        else
            return resultA;
    }

    private static void BounceCars(Cars carA, Cars carB){
        int bounce_displace = 5;
        if(carA.getPosition().getX() > carB.getPosition().getX()) {
            //  to the right and below carB
            if (carA.getPosition().getY() > carB.getPosition().getY()) {
                carA.setPosition(new Vector(carA.getX() + bounce_displace,
                        carA.getY() + bounce_displace));
                carB.setPosition(new Vector(carB.getX() - bounce_displace,
                        carB.getY() - bounce_displace));
            }
            //  to the right and above carB
            else if (carA.getPosition().getY() < carB.getPosition().getY()) {
                carA.setPosition(new Vector(carA.getX() + bounce_displace,
                        carA.getY() - bounce_displace));
                carB.setPosition(new Vector(carB.getX() - bounce_displace,
                        carB.getY() + bounce_displace));
            }
            else if (carA.getPosition().getY() == carB.getPosition().getY()){
                carA.setPosition(new Vector(carA.getX() + bounce_displace,
                        carA.getY() ));
                carB.setPosition(new Vector(carB.getX() - bounce_displace,
                        carB.getY() ));
            }
        }else if (carA.getPosition().getX() < carB.getPosition().getX()) {
            //  to the left and below carB
            if (carA.getPosition().getY() > carB.getPosition().getY()) {
                carA.setPosition(new Vector(carA.getX() - bounce_displace,
                        carA.getY() + bounce_displace));
                carB.setPosition(new Vector(carB.getX() + bounce_displace,
                        carB.getY() - bounce_displace));
            }
            //  to the left and above carB
            else if (carA.getPosition().getY() < carB.getPosition().getY()) {
                carA.setPosition(new Vector(carA.getX() - bounce_displace,
                        carA.getY() - bounce_displace));
                carB.setPosition(new Vector(carB.getX() + bounce_displace,
                        carB.getY() + bounce_displace));
            }
            else if (carA.getPosition().getY() == carB.getPosition().getY()){
                carA.setPosition(new Vector(carA.getX() - bounce_displace,
                        carA.getY() ));
                carB.setPosition(new Vector(carB.getX() + bounce_displace,
                        carB.getY() ));
            }
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

    public static void GoalieBallCollide(Goalie goalie, Ball ball){
        float goalie_x = goalie.getX();
        float goalie_y = goalie.getY();
        float goalie_height = goalie.goalie_rect.getHeight();

        if(goalie_x < SuperRacyFutbol3000.WIDTH/2){
            if(ball.getNext_move_location().getX()-ball.getCoarseGrainedRadius() <= goalie_x){
                //  check the top height
                if(ball.getNext_move_location().getY() >= goalie_y - goalie_height/2f
                        && ball.getNext_move_location().getY() <= goalie_y + goalie_height/2f){
                    ball.setPosition(ball.getX()+10f, ball.getY());
                    ball.setNext_move_direction(new Vector(ball.getNext_move_direction().getX()*-1f,
                            ball.getNext_move_direction().getY()));
                }
            }
        }
        if(goalie_x > SuperRacyFutbol3000.WIDTH/2){
            if(ball.getNext_move_location().getX()+ball.getCoarseGrainedRadius() >= goalie_x){
                //  check the top height
                if(ball.getNext_move_location().getY() >= goalie_y - goalie_height/2f
                        && ball.getNext_move_location().getY() <= goalie_y + goalie_height/2f){
                    ball.setPosition(ball.getX()-10f, ball.getY());
                    ball.setNext_move_direction(new Vector(ball.getNext_move_direction().getX()*-1f,
                            ball.getNext_move_direction().getY()));
                }
            }
        }

    }



    public static void CheckWorldCollisions(Teams teams, Ball ball, Ellipse left_boundary, Ellipse right_boundary,
                                            Rectangle center_boundary,int time) {
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
        float wall_hit = 0.98f;
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
                            car.setX(car.getX() + 13f);

                            car.ReduceHealth(wall_hit);

                            //  if y coordinate is less than screen middle increase y
                            if (car.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT / 2)
                                car.setY(car.getY() + 13f);
                            else//  decrease y its in the lower half
                                car.setY(car.getY() - 13f);
                            //car.getTranslate_next_move().setY(28f);
                        }
                    }
                    //  X is on the right side of the field since its larger than rectangle bound max
                    else if (car.getNext_move_location().getX() > center_boundary.getMaxX()) {
                        //  evaluate point with ellipse 2 right boundary
                        if (WallCollide(new_extent_move, right_boundary, center_boundary)) {
                            //  don't keep this behavior but it works for now
                            car.setX(car.getX() - 13f);
                            car.ReduceHealth(wall_hit);
                            if (car.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT / 2)
                                car.setY(car.getY() + 13f);
                            else
                                car.setY(car.getY() - 13f);
                            //car.getTranslate_next_move().setY(28f);
                        }
                    } else if (center_boundary.getMinX() <= new_extent_move.getX() && new_extent_move.getX() <= center_boundary.getMaxX()) {
//                      point is not in the rectangle it is trying to get out the top or bottom
                        if (!center_boundary.contains(new_extent_move.getX(), new_extent_move.getY())) {
                            car.ReduceHealth(wall_hit);
                            if (new_extent_move.getY() > SuperRacyFutbol3000.HEIGHT / 2)
                                car.setY(car.getY() - 13f);
                            else
                                car.setY(car.getY() + 13f);
                        }
                    }
                }// end test car extreme for wall contact

                //  ===========================
                //  BEGIN car ball collisions
                //  check for car collisions with ball.
                Collision collision = null;

                collision = ball.collides(car);

                if(collision!=null){
//                    System.out.println("Stop TOUCHING ME");



                    //  Gets a direction multiplied by the current magnitude but the car force is not added
//                    Vector result_bounced_direction = ball.getNext_move_direction().bounce(90-car.getTurn_degs()%180);
                    //  adds the car direction to the ball direction
                    Vector result_bounced_direction = ball.getNext_move_direction().add(car.getNext_move_direction());
                    //  sets ball direction to the resulting direction after the collision
                    ball.setNext_move_direction(result_bounced_direction);

//                    System.out.println("Bounced dir x: "+result_bounced_direction.getX());
//                    System.out.println("Bounced dir y: "+result_bounced_direction.getY());
                    //  sets ball speed to 1.5 of the cars in hopes it will move out in front and not collide again.
                    //  todo: may need a top speed
                    ball.setSpeed((ball.getSpeed()+ 1.25f*car.getSpeed())%ball.getTopSpeed());//car speed carries direction forward and back


                    //  create a translation vector multiplying speed*direction
                    Vector result_translate = new Vector(ball.getSpeed()*result_bounced_direction.getX(),
                            ball.getSpeed()*result_bounced_direction.getY());
                    System.out.println("translate dir x: "+result_translate.getX());
                    System.out.println("translate dir y: "+result_translate.getY());

                    //  set the ball next_translation to be the result translate.
                    //  it can then be verified for wall collisions
                    ball.setTranslateNextMove(result_translate);

                    //  bounce away from car to avoid another collision
                    //  ball to left of car
                    if(ball.getX() < car.getX()){
                        //  ball left and above car
                        if(ball.getY()< car.getY()){
                            ball.setPosition(ball.getX()-2f,ball.getY() - 2f);
                        }else if(ball.getY()> car.getY()){
                            ball.setPosition(ball.getX()-2f, ball.getY()+2f);
                        }else if (ball.getY() == car.getY()){
                            ball.setPosition(ball.getX()-2f, ball.getY());
                        }
                    }
                    else if(ball.getX() > car.getX()){
                        //  ball left and above car
                        if(ball.getY()< car.getY()){
                            ball.setPosition(ball.getX()+2f,ball.getY() - 2f);
                        }else if(ball.getY()> car.getY()){
                            ball.setPosition(ball.getX()+2f, ball.getY()+2f);
                        }else if (ball.getY() == car.getY()){
                            ball.setPosition(ball.getX()+2f, ball.getY());
                        }
                    } else if (ball.getX() == 0){
                        //  ball left and above car
                        if(ball.getY()< SuperRacyFutbol3000.HEIGHT/2){
                            ball.setPosition(ball.getX(),ball.getY() - 2f);
                        }else if(ball.getY()> SuperRacyFutbol3000.HEIGHT/2){
                            ball.setPosition(ball.getX(), ball.getY()+2f);
                        }
                    }
                    //  takes all translation vector and generates a potential next move
                    //  this will be used to test against wall collisions, goals, and goalie conditions
                    ball.GenerateNextMove();

                }// end car ball collisions

                //  =========================
                //  BEGIN CAR CAR COLLISIONS
                //  ============================
                for(ArrayList<Cars> teamB: teams_list){
                    for(Cars carB: teamB){
                        if(car != carB){
                            System.out.println("not same car: carA: "+ car.controlling_player.GetPlayerName()
                                    +" carB: "+carB.controlling_player.GetPlayerName());
                            car.setNextDirection(CarCarCollides(car, carB));
                        }
                    }
                }
            }// end car per team
        }// end team list


        //  BEGIN BALL COLLISION TESTING (walls, goals, goalies)
        //  check ball collisions with walls
        //  ====================================

        //  LEFT SIDE OF FIELD TEST
        //  check if next ball move is less than rectangle boundary
        if (ball.getNext_move_location().getX() <= center_boundary.getMinX()) {
            //  Send left ellipse to wall collide and evaluate next ball move
            if (WallCollide(ball.getNext_move_location(),
                    left_boundary, center_boundary)) {
                ball.setNext_move_direction(new Vector(ball.getNext_move_direction().getX()*-1f,
                        ball.getNext_move_direction().getY()*-1f));
                //  if y coordinate is less than screen middle increase y
                if (ball.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT / 2)
                    ball.setPosition(ball.getX()+10f,ball.getY() + 10f);
                else//  decrease y its in the lower half
                    ball.setPosition(ball.getX()+10f,ball.getY() - 10f);
            }
        }// END LEFT FIELD BALL WALL COLLISION

        //  BEGIN RIGHT SIDE OF FIELD BALL WALL COLLISION
        else if (ball.getNext_move_location().getX() >= center_boundary.getMaxX()) {
            //  evaluate point with ellipse 2 right boundary
            if (WallCollide(ball.getNext_move_location(), right_boundary, center_boundary)) {
                //  don't keep this behavior but it works for now
                //    ball.setX(ball.getX() - 300f);
                /*    ball.setNext_move_direction( new Vector((float) acos((ball.getPosition().getX()-955f)/326f),
                            (float) asin((ball.getPosition().getY()-360)/360 )));*/
                ball.setNext_move_direction(new Vector(ball.getNext_move_direction().getX() * -1f,
                        ball.getNext_move_direction().getY() * -1f));
                if (ball.getNext_move_location().getY() < SuperRacyFutbol3000.HEIGHT / 2)
                    ball.setPosition(ball.getX()-10f,ball.getY() + 10f);
                else//  decrease y its in the lower half
                    ball.setPosition(ball.getX()-10f,ball.getY() - 10f);
            }// END RIGHT SIDE BALL WALL COLLISION TEST
        }
            //  BEGIN BALL IN RECTANGLE COLLISION TEST
            else if (center_boundary.getMinX() <= ball.getNext_move_location().getX() &&
                    ball.getNext_move_location().getX() <= center_boundary.getMaxX()) {
//                      point is not in the rectangle it is trying to get out the top or bottom

                //  Ball Rectangle collision
                if (!center_boundary.contains(ball.getNext_move_location().getX(), ball.getNext_move_location().getY())) {
//              Check if ball is the upper part of the screen
                    if (ball.getNext_move_location().getY() <= SuperRacyFutbol3000.HEIGHT / 2f) {
                        // set direction to bottom of screen positive direction
                        ball.setNext_move_direction(new Vector(ball.getNext_move_direction().getX(),
                                ball.getNext_move_direction().getY() * -1f));
                        ball.setPosition(ball.getX(),ball.getY() + 10f);
                    }
                    //  ball is in the lower part of the screen
                    else {
                        System.out.println("Lower Rect Collide: direction -1 y change");
                        // set direction to bottom of screen positive direction
                        Vector ball_reflect_dir = new Vector(ball.getNext_move_direction().getX(),
                                ball.getNext_move_direction().getY() * -1f);
                        System.out.println("reflectX: " + ball_reflect_dir.getX() + "reflectY: " + ball_reflect_dir.getY());
                        ball.setNext_move_direction(ball_reflect_dir);
                        ball.setPosition(ball.getX(),ball.getY() - 10f);
//                    ball.setY(ball.getY() + 10f);
                    }
                    //  Adjust speed - none for now
                    // recalc ball next move
                    ball.GenerateNextMove();
                }
            }// END BALL RECTANGLE COLLISION TEST
        //  Begin Ball Goalie Collision Test
            GoalieBallCollide(teams.blue_goalie, ball);
            GoalieBallCollide(teams.red_goalie, ball);
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
