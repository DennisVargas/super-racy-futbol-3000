package com.dv.superracyfutbol3000;

import com.sun.corba.se.impl.orbutil.ObjectWriter;
import org.mapeditor.core.ObjectGroup;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.Layer;
import org.newdawn.slick.tiled.TiledMap;
import org.omg.CORBA.Object;
import org.newdawn.slick.XMLPackedSheet.*;

import java.util.ArrayList;

public class MainMenuState extends BasicGameState {
    int stateID = -1;

    int w = SuperRacyFutbol3000.WIDTH;
    int h = SuperRacyFutbol3000.HEIGHT;
    float s = SuperRacyFutbol3000.SCALE;

    int options_y = h/2;    // options selection is the 2nd so
                            // this should be centered and new game and quit offset from there.
    int menu_item_spacing = 128;
    float left_justified_x = (w*s)/8;
    public enum MENU_VIEW {Main, NewGame, Options, Quit}
    MENU_VIEW current_menu_view = MENU_VIEW.Main;
    boolean on_new_game = false;
    Image [] new_game = new Image[2];
    boolean on_options = false;
    Image [] options = new Image[2];
    boolean on_quit = false;
    Image [] quit = new Image[2];
    Image background = null;

    public MainMenuState(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        new_game[0] = new Image("gfx/main_menu/new_game3.png");
        new_game[1] = new Image("gfx/main_menu/new_game4.png");
        options[0] = new Image("gfx/main_menu/options3.png");
        options[1] = new Image("gfx/main_menu/options4.png");
        quit[0] = new Image("gfx/main_menu/quit3.png");
        quit[1] = new Image("gfx/main_menu/quit4.png");
        background = new Image("gfx/main_menu/MainMenu2.png");
//        one_v_one[0] = new Image("gfx/main_menu/1v1_1.png");
//        one_v_one[1] = new Image("gfx/main_menu/1v1_2.png");
//        two_v_two[0] = new Image("gfx/main_menu/2v2_1.png");
//        two_v_two[1] = new Image("gfx/main_menu/2v2_2.png");
//        three_v_three[0] = new Image("gfx/main_menu/3v3_1.png");
//        three_v_three[1] = new Image("gfx/main_menu/3v3_2.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //set background to white
        g.setColor(Color.white);
        g.fillRect(0,0,w*s,h*s);

        background.draw(0,0,s);
        switch(current_menu_view){
            case Main:
                if (on_options)
                    options[1].draw(left_justified_x,options_y*s,s);
                else
                    options[0].draw(left_justified_x, options_y*s,s)
                            /*new_game[0].getHeight()+ new_game_y+ menu_item_spacing,s)*/;
                if (on_new_game)
                    new_game[1].draw(left_justified_x,(options_y-menu_item_spacing)*s,s);
                else
                    new_game[0].draw(left_justified_x,(options_y-menu_item_spacing)*s,s);
                if (on_quit)
                    quit[1].draw(left_justified_x,(options_y +menu_item_spacing)*s,s);
                else
                    quit[0].draw(left_justified_x,(options_y + menu_item_spacing)*s,s);
                break;
            case NewGame:
//                if (on_1v1)
//                    one_v_one[1].draw((w*s)/8, new_game_y,s);
//                else
//                    one_v_one[0].draw((w*s)/8, new_game_y, s);
                break;
        }

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();


        float new_game_minX = (w*s)/8;
        float options_minX = new_game_minX;
        float quit_minX = new_game_minX ;

//        if isMouseHover()
//        if(mouseX >= new_game_minX &&
//                mouseX <=new_game_minX + new_game[0].getWidth()){
//            if (mouseY >= new_game_minX && mouseY <= new_game_minX+new_game[0].getHeight()){
//                System.out.println("y in new game");
//                on_new_game = true;
//                if (input.isMouseButtonDown(0)){
//                    // transition to new_game menu
////                    current_menu_view = MENU_VIEW.NewGame;
//                }
//            }
//            else
//                on_new_game = false;
//        }else
//            on_new_game = false;
//        if(mouseX >= options_minX &&
//                mouseX <= options_minX+options[0].getWidth()){
//            if(mouseY >= new_game_minX+new_game[0].getHeight()+menu_item_spacing &&
//                    mouseY <= new_game_minX+2*new_game[0].getHeight()+menu_item_spacing){
//                System.out.println("y in options");
//                on_options = true;
//            }else
//                on_options = false;
//        }else
//            on_options = false;
//        if(mouseX >= quit_minX &&
//                mouseX <= quit_minX + quit[0].getWidth()){
//            System.out.println("x in quit");
//            if(mouseY >= new_game_minX + 2*(new_game[0].getHeight()+menu_item_spacing)&&
//                    mouseY <= new_game_minX+3*new_game[0].getHeight() + 2*menu_item_spacing){
//                System.out.println("y in quit");
//                on_quit = true;
//                if (input.isMouseButtonDown(0))
//                    System.exit(0);
//            }
//            else
//                on_quit = false;
//        }else
//            on_quit = false;
    }

    public boolean isMouseHover(float minX, float minY, float maxX, float maxY, float mouseX, float mouseY){
        if( mouseX >= minX && mouseX <= maxX){
            if (mouseY >= minY && mouseY <= maxY){
                System.out.println("y in hover");
                return true;
            }
            else
                return false;
        }else
            return false;
    }
}
