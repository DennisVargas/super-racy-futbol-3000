package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.ResourceManager;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.HashMap;
import java.util.Map;

public class MainMenuState extends BasicGameState {
    int stateID = -1;

    private int w = SuperRacyFutbol3000.WIDTH;
    private int h = SuperRacyFutbol3000.HEIGHT;
    private float s = SuperRacyFutbol3000.SCALE;

    //  Setup initial locations and Spacing variables for menu seletions
    private float mid_selectionY = ((h/2f)+32f)*s;    /*options selection is the 2nd so
                                            this should be centered and new game
                                            and quit offset from there.*/
    private float menu_item_spacing = 160*s;
    private float left_justified_x = (w*s)*(19f/64f);

    private boolean on_new_game = false;
    private boolean on_options = false;
    private boolean on_quit = false;
    private boolean white_new_game = false;
    private boolean white_options = false;
    private boolean white_quit = false;


    private Image background = null;
    private MenuItem new_game_ent;
    private MenuItem options_ent;
    private MenuItem quit_ent;


    public MainMenuState(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        background = ResourceManager.getImage(SuperRacyFutbol3000.main_menu_bkgrnd_rsc);
        //  initialize Menu entities
        new_game_ent = new MenuItem(left_justified_x,mid_selectionY-menu_item_spacing,
                SuperRacyFutbol3000.main_menu_new_game3_rsc,SuperRacyFutbol3000.main_menu_new_game4_rsc);
        options_ent = new MenuItem(left_justified_x,mid_selectionY,
                SuperRacyFutbol3000.main_menu_options3_rsc, SuperRacyFutbol3000.main_menu_options4_rsc);
        quit_ent = new MenuItem(left_justified_x,(mid_selectionY + menu_item_spacing),
                SuperRacyFutbol3000.main_menu_quit3_rsc, SuperRacyFutbol3000.main_menu_quit4_rsc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

//      Draw The Background Image
        background.draw(0,0,s);
//      Is new game white
        white_new_game = new_game_ent.SwapImage( on_new_game, white_new_game);

//      Is new game white
        white_options = options_ent.SwapImage( on_options, white_options);

//      Is quit game white
        white_quit = quit_ent.SwapImage( on_quit, white_quit);

    //  render menu items
        new_game_ent.render(g);
        options_ent.render(g);
        quit_ent.render(g);
    }

//    private boolean SwapImage(Entity e, boolean on_flag, boolean white_flag, String item_name) {
//        if (on_flag && !white_flag){
//            //  then remove grey quit
//            e.removeImage(ResourceManager.getImage( menu_item_image_paths.get(item_name+"_off")));
//            //  add white quit
//            e.addImageWithBoundingBox(ResourceManager.getImage(menu_item_image_paths.get(item_name+"_on")));
//            return true;
//        }else if (white_flag && !on_flag){
//            //  remove white quit
//            e.removeImage(ResourceManager.getImage(menu_item_image_paths.get(item_name+"_on")));
//            //  add grey quit
//            e.addImageWithBoundingBox(ResourceManager.getImage(menu_item_image_paths.get(item_name+"_off")));
//            return false;
//        }
//        return white_flag;
//    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();
//      Check if mouse is over new game
        on_new_game = isMouseHover(new_game_ent.getCoarseGrainedMinX(),new_game_ent.getCoarseGrainedMinY(),
                new_game_ent.getCoarseGrainedMaxX(),new_game_ent.getCoarseGrainedMaxY(),mouseX,mouseY);
//      Check if mouse is over options
        on_options = isMouseHover(options_ent.getCoarseGrainedMinX(),options_ent.getCoarseGrainedMinY(),
                options_ent.getCoarseGrainedMaxX(),options_ent.getCoarseGrainedMaxY(),mouseX,mouseY);
//      Check if mouse is over quit
        on_quit = isMouseHover(quit_ent.getCoarseGrainedMinX(),quit_ent.getCoarseGrainedMinY(),
                quit_ent.getCoarseGrainedMaxX(),quit_ent.getCoarseGrainedMaxY(),mouseX,mouseY);
    }

    public boolean isMouseHover(float minX, float minY, float maxX, float maxY, float mouseX, float mouseY){
        if( mouseX >= minX && mouseX <= maxX){
            return (mouseY >= minY && mouseY <= maxY);
        }else
            return false;
    }
}
