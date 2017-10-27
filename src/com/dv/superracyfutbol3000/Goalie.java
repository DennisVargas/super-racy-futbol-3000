package com.dv.superracyfutbol3000;
import jig.ConvexPolygon;
import jig.Entity;
import jig.Vector;
import org.lwjgl.Sys;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import static java.lang.Math.abs;


public class Goalie extends Entity{

    private float speed = 1.5f;

    //  Goalies range of Y : min = 192; max = 512
    private static int GOLIE_MINY=192;
    private static int GOLIE_MAXY=512;

    private Vector next_direction = new Vector(0,1);
    private Vector next_translation = new Vector(0,0);

    private Vector next_position = new Vector(0,0);

    //  Used to cheat and get good...also to track the
    // amount of time the ball is in the same place
    // after a time the goalie closest will reset it with a shot on other teams goal.

    private Vector last_ball_position = new Vector(0,0);
    private int is_stuck_start_time = -1;

    Rectangle goalie_rect;

    private int is_stuck_time_out = 8;


    public void setIs_stuck_start_time(int is_stuck_start_time) {
        this.is_stuck_start_time = is_stuck_start_time;
    }

    public int getIs_stuck_time_out() {
        return is_stuck_time_out;
    }
    public static int getGolieMiny() {
        return GOLIE_MINY;
    }

    public static void setGolieMiny(int golieMiny) {
        GOLIE_MINY = golieMiny;
    }

    public static int getGolieMaxy() {
        return GOLIE_MAXY;
    }

    public static void setGolieMaxy(int golieMaxy) {
        GOLIE_MAXY = golieMaxy;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getIs_stuck_start_time() {
        return is_stuck_start_time;
    }


    public Rectangle getGoalie_rect() {
        return goalie_rect;
    }

    public void setGoalie_rect(Rectangle goalie_rect) {
        this.goalie_rect = goalie_rect;
    }

    public Vector getNext_direction() {

        return next_direction;
    }

    public void setNext_direction(Vector next_direction) {
        this.next_direction = next_direction;
    }

    public Vector getNext_translation() {
        return next_translation;
    }

    public void setNext_translation(Vector next_translation) {
        this.next_translation = next_translation;
    }

    public Vector getNext_position() {
        return next_position;
    }

    public void setNext_position(Vector next_position) {
        this.next_position = next_position;
    }

    public Vector getLast_ball_position() {
        return last_ball_position;
    }

    public void setLast_ball_position(Vector last_ball_position) {
        this.last_ball_position = last_ball_position;
    }

    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    private boolean isRed;



    public Goalie(Vector position) {
        super(position);
    }

    public Goalie(float x, float y, boolean isRed) {
        super(x, y);
        this.setX(x);
        this.setY(y);
        this.addShape(new ConvexPolygon(20f,50f));
        this.isRed = isRed;
        goalie_rect = new Rectangle(x-10, y-25, 20,50);
//        this.debugThis = true;
    }

    public Goalie(boolean isRed) {
        super();

    }



    public Vector TrackBallSetDirection(Vector ball_position, Vector ball_next_move_direction) {
        //  Take the difference of ballY - 360 %2

        //  current ball position
        float cur_ball_y =ball_position.getY();
        //  current heading
        float cur_ball_y_dir = ball_next_move_direction.getY();
        float cur_goalie_y = this.getPosition().getY();

        if(ball_position.getY() < this.getY()){
            this.setNext_direction(new Vector(0,-1f));
        } else if (ball_position.getY()>this.getY()){
            this.setNext_direction(new Vector(0,1f));
        } else if (ball_position.getY() <= this.getY()+this.getGoalie_rect().getHeight()/2
                && ball_position.getY() >= this.getY()-this.getGoalie_rect().getHeight()/2){
            this.setNext_direction(new Vector(0,0));
        }


//        if(cur_ball_y  < cur_goalie_y+1 && cur_ball_y> cur_goalie_y-1)
//            this.setNext_direction(new Vector(0,0));
//        else if(cur_ball_y < 360f ){
//            //  set goalie neg y dir
//            this.setNext_direction(new Vector(0,-1f));
//        }else if( cur_ball_y > 360f){
//            //  set goalie pos y dir
//            this.setNext_direction(new Vector(0,1f));
//        }else if( cur_ball_y == 360f){
//            //  if goalie y less than 360
//            if((cur_goalie_y >cur_ball_y))
//                this.setNext_direction(new Vector(0,-1));
//            else if((cur_goalie_y - cur_ball_y)<0)
//                this.setNext_direction(new Vector(0,1));
//        }
        return this.next_direction;
    }

    //  Time should be passed in seconds.
    public boolean IsBallStuck(Vector cur_ball_position, int time){
        Vector ball_diff = this.last_ball_position.subtract(cur_ball_position);

        if (abs(ball_diff.getX()) <= 0.00001f && abs(ball_diff.getY())<=0.00001f){
            if (getIs_stuck_start_time()<0){
                setIs_stuck_start_time(time);
                setLast_ball_position(cur_ball_position);
                return false;
            }
            //  ball hasn't moved in "time_out" seconds
            //System.out.println("IS Stuck Timer: "+(time - getIs_stuck_start_time()));
            if((time - getIs_stuck_start_time()) >= is_stuck_time_out) {
                //  reset is_stuck timer to current time and return true
                setIs_stuck_start_time(-1);
                return true;
            }else
                this.last_ball_position = cur_ball_position;
                return false;
        }else{
            //  ball has moved reset the timer
            if (getIs_stuck_start_time()>=0)
                setIs_stuck_start_time(-1);
            this.last_ball_position = cur_ball_position;
            return false;
        }
    }

    public void setIs_stuck_time_out(int is_stuck_time_out) {
        this.is_stuck_time_out = is_stuck_time_out;
    }

    public Vector GenerateNextTranslation() {
        this.next_translation = new Vector(0,speed*next_direction.getY());
        CalculateNextGoaliePosition();
        return this.next_translation;
    }

    public void UpdateGoaliePosition() {
        //  Check that next move stays within bounds
        if(this.next_position.getY() < GOLIE_MAXY
                && this.next_position.getY() > GOLIE_MINY){
            if(SuperRacyFutbol3000.isGoalieDebug)System.out.println(this.next_translation+", "+this.next_translation);
            //  translate goalie entity
            this.translate(this.next_translation);

            //  update the draw location of rectangle
            goalie_rect.setLocation(goalie_rect.getX(),goalie_rect.getY()+this.next_translation.getY());
        }

    }

    private Vector CalculateNextGoaliePosition() {
        this.next_position = new Vector(this.getX()+this.next_translation.getX(),this.getY()
                +this.next_translation.getY());
        return this.next_position;
    }
}

