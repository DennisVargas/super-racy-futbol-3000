package com.dv.superracyfutbol3000;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SuperRacyFutbol3000 extends StateBasedGame{
    public static final String NAME = "Super Racy Futbol 3000";
    public static final int WIDTH = 1280;   // 1280x(height = 720)
    public static final int HEIGHT = (WIDTH/16)*9;    // adjust height with 16:9 ratio
    public static final float SCALE = 1.0f;

    public SuperRacyFutbol3000() {
        super(NAME);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {

    }

    public static void main(String[] args) throws SlickException{
        AppGameContainer app = new AppGameContainer(new SuperRacyFutbol3000());
        app.setDisplayMode((int)(WIDTH*SCALE), (int)(HEIGHT*SCALE),false);
        app.setVSync(true);
        app.start();
    }
}
