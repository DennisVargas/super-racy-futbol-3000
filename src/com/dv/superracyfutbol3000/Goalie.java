package com.dv.superracyfutbol3000;
import jig.ConvexPolygon;
import jig.Entity;
import jig.Vector;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;


public class Goalie extends Entity{

    private float speed = 0.5f;

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

    //  Goalies range of Y : min = 192; max = 512
    private static int GOLIE_MINY=192;
    private static int GOLIE_MAXY=512;

    private Vector next_direction = new Vector(0,1);
    private Vector next_translation = new Vector(0,0);

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getNo_move_start_time() {
        return no_move_start_time;
    }

    public void setNo_move_start_time(float no_move_start_time) {
        this.no_move_start_time = no_move_start_time;
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

    private Vector next_position = new Vector(0,0);

    //  Used to cheat and get good...also to track the
    // amount of time the ball is in the same place
    // after a time the goalie closest will reset it with a shot on other teams goal.
    private Vector last_ball_position = new Vector(0,0);
    private Vector predict_ball_position = new Vector(0,0);
    private float no_move_start_time = 0f;

//    private float
    public boolean isRed() {
        return isRed;
    }

    public void setRed(boolean red) {
        isRed = red;
    }

    private boolean isRed;


//    Rectangle red_rect = new Rectangle(64,360, 20,50);
//    Rectangle blue_rect; = new Rectangle(1000,360,20,50);

    Rectangle goalie_rect;
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
        this.debugThis = true;
    }

    public Goalie(boolean isRed) {
        super();

    }

    void RenderGoalie(Graphics g){

    }


    public void TrackBall(Vector ball_position, Vector ball_next_move_direction) {
        //  current ball position
        float cur_ball_y =ball_position.getY();
        //  current heading
        float cur_ball_y_dir = ball_next_move_direction.getY();

        if(cur_ball_y -192f <= 0f ){
            //  set goalie neg y dir
            this.setNext_direction(new Vector(0,-1f));
        }else if( 512-cur_ball_y<=0f){
            //  set goalie pos y dir
            this.setNext_direction(new Vector(0,1f));
        }
    }

    public boolean IsBallStuck(Vector cur_ball_position, int time){
        Vector ball_diff = this.last_ball_position.subtract(cur_ball_position);
        if (ball_diff.getX() <= 0.001f){
            if(ball_diff.getY()<=0.001f){
                if (getNo_move_start_time()== 0f)
                    no_move_start_time = time;
                if((time - getNo_move_start_time())/1000f >= 10f)
                    return true;
                else
                    return false;
            }
        }else{
            return false;
        }
        return false;
    }
}

