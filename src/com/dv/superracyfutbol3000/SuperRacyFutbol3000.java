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
    public static boolean isDebug = false;
    public static final int MAINMENUSTATE = 0;

    private String main_menu_bkgrnd_rsc = "gfx/main_menu/MainMenu2.png";
    private String main_menu_new_game3_rsc = "gfx/main_menu/new_game3.png";
    private String main_menu_new_game4_rsc = "gfx/main_menu/new_game4.png";
    private String main_menu_options3_rsc = "gfx/main_menu/options3.png";
    private String main_menu_options4_rsc = "gfx/main_menu/options4.png";
    private String main_menu_quit3_rsc = "gfx/main_menu/quit3.png";
    private String main_menu_quit4_rsc = "gfx/main_menu/quit4.png";

    public SuperRacyFutbol3000() {
        super(NAME);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new MainMenuState(MAINMENUSTATE));
    }

    public static void main(String[] args) throws SlickException{
        if (CheckDebug(args))
            isDebug= true;
        AppGameContainer app = new AppGameContainer(new SuperRacyFutbol3000());
        app.setDisplayMode((int)(WIDTH*SCALE), (int)(HEIGHT*SCALE),false);
        app.setVSync(true);
        app.start();
    }

    private static boolean CheckDebug(String[] args) {
        for(String x:args){
            if (x.contentEquals("-d") )
                return true;
        }
        return false;
    }
}
