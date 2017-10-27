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
    public static boolean isWallDebug = false;
    public static boolean isDebugWallBounce = false;
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
    public static final String num_1_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/1.png";
    public static final String num_2_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/2.png";
    public static final String num_3_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/3.png";
    public static final String num_4_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/4.png";
    public static final String num_5_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/5.png";
    public static final String num_6_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/6.png";
    public static final String num_7_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/7.png";
    public static final String num_8_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/8.png";
    public static final String num_9_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/9.png";
    public static final String num_0_rsc = "com/dv/superracyfutbol3000/gfx/play_state/Score/0.png";
    public static boolean isDebugCarCreation = false;
    public static boolean isDebugCarExtents = false;
    public static boolean isMouseDebug= false;
    public static boolean isVelocityDebug;
    public static boolean isDebugMovingDirection;
    public static boolean isDebugField;
    public static boolean isDebugRotationTest;
    public static boolean isTurnDebug;
    public static boolean isQuadrantDebug;
    public static boolean isGoalieDebug;
    public static boolean isScoreDebug;

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
        ResourceManager.loadImage(num_1_rsc);
        ResourceManager.loadImage(num_2_rsc);
        ResourceManager.loadImage(num_3_rsc);
        ResourceManager.loadImage(num_4_rsc);
        ResourceManager.loadImage(num_5_rsc);
        ResourceManager.loadImage(num_6_rsc);
        ResourceManager.loadImage(num_7_rsc);
        ResourceManager.loadImage(num_8_rsc);
        ResourceManager.loadImage(num_9_rsc);
        ResourceManager.loadImage(num_0_rsc);
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
            if (x.contains("-d") ){

                if(x.contains("-dw")){
                    if(x.contains("-dwB"))
                        isDebugWallBounce = true;
                    else
                        isWallDebug = true;
                }
                if(x.contains("m"))
                    isMouseDebug = true;
                if(x.contains("cE"))
                    isDebugCarExtents = true;
                if(x.contains("v"))
                    isVelocityDebug = true;
                if(x.contains("f"))
                    isDebugField = true;
                if(x.contains("rot"))
                    isDebugRotationTest = true;
                if(x.contains("Mvd"))
                    isDebugMovingDirection = true;
                if(x.contains("td"))
                    isTurnDebug = true;
                if(x.contains("q"))
                    isQuadrantDebug = true;
                if(x.contains("gd"))
                    isGoalieDebug = true;
                else
                    isDebug = true;
            }
        }
        return false;
    }
}
