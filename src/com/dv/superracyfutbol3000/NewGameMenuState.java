package com.dv.superracyfutbol3000;

import jig.ResourceManager;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class NewGameMenuState extends MenuBase {
    Image background = null;
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
    background = ResourceManager.getImage(SuperRacyFutbol3000.new_game_menu_bkgrnd_rsc);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Rectangle rect = new Rectangle(0,0,this.w*s,this.h*s);
        graphics.fill(rect);
        graphics.setColor(Color.orange);
        background.draw();
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
