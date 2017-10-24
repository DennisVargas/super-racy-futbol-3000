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

public class Ball extends Entity{
    private final float radius = 16f;
    private float mass = 0.5f;
    private Circle ball_sphere;
    //  Holds resulting hit vectors that will be added to in the update method
    ArrayList<CollidesHelper.CollisionReport> collision_reports;

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
