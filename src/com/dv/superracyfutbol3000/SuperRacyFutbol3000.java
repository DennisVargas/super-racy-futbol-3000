package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.ResourceManager;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class SuperRacyFutbol3000 extends StateBasedGame{
    public static final String NAME = "Super Racy Futbol 3000";
    public static final int WIDTH = 1280;   // 1280x(height = 720)
    public static final int HEIGHT = (WIDTH/16)*9;    // adjust height with 16:9 ratio
    public static final float SCALE = 1f;
    public static boolean isDebug = false;
    public static PlaySettings play_settings = new PlaySettings();
    public static final int MAINMENUSTATE = 0;
    public static final int NEWGAMEMENUSTATE = 1;
    public static final int OPTIONSMENUSTATE = 2;
    public static final int PLAYSTATE = 3;

    public static final String main_menu_bkgrnd_rsc = "com/dv/superracyfutbol3000/gfx/main_menu/MainMenu2.png";
    public static final String main_menu_new_game3_rsc = "com/dv/superracyfutbol3000/gfx/main_menu/new_game3.png";
    public static final String main_menu_new_game4_rsc = "com/dv/superracyfutbol3000/gfx/main_menu/new_game4.png";
    public static final String main_menu_options3_rsc = "com/dv/superracyfutbol3000/gfx/main_menu/options3.png";
    public static final String main_menu_options4_rsc = "com/dv/superracyfutbol3000/gfx/main_menu/options4.png";
    public static final String main_menu_quit3_rsc = "com/dv/superracyfutbol3000/gfx/main_menu/quit3.png";
    public static final String main_menu_quit4_rsc = "com/dv/superracyfutbol3000/gfx/main_menu/quit4.png";
    public static final String new_game_menu_bkgrnd_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/new_game_background.png";
    public static final String new_game_menu_ppt1_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/1.png";
    public static final String new_game_menu_ppt2_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/2.png";
    public static final String new_game_menu_ppt3_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/3.png";
    public static final String new_game_white_left_arrow_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/white_left_arrow1.png";
    public static final String new_game_white_right_arrow_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/white_right_arrow1.png";
    public static final String new_game_grey_left_arrow_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/grey_left_arrow1.png";
    public static final String new_game_grey_right_arrow_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/grey_right_arrow1.png";
    public static final String new_game_grey_back_button_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/grey_back.png";
    public static final String new_game_white_back_button_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/white_back.png";
    public static final String new_game_player1_white_rsc ="com/dv/superracyfutbol3000/gfx/new_game_menu/player1_white.png";
    public static final String new_game_player1_grey_rsc ="com/dv/superracyfutbol3000/gfx/new_game_menu/player1_grey.png";
    public static final String new_game_red_team_selector_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/red_team_selector.png";
    public static final String new_game_blue_team_selector_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/blue_team_selector.png";
    public static final String new_game_grey_start_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/start_game_grey.png";
    public static final String new_game_white_start_rsc = "com/dv/superracyfutbol3000/gfx/new_game_menu/start_game_white.png";
    public static final String play_field_rsc = "com/dv/superracyfutbol3000/gfx/play_state/field.png";
    public static final String cars_blue_rsc = "com/dv/superracyfutbol3000/gfx/cars/blue_car.png";
    public static final String cars_red_rsc = "com/dv/superracyfutbol3000/gfx/cars/red_car.png";

    public SuperRacyFutbol3000() {
        super(NAME);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.addState(new NewGameMenuState(NEWGAMEMENUSTATE));
        this.addState(new PlayState(PLAYSTATE));
        this.addState(new MainMenuState(MAINMENUSTATE));

        this.addState(new OptionsMenuState(OPTIONSMENUSTATE));

        Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE);
        ResourceManager.loadImage(main_menu_bkgrnd_rsc);
        ResourceManager.loadImage(main_menu_new_game3_rsc);
        ResourceManager.loadImage(main_menu_new_game4_rsc);
        ResourceManager.loadImage(main_menu_options3_rsc  );
        ResourceManager.loadImage(main_menu_options4_rsc);
        ResourceManager.loadImage(main_menu_quit3_rsc);
        ResourceManager.loadImage(main_menu_quit4_rsc);
        ResourceManager.loadImage(new_game_menu_bkgrnd_rsc);
        ResourceManager.loadImage(new_game_grey_left_arrow_rsc);
        ResourceManager.loadImage(new_game_grey_right_arrow_rsc);
        ResourceManager.loadImage(new_game_white_left_arrow_rsc);
        ResourceManager.loadImage(new_game_white_right_arrow_rsc);
        ResourceManager.loadImage(new_game_menu_ppt1_rsc);
        ResourceManager.loadImage(new_game_menu_ppt2_rsc);
        ResourceManager.loadImage(new_game_menu_ppt3_rsc);
        ResourceManager.loadImage(new_game_grey_back_button_rsc);
        ResourceManager.loadImage(new_game_white_back_button_rsc);
        ResourceManager.loadImage(new_game_red_team_selector_rsc);
        ResourceManager.loadImage(new_game_blue_team_selector_rsc);
        ResourceManager.loadImage(new_game_player1_grey_rsc);
        ResourceManager.loadImage(new_game_player1_white_rsc);
        ResourceManager.loadImage(new_game_white_start_rsc);
        ResourceManager.loadImage(new_game_grey_start_rsc);
        ResourceManager.loadImage(play_field_rsc);
        ResourceManager.loadImage(cars_blue_rsc);
        ResourceManager.loadImage(cars_red_rsc);
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
