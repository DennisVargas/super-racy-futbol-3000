package com.dv.superracyfutbol3000;

import org.newdawn.slick.Input;
import org.newdawn.slick.state.BasicGameState;

public abstract class MenuBase extends BasicGameState{
    int stateID = -1;
    Input input;
    float mouseX, mouseY;
    protected int w = SuperRacyFutbol3000.WIDTH;
    protected int h = SuperRacyFutbol3000.HEIGHT;
    protected float s = SuperRacyFutbol3000.SCALE;

    protected boolean isMouseHover(float minX, float minY, float maxX, float maxY, float mouseX, float mouseY){
        if( mouseX >= minX && mouseX <= maxX){
            return (mouseY >= minY && mouseY <= maxY);
        }else
            return false;
    }
}
