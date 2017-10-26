package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.Vector;
import org.newdawn.slick.geom.Rectangle;


public class Goals extends Entity{
    private static final Vector RED_GOAL_CENTER = new Vector(32,352);
    private static final Vector BLUE_GOAL_CENTER = new Vector(1248,352);
    private static final Vector GOAL_WIDTH_HEIGHT = new Vector(64,320);

    private boolean isRed;
    //  red goal box low right = 64,512
    //               low left = 0,512
    //               upper right64,192
    //               upper left 0,192
    //  blue goal box low right=     1280,512
//                      low left= 1216,512
                    //  up left =  1216,192
    //                  up right =  1280,192

    private Rectangle goalRectangle;


    public Goals(boolean isRed){
        this.isRed = isRed;
    }

    public boolean isRed() {
        return isRed;
    }

    public Vector getRedGoalCENTER() {
        return RED_GOAL_CENTER;
    }

    public Rectangle getRectangle() {
        return goalRectangle;
    }

    public Vector getGOAL_WIDTH_HEIGHT(){
        return GOAL_WIDTH_HEIGHT;
    }
    public void setGoalRectangle() {
        if(isRed)
            this.goalRectangle = new Rectangle(getRedGoalCENTER().getX()-getGOAL_WIDTH_HEIGHT().getX()/2f,
                    getRedGoalCENTER().getY()-getGOAL_WIDTH_HEIGHT().getY()/2f, getGOAL_WIDTH_HEIGHT().getX(),getGOAL_WIDTH_HEIGHT().getY());
        else
            this.goalRectangle = new Rectangle((getBlueGoalCENTER().getX()-getGOAL_WIDTH_HEIGHT().getX()/2f),
                    getBlueGoalCENTER().getY()-getGOAL_WIDTH_HEIGHT().getY()/2f,
                    getGOAL_WIDTH_HEIGHT().getX(),getGOAL_WIDTH_HEIGHT().getY());
    }

    public Vector getBlueGoalCENTER() {
        return BLUE_GOAL_CENTER;
    }

    public void setIsRed(boolean isRed) {
        this.isRed = isRed;
    }

    //  Returns 1 when a point on the circumfence of the ball is on the goal...
    //  only the cardinal directions are calculated.
    public int IsGoal(Vector ball_pos, float radius) {
        if(this.goalRectangle.contains(ball_pos.getX(),ball_pos.getY()))
            return 1;
        if(this.goalRectangle.contains(ball_pos.getX()-radius,ball_pos.getY()))
            return 1;
        if(this.goalRectangle.contains(ball_pos.getX(),ball_pos.getY()+radius))
            return 1;
        if(this.goalRectangle.contains(ball_pos.getX(),ball_pos.getY()-radius))
            return 1;
        else
            return 0;
    }
}
