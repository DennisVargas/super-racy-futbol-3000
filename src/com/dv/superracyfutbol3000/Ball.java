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

    //  Update Ball
    public void UpdateBall(Ellipse left_walls, Ellipse right_walls, Rectangle center_walls, Cars player_car){

        Collision collide = this.collides(player_car);
        if(collide != null)
            System.out.println("car hit ball");

    }

}
