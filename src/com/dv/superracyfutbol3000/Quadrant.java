package com.dv.superracyfutbol3000;


import static java.lang.Math.PI;
import static java.lang.StrictMath.abs;

public class Quadrant {
    public void InvertMovement() {
        this.setDy(this.getDy()*-1f);
        this.setDx(this.getDx()*-1f);
    }

    enum QuadrantLabel {no_q,first_q, second_q, third_q, fourth_q}   // enumerates the quadrants
    // of the unit circle for direction handling
    private float dx,dy;
    private QuadrantLabel quadrant_label;

    public QuadrantLabel getQuadrant_label() {
        return quadrant_label;
    }
    public void setQuadrant_label(QuadrantLabel quadrant_label) {
        this.quadrant_label = quadrant_label;
    }
    public float getDx() {
        return dx;
    }
    public void setDx(float dx) {
        this.dx = dx;
    }
    public float getDy() {
        return dy;
    }
    public void setDy(float dy) {
        this.dy = dy;
    }

//  custom constructor
    public Quadrant(float dx, float dy, QuadrantLabel ql) {
        setDx( dx);
        setDy(dy);
        setLabel(ql);
    }
//  default constructor
    public Quadrant() {
        setDx( 0f);
        setDy(0f);
        setLabel(QuadrantLabel.no_q);
    }

    private void setLabel(QuadrantLabel label) {
        quadrant_label = label;
    }

    public Quadrant Get_Direction_Vector(float turning_angle){
       Quadrant q = new Quadrant();

       if (turning_angle < 0f )
           turning_angle = turning_angle +360;
       if ((0f <= turning_angle && turning_angle <= 90f)
               ||(-270f>= turning_angle && turning_angle<= -360f)){
           q.setDx(1f);
           q.setDy(-1f);
           q.setLabel(QuadrantLabel.first_q);
       }else if((90f <= turning_angle && turning_angle <= 180f)
               ||(-180f>= turning_angle && turning_angle<= -270f)){
           q.setDx(-1f);
           q.setDy(-1f);
           q.setLabel(QuadrantLabel.second_q);
       }else if ((180f <= turning_angle && turning_angle <= 270f)
           ||(-90f >= turning_angle && turning_angle<= -180f)){
           q.setDx(-1f);
           q.setDy(1f);
           q.setLabel(QuadrantLabel.third_q);
       }else if ((270f <= turning_angle && turning_angle <= 360f)
               ||(0f >= turning_angle && turning_angle <= -90f)){
           q.setDx(1f);
           q.setDy(1f);
           q.setLabel(QuadrantLabel.fourth_q);
       }
       return q;
    }

    //  determines a quadrant label based on a given Dx and Dy
    public void DetermineLabel(){
        if(dx < 0){
            if (dy <0)
                this.quadrant_label = QuadrantLabel.second_q;
            else
                this.quadrant_label = QuadrantLabel.third_q;
        }else if (dx > 0){// dx greater than zero
            if (dy < 0)
                this. quadrant_label = QuadrantLabel.first_q;
            else
                this.quadrant_label = QuadrantLabel.fourth_q;
        }else{
            System.out.println("NO label");
        }
    }

    public Quadrant GetMovingDirection(Quadrant forward_facing_direction, float x_vel, float y_vel){
        //  get the change in x and y

        //  get the current heading of the car
        Quadrant moving_direction = new Quadrant(forward_facing_direction.getDx(),
                forward_facing_direction.getDy(), forward_facing_direction.getQuadrant_label());

        // check if driving in reverse
        // the direction i'm pointing isn't the one being moved in

        //  if x is decreasing in x displacement yet facing the increasing direction
        if (forward_facing_direction.getDx()> 0 && x_vel <0){
            System.out.println("reverse, X decreasing from " + forward_facing_direction.quadrant_label);
            moving_direction.setDx(-1f);
        }
        //  if x is increasing in x displacement yet facing the decreasing direction
        else if (forward_facing_direction.getDx()< 0 && x_vel >0){
            System.out.println("reverse, X increasing from " + forward_facing_direction.quadrant_label);
            moving_direction.setDx(1f);
            moving_direction.DetermineLabel();
        }
        //  if y is decreasing yet facing the increasing direction
        if(forward_facing_direction.getDy()> 0 && y_vel <0){
            System.out.println("reverse, Y decreasing from " + forward_facing_direction.quadrant_label);
            moving_direction.setDy(-1f);
        }
        //  y is increasing yet facing decreasing direction
        else if(forward_facing_direction.getDy() < 0 && y_vel >0){
            System.out.println("reverse, Y increasing " + forward_facing_direction.quadrant_label);
            moving_direction.setDy(1f);
        }
        if(SuperRacyFutbol3000.isDebugMovingDirection) {
            System.out.println("Facing Direction: " + moving_direction.getQuadrant_label());
            System.out.println("Moving Direction: " + forward_facing_direction.getQuadrant_label());
        }
        return moving_direction;
    }


}
