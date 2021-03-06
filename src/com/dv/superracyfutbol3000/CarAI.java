package com.dv.superracyfutbol3000;

import jig.Vector;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.StrictMath.PI;
import static java.lang.StrictMath.abs;

public class CarAI {

    private PlayState play_state;// holds a copy of playstate object so as to track positions of things
    private Cars car;
    private Ball ball;
    private Vector red_goal_pos;
    private Vector blue_goal_pos;
    private Rectangle blue_goal_rect, red_goal_rect;
    private Vector prev_car_pos, prev_ball_pos;
    public PlayState getPlayState() {
        return this.play_state;
    }

    public Rectangle getBlueGoalRect(){
        return this.blue_goal_rect;
    }

    public Rectangle getRedGoalRect(){
        return this.red_goal_rect;
    }
    public Ball getBall(){
        return ball;
    }

    public LinkedList<MoveOrder> GenerateGotoBall() {

        LinkedList<MoveOrder> ball_orders = new LinkedList<>();
        MoveOrder order = new MoveOrder();
        float ball_x = ball.getX();
        float ball_y = ball.getY();

        float car_x = car.getX();
        float car_y = car.getY();

        double car_turn_rads = car.getTurn_rads();
        double car_turn_degs = car.getTurn_degs();

        float x_screen_center = SuperRacyFutbol3000.WIDTH/2;
        float y_screen_center = SuperRacyFutbol3000.HEIGHT/2;

        float x = ball_x-x_screen_center;
        float y = y_screen_center-ball_y;
        double angle_to_ball_from_center =  Math.atan2(y,x);

//        if(angle_to_ball_from_center < 0)
//            angle_to_ball_from_center += 2*PI;
//        x = car_x-x_screen_center;
//        y = y_screen_center-car_y;

        x = ball_x - car_x;
        y =  car_y- ball_y;
        double angle_to_ball_from_car =  Math.atan2(y,x);

        if(angle_to_ball_from_car < 0)
            angle_to_ball_from_car += 2*PI;

        if(car_turn_rads < 0)
            car_turn_rads += 2*PI;

        System.out.println("angle_to_ball_from_center: " +(180/Math.PI)*angle_to_ball_from_center);
        System.out.println("angle_to_ball_from_car: " +(180/Math.PI)*angle_to_ball_from_car);
        System.out.println("turn_rads: "+ car_turn_rads);
        System.out.println("turn_degs: " +car_turn_degs);

        double turn_angle_diff;
        if(abs(car_x - ball_x) >= 41f || abs(car_y-ball_y) >= 41f) {
//            if (car_turn_rads <= PI / 2 || car_turn_rads <= -PI / 2) {
//                if (car_x <= ball_x)
//                    turn_angle_diff = car.getTurn_rads() - angle_to_ball_from_car;
//                else
//                    turn_angle_diff = car.getTurn_rads() - angle_to_ball_from_center;
//            } else {
//                if (car_x <= ball_x)
//                    turn_angle_diff = car.getTurn_rads() - angle_to_ball_from_center;
//                else
//                    turn_angle_diff = car.getTurn_rads() - angle_to_ball_from_car;
//
//            }
            turn_angle_diff = car.getTurn_rads() - angle_to_ball_from_car;
            System.out.println("turn_angle_diff : " + turn_angle_diff);
            if (Math.abs(turn_angle_diff) >= Math.PI / 64) {
                if (angle_to_ball_from_car >= car_turn_rads) {
                    if ( angle_to_ball_from_car <= PI/180
                            || angle_to_ball_from_car >= 2*PI-PI/180)
                        order.setAccelCommand(MoveOrder.CarCommands.deccelerate);
                    else if (car.getSpeed() < 1.0f)
                        order.setAccelCommand(MoveOrder.CarCommands.accelerate);
                    order.setTurn_command(MoveOrder.CarCommands.turn_left);
                } else if (angle_to_ball_from_car < car_turn_rads) {
                    if ( angle_to_ball_from_car <= PI/180
                            || angle_to_ball_from_car >= 2*PI-PI/180)
                        order.setAccelCommand(MoveOrder.CarCommands.deccelerate);
                    else if (car.getSpeed() < 1.0f)
                        order.setAccelCommand(MoveOrder.CarCommands.accelerate);
                    order.setTurn_command(MoveOrder.CarCommands.turn_right);
                }
            } else {
                if (car.reverse)
                    order.setAccelCommand(MoveOrder.CarCommands.deccelerate);
                else
                    order.setAccelCommand(MoveOrder.CarCommands.accelerate);
            }
        }

//        double ball_car_angle_diff = angle_to_ball_from_center - car.getTurn_rads();
//        double ball_car_angle_diff = angle_to_ball_from_center - angle_to_car_from_center ;
//        if(ball_car_angle_diff >0.01){
//            System.out.println("TurnLeft\nball_car_angle_diff: "+ball_car_angle_diff);
//            order.setAccelCommand(MoveOrder.CarCommands.accelerate);
//            order.setTurn_command(MoveOrder.CarCommands.turn_left);
//        }
//        else if (ball_car_angle_diff <0.01){
//            System.out.println("TurnRight\nball_car_angle_diff: "+ball_car_angle_diff);
//            order.setAccelCommand(MoveOrder.CarCommands.accelerate);
//            order.setTurn_command(MoveOrder.CarCommands.turn_right);
//        } else if(car_x > ball_x){
//            System.out.println("passed the ball");
//        }

        ball_orders.add(order);
        return ball_orders;

//        x = car_x - x_screen_center;
//        y = y_screen_center-car_y;
//        double angle_to_car_from_center = Math.atan2(y,x);
//        if(angle_to_car_from_center < 0)
//            angle_to_car_from_center += 2*PI;
//        System.out.println("angle_to_car_from_center: " +(180/Math.PI)*angle_to_car_from_center);
//        System.out.println("turn rads to degs: "+(180/Math.PI)*car_turn_rads);

//        GenerateTurnOrder(this.play_state.getBall().getPosition(), this.car.getPosition());
    }

    public CarAI() {
//        this.play_state = play_state;
    }

    public void setBall(Ball ball){
        this.ball = ball;
    }

    public void setCar(Cars car){
        this.car = car;
    }

    private MoveOrder GenerateTurnOrder(Vector ball_pos, Vector car_pos) {
        return new MoveOrder();
    }

    public void setRedGoalRect(Rectangle red_goal_rect) {
        this.red_goal_rect = red_goal_rect;
    }

    public void setBlueGoalRect(Rectangle blue_goal_rect) {
        this.blue_goal_rect = blue_goal_rect;
    }

    enum Needs {needBall, needBoost, needDefense, needHealth}
    enum MoveCommands{Accelerate, Decelerate, Left, Right}

    Needs need = Needs.needBall;

    public ArrayList<Vector> WhereHealth(){
        //  scans board for health and
        // reports an ArrayList of Vector health locations
        ArrayList<Vector> health_location = new ArrayList<>();

        return null;
    }

    public void DecideMove(Ball ball, Cars car){
        Vector ball_position = ball.getPosition();

        ball_position.getX();
        ball_position.getY();

        float x_screen_center = SuperRacyFutbol3000.WIDTH/2;
        float y_screen_center = SuperRacyFutbol3000.WIDTH/2;

        float x = x_screen_center- ball_position.getX();
        float y = ball_position.getY() - y_screen_center;
        double angle_to_ball_from_center =  Math.atan2(x,y);

        System.out.println("angle_to_ball_from_center: "+angle_to_ball_from_center);

    }

    public Vector WhereBoost(){
        return null;
    }

    public Vector getMyGoal(){
        return null;
    }

    public Vector getTheirGoal (){
        return null;
    }


    public void setPlayState(PlayState play_state) {
        this.play_state = play_state;
    }
//    public static void main(String args[]){
//        Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE);
//        Ball ball = new Ball();
//
//        CarAI carAI = new CarAI();
//        Cars car = new Cars(0,0, new Players(Players.Controller.AI));
//        ball.setPosition(0,0);
//        CarAI.DecideMove(ball, car);
//    }

}
