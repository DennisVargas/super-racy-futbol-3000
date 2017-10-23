package com.dv.superracyfutbol3000;

import jig.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.util.FastTrig;

import java.math.MathContext;
import java.util.Scanner;

import static com.dv.superracyfutbol3000.CollidesHelper.*;
import static com.dv.superracyfutbol3000.SuperRacyFutbol3000.*;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static org.newdawn.slick.util.FastTrig.cos;
import static org.newdawn.slick.util.FastTrig.sin;

public class Cars extends Entity{
    private float wall_bounce_factor = 0.003f;

    enum CarExtentNames {minXY, maxXY, maxMinXY, minMaxXY};
    Quadrant facing_direction = new Quadrant();
    Quadrant moving_direction = new Quadrant();
    Players controlling_player;
    private float health_level = 0f;
    private float boost_level = 0f;
    private float acceleration = 1.075f;
    private float mass = 1f;

    private double turn_angle = Math.PI/2;

    private double turn_increment = Math.PI/175;
    private float top_speed = 3.5f;
    private float top_boost_speed = 0f;
    private float max_accel = 0f;
    private float max_boost_vel = 0f;
    private float min_vel = 0.25f;
    private float vel = 0.0f;
    private float vel_0 = 0.7f;
    private float friction = 0.90f; // simulates friction, gravity, and mass
    private int player_number = -1; //  controlling player number
    private boolean isRed = false;

    enum TurnDirection {Left, Right}

    //  Cars Constructor
    public Cars(float x, float y, Players controlling_player) {
        super(x, y);
        this.controlling_player = controlling_player;

        SetCarImage();

        this.debugThis = true;
    }

    private void SetCarImage() {
        if(controlling_player.isRed)
            this.addImage(ResourceManager.getImage(cars_red_rsc));
        else
            this.addImage(ResourceManager.getImage(cars_blue_rsc));

        SetBoundingBox();
        this.scale(0.67f);  // the image is a little big for the field so scale this down to gain space
    }

    private void SetBoundingBox() {
        Image car_img = ResourceManager.getImage(cars_blue_rsc);
        float w = car_img.getWidth();
        float h = car_img.getHeight();
        Rectangle rect = new Rectangle(this.getX(),this.getY(),w,h);
        //this.addShape(rect);
        this.addShape(new ConvexPolygon(w, h),new Vector(0,0));
        if(SuperRacyFutbol3000.isDebugCarCreation)System.out.println(this.getNumShapes());
    }

    public double getTurn_angle() {
        return turn_angle;
    }

    public void setTurn_angle(double turn_angle) {
        this.turn_angle = turn_angle;
    }

    void ProcessInput(Input i){
        switch(controlling_player.control_type){
            case Keyboard:
                if(i.isKeyDown(Input.KEY_UP)){
                    Accelerate();
                }else if(i.isKeyDown(Input.KEY_DOWN)){
                    Decelerate();

                }else {
                    //  If not accelerating or decelerating
                    //  losing velocity due to friction
                    if (abs(vel) > min_vel)
                        vel *= friction;
                    else
                        vel = 0f;
                }
                if(abs(vel)>1.3f){
                    if(i.isKeyDown(Input.KEY_A))
                        Steer(TurnDirection.Left);
                    if(i.isKeyDown(Input.KEY_D))
                        Steer(TurnDirection.Right);
                }
                break;
            case Gamepad:
                int controller_num = controlling_player.GetControllerNumber();
                if(i.isButton1Pressed(controller_num)){
                    Accelerate();
                }else if(i.isButton2Pressed(controller_num)){
                    Decelerate();

                }else {
                    //  If not accelerating or decelerating
                    //  losing velocity due to friction
                    if (abs(vel) > min_vel)
                        vel *= friction;
                    else
                        vel = 0f;
                }
                if(abs(vel)>1.3f){
                    if(i.isControllerLeft(controller_num))
                        Steer(TurnDirection.Left);
                    if(i.isControllerRight(controller_num))
                        Steer(TurnDirection.Right);
                }
                break;
        }
    }

    private void Steer(TurnDirection dir) {
        switch(dir){
            case Left:
                turn_angle+= turn_increment;
                this.rotate(-(180/Math.PI)*turn_increment);
                break;
            case Right:
                turn_angle-= turn_increment;
                this.rotate((180/Math.PI)*turn_increment);
                break;
            default:
                break;
        }
    }

    public void UpdateCar(Ellipse goal_ellipse_bounds, Rectangle center_rectangle_bounds){
        Vector next_move = new Vector(0f,0f);
    //  calculate newX adding to old x the cos of the angle that has ben turned through
//      the x component of the velocity vector given the turn_angle at the magnitude of vel
        float newX = (float)this.getX()+(float)(vel*cos(turn_angle));
    //  calculate new Y component
        float newY = (float)this.getY() - (float)(vel*sin(turn_angle));

        next_move = next_move.setX(newX);
        next_move = next_move.setY(newY);

        //Check if collisions then if that's all good move the car
        CollidesHelper.CollisionType collision_type =
                CollidesHelper.CheckCollisons(newX, newY, goal_ellipse_bounds, center_rectangle_bounds);
        if(collision_type == CollidesHelper.CollisionType.None){
            this.setX(newX);
            this.setY(newY);
        }else
            ProcessHit(collision_type, next_move, center_rectangle_bounds); // process hit will determine what the newX and newY
                                                    // direction and magnitude should be after the collision
    }

    private void Accelerate() {
        if(vel <= top_speed) {
            //  if car is moving
            if (vel > 0) {
                vel *= acceleration;
            }
            //  car moving in reverse
            //  change direction
            else if (vel < -min_vel) {
                vel *= (1 - (acceleration - 1) * 6);
            } else {
                //  car is stopped
                //  this is first acceleration
                //  start slow normal accel == 1.25
                vel = vel_0;
            }
        }else{
            vel = top_speed;
        }
    }

    private void Decelerate() {
        if(vel >= -0.5f*top_speed){
            if (vel < 0) {
                vel *= acceleration;
            } else if (vel > min_vel) {
                vel *= 1 - (acceleration - 1)*6;
                System.out.println("decel: "+(1-(acceleration-1)*2));
            } else {
                vel = -1f*vel_0;
            }
        }else
            //  half top_speed in reverse
            vel = -0.5f*top_speed;
    }

    public void ProcessHit(CollidesHelper.CollisionType collision_type, Vector next_move, Rectangle rect){
        //  takes a parameter of collison type and affects the car based on what it ran into.
        //  reduces the health of the cars hit based on the velocity when the hit occured.
        //  shoudl both cars take damage?
        //  that's easy
        //  just only if they are at max speed..they get (1-0.05)*health off
        //  boost hit are worth 0.15 of health. (1-0.15)*health

        if(collision_type == CollidesHelper.CollisionType.Wall){
            if(isDebugWallBounce)System.out.println("WALL HERE!!!!");
            //  reverses both x and y the same
            //  this should be done in components based on direction of movement
            this.vel *= (-1f)/*1.25f*/;
            //  ratio by which the car is moved away from the wall
            float wall_bounce_factor = 0.015625f;

            //  Get newX and newY
            float newX = next_move.getX();
            if(isDebugWallBounce)System.out.println("Bounce new_X: "+newX);
            float newY = next_move.getY();
            if(isDebugWallBounce)System.out.println("Bounce new_Y: "+newY);

            if(newX > rect.getMaxX())
                this.setX(newX+newX*(-wall_bounce_factor));
            else if(newX < rect.getMinX() && newX > 0f){
                this.setX(newX+newX*(wall_bounce_factor));
            }else if (newX < 0f){
                if(isDebugWallBounce)System.out.println("In the X NEG!! "+newX);
                newX = 1f;
                this.setX(newX+newX*(wall_bounce_factor));
                this.setY(newY);
            }

            else if(newY > SuperRacyFutbol3000.HEIGHT/2f)
                this.setY(newY+newY*(-wall_bounce_factor));
            else if (newY < SuperRacyFutbol3000.HEIGHT/2f && newY > 0f){
                this.setY(newY+newY*(wall_bounce_factor));
            }

            else if (newY < 0f){
                newY = 1f;
                if(isDebugWallBounce)System.out.println("In the Y NEG!! "+newY);
                this.setY(newY+newY*(wall_bounce_factor));
                this.setX(newX);
            }

        }else
            System.out.println("OtherHitType");
    }
}
