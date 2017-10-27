package com.dv.superracyfutbol3000;

import jig.Vector;

public class CarAI {

    enum Needs {needBall, needBoost, needDefense, needHealth}
    enum MoveCommands{Accelerate, Decelerate, Left, Right}

    Needs need = Needs.needBall;
    class MoveNode{

    }
    public Vector WhereHealth(){
        //  scans board for health when it needs health
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
