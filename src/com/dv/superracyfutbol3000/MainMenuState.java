package com.dv.superracyfutbol3000;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
    int stateID = -1;

    int w = SuperRacyFutbol3000.WIDTH;
    int h = SuperRacyFutbol3000.HEIGHT;
    float s = SuperRacyFutbol3000.SCALE;

    Image new_game_img = null;
    Image options_img = null;
    Image quit_img = null;

    public MainMenuState(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }
}
