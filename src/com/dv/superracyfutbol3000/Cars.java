package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

import static com.dv.superracyfutbol3000.CollidesHelper.CollisionType;
import static com.dv.superracyfutbol3000.SuperRacyFutbol3000.*;
import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static org.newdawn.slick.util.FastTrig.cos;
import static org.newdawn.slick.util.FastTrig.sin;

public class Cars extends Entity{
    private float wall_bounce_factor = 0.003f;

//  Holds resulting hit vectors that will be added to in the update method
    ArrayList<CollidesHelper.CollisionReport> collision_reports;

    private Vector next_move_location = new Vector(0,0);
    private Vector translate_next_move = new Vector(0,0);

    Quadrant facing_direction = new Quadrant();
    Quadrant moving_direction = new Quadrant();

    Players controlling_player;
    private float health_level = 0f;
    private float boost_level = 0f;

    boolean reverse = false;
    private float mass = 1f;
    private boolean turned = false;
    private double prev_turn_rads;
    private double turn_rads = (PI / 180) * this.getRotation();

    private double turn_increment = PI / 175; // angular acceleration
    private double deg_turn_increment = (180 * turn_increment) / PI;
    private float top_speed = 3.5f;
    private float acceleration = 1.075f;
    private float top_boost_speed = 0f;
    private float max_boost_vel = 0f;



    private double original_jig_rot;
    private double original_turn_rads;

    private float min_vel = 0.25f;
    private float speed = 0.0f;
    private float vel_0 = 0.7f;
    private float friction = 0.90f; // simulates friction, gravity, and mass
    private int player_number = -1; //  controlling player number
    private boolean isRed = false;


    Rectangle car_health_bar;
    Vector next_move_direction = new Vector(0f, 0f);
    private Vector start_location;
    private boolean isDead=false;
    private int second_of_death=-1;
    private int death_timeout = 2;

    public Vector getNext_move_direction() {
        return next_move_direction;
    }

    public double getTurn_degs() {
        return (180/PI)*turn_rads;
    }

    public void ReduceHealth(float impact){
        health_level*=impact;
    }
    public double getOriginal_jig_rot() {
        return original_jig_rot;
    }

    public void setOriginal_jig_rot(float original_jig_rot) {
        this.original_jig_rot = original_jig_rot;
    }

    public double getOriginal_turn_rads() {
        return original_turn_rads;
    }

    public void setOriginal_turn_rads(float original_turn_rads) {
        this.original_turn_rads = original_turn_rads;
    }
    public boolean UpdateDeathStatus(){
        return false;
    }

    public boolean isDead(){
        if(health_level <= 0.01f)
            this.isDead = true;
        else
            this.isDead = false;
        return this.isDead;
    }

    public void setNextDirection(Vector next_direction) {
        this.next_move_direction = next_direction;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void ResetToStart() {
        this.setPosition(this.start_location);
        this.setNextDirection(new Vector(0,0));
    }

    public void setNextTranslation(Vector resultA) {
        this.translate_next_move = resultA;
    }

    enum TurnDirection {Left, Right}

    public Vector getNext_move_location() {
        return next_move_location;
    }

    public double getTurn_rads() {
        return turn_rads;
    }
    public float getSpeed(){
        return speed;
    }

    public float getAcceleration(){
        return acceleration;
    }

    public double getTurnIncrement(){
        return turn_increment;
    }

    public void setTurn_rads(double turn_rads) {
        this.turn_rads = turn_rads;
    }

    public double getTurn_increment() {
        return turn_increment;
    }

    public void setTurn_increment(double turn_increment) {
        this.turn_increment = turn_increment;
    }

    //  Cars Constructor
    public Cars(float x, float y, Players controlling_player) {
        super(x, y);
        this.controlling_player = controlling_player;
        setStartPosition();
        SetCarImage();
        next_move_direction = new Vector(1f,0f);

//        this.debugThis = true;
//        Entity.setDebug(true);
    }

    private void setStartPosition() {
        this.start_location = this.getPosition();
    }

    //public void SetCarImageFromPath(String path){
//    Image img = new Image();
//}
    private void SetCarImage() {
        if(controlling_player.isRed)
            this.addImageWithBoundingBox(ResourceManager.getImage(cars_red_rsc));
            //this.addImage(ResourceManager.getImage(cars_red_rsc));
        else
            this.addImageWithBoundingBox(ResourceManager.getImage(cars_blue_rsc));
        //    this.addImage(ResourceManager.getImage(cars_blue_rsc));
        this.scale(0.67f);  // the image is a little big for the field so scale this down to gain space
      //  SetBoundingBox();
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
                    if (abs(speed) > min_vel)
                        speed *= friction;
                    else
                        speed = 0f;
                }
                if(abs(speed) > 0){
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
                    if (abs(speed) > min_vel)
                        speed *= friction;
                    else
                        speed = 0f;
                }
                if(abs(speed)>0f){
                    if(i.isControllerLeft(controller_num))
                        Steer(TurnDirection.Left);
                    if(i.isControllerRight(controller_num))
                        Steer(TurnDirection.Right);
                }
                break;
        }
    }

    private void Steer(TurnDirection dir) {
        prev_turn_rads = turn_rads;
        if(reverse ){
            if(dir == TurnDirection.Left){
                dir = TurnDirection.Right;
            }else{
                dir = TurnDirection.Left;
            }
        }
        switch(dir){
            case Left :
                turn_rads += turn_increment;
                this.rotate(-deg_turn_increment);
                break;
            case Right:
                turn_rads -= turn_increment;
                this.rotate(deg_turn_increment);
                break;
            default:
                break;
        }
        if(SuperRacyFutbol3000.isTurnDebug){
            System.out.println("turn rads:" + turn_rads);
            System.out.println("turn rad degrees:" + ((180/PI)*turn_rads));
            System.out.println("Entity Degrees: " + this.getRotation());
            System.out.println("Entity Adj Deg: "+ (90 - (this.getRotation())));
        }
        turned = true;
        turn_rads %=2*PI;
    }

    public Vector getTranslate_next_move() {
        return translate_next_move;
    }

    //  GenerateNextMove should be a function
    public void GenerateNextMove(Input input){
        //  input causes turn angle and speed to change
        //  steering will cause a rotation of the entity but not movement
        ProcessInput(input);
        //  set the x and y components of the move direction based upon the rotated angle*speed
        //  direction*scaler

        next_move_direction = new Vector((float) cos(turn_rads),
                                        (float)(sin(turn_rads-PI)));
        if(controlling_player.control_type != Players.Controller.AI && SuperRacyFutbol3000.isTurnDebug)
            if(isDebugMovingDirection)System.out.println("Cars class: car_next_move_direction: "+next_move_direction.getX()+", "+next_move_direction.getY());
        translate_next_move = new Vector ( speed*next_move_direction.getX(), speed*next_move_direction.getY());

        next_move_location = new Vector(this.getX()+speed*next_move_direction.getX(),
                                        this.getY()+speed*next_move_direction.getY());
    }

    //  implements the translation on the next move
    public void UpdateCar(Ellipse goal_ellipse_bounds, Rectangle center_rectangle_bounds){
        this.translate(translate_next_move);
    }


    private void Accelerate() {

        //  Accelerate alters the scaler value of speed by multiplying it by positive
        //  or negative acceleration depending on the motion of the body when accelerate is pressed.
        //  if moving in reverse direction should change along the same axis so and negative 1 is multiplied
        if (reverse){
            reverse = false;
            turn_rads += PI;
//            next_move_direction = next_move_direction.setX((float) (next_move_direction.getX()*-1f));
//            next_move_direction =  next_move_direction.setY((float) (next_move_direction.getY()*-1f));
        }


        if(speed <= top_speed) {
            //  if car is moving
            if (speed > 0 ) {
                speed *= acceleration;
            }
            //  car moving in reverse
            //  change direction
//            else if (speed < -min_vel) {
//                //speed *= (1 - (acceleration - 1) * 6);
//                speed *= 0.95f*acceleration;
//            }
            else {
                //  car is stopped
                //  this is first acceleration
                //  start slow normal accel == 1.25
                speed = vel_0;
            }
        }else{
            speed = top_speed;
        }
    }

    private void Decelerate() {
        //  Like decelerate but seeks to make speed negative instead of the positve that accelerate seeks
        if (!reverse){
            reverse = true;
            turn_rads += PI;
//            next_move_direction = next_move_direction.setX((float) (next_move_direction.getX()*-1f));
//            next_move_direction = next_move_direction.setY((float) (next_move_direction.getY()*-1f));
        }

        if(speed <= 0.6f*top_speed){
            //  already in reverse keep
            if (speed > 0) {
                speed *= acceleration;
            }
//            else if (speed > min_vel) {
//                speed *= -0.95f*acceleration;
//            }
            else {
//                speed = -1f*vel_0;
                speed = vel_0;
            }
        }else
            //  half top_speed in reverse
            speed = 0.6f*top_speed;
    }



}
