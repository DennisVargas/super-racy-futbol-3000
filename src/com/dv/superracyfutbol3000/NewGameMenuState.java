package com.dv.superracyfutbol3000;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class NewGameMenuState extends MenuBase {

    public NewGameMenuState(int stateID) {
        super();
        this.stateID = stateID;
    }

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
        graphics.setColor(Color.orange);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
