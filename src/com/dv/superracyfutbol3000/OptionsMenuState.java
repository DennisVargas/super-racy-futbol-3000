package com.dv.superracyfutbol3000;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class OptionsMenuState extends MenuBase {
    public OptionsMenuState(int stateID) {
        super();
        this.stateID = stateID;
    }

    //
    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Rectangle rect = new Rectangle(0,0,this.w*s,this.h*s);
        graphics.fill(rect);
        graphics.setColor(Color.cyan);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
