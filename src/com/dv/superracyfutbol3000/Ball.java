package com.dv.superracyfutbol3000;

import jig.Collision;
import jig.ConvexPolygon;
import jig.Entity;
import jig.Vector;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class Ball extends Entity{
    private final float radius = 16f;
    private float mass = 0.5f;
    private Circle ball_sphere;
    //  Holds resulting hit vectors that will be added to in the update method
    ArrayList<CollidesHelper.CollisionReport> collision_reports = new ArrayList<>();

    public Vector getNext_move_direction() {
        return next_move_direction;
    }

    public void setNext_move_direction(Vector next_move_direction) {
        this.next_move_direction = next_move_direction;
    }

    private Vector next_move_direction = new Vector(0f, 0f);

    public Vector getNext_move_location() {
        return next_move_location;
    }

    private Vector next_move_location = new Vector(0f,0f);
    private Vector translate_next_move = new Vector(0f,0f);

    private float wall_bounce_factor = 0.003f;
    private float speed_0 = 0.2f;
    private float speed = speed_0;



    public Ball(Vector position) {
        super(position);
    }


    public Ball(float x, float y) {
        super(x, y);
        //setBallImage();// or maybe just draw a shape for now and forever
        ball_sphere = new Circle(x,y,this.radius);
        this.addShape(new ConvexPolygon(this.radius));
        this.GenerateNextMove();
    }

    public Ball() {

    }

    //  Render Ball
    public void RenderBall(Graphics g){
        g.setColor(Color.orange);
        g.fill(this.ball_sphere);
        this.render(g);
    }

    //  Process Ball Hit
    public void ProcessBallHit(){

    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void GenerateNextMove(){
        translate_next_move = translate_next_move.setX(speed * (next_move_direction.getX()));
        translate_next_move = translate_next_move.setY(speed * (next_move_direction.getY()));/*
        if (speed != 0) {
            translate_next_move = translate_next_move.setX(speed * (next_move_direction.getX()) + translate_next_move.getX());
            translate_next_move = translate_next_move.setY(speed * (next_move_direction.getY()) + translate_next_move.getY());
        }else{
            translate_next_move = translate_next_move.setX(0);
            translate_next_move = translate_next_move.setY(0);
        }*/
        CalculateNextPosition();
    }

    //  Update Ball
    public void UpdateBall(Ellipse left_walls, Ellipse right_walls, Rectangle center_walls){
//        AddCollides();
        this.translate(translate_next_move);
        if(speed < 6.8f){
            if(speed >0.1f)
                speed*=0.5f;
            else if (abs(speed) <= 0.1f)
                speed = 0f;
        }else
            speed = 6.8f*0.5f;

        this.ball_sphere.setLocation(this.getX()-ball_sphere.radius ,
                                        this.getY()-ball_sphere.radius);
        GenerateNextMove();
        //  Get next ball location
    }

    public Vector getTranslate_next_move() {
        return translate_next_move;
    }
    public Vector CalculateNextPosition(){
        this.next_move_location = new Vector(this.getX()+this.translate_next_move.getX(), this.getY()+this.translate_next_move.getY());
        return this.next_move_location;
    }


}
