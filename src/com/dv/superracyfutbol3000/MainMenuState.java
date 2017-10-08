package com.dv.superracyfutbol3000;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends BasicGameState {
    int stateID = -1;


    int w = SuperRacyFutbol3000.WIDTH;
    int h = SuperRacyFutbol3000.HEIGHT;
    float s = SuperRacyFutbol3000.SCALE;

    int new_game_y = 256;
    int menu_item_spacing = 30;

    Image new_game_img = null;
    Image options_img = null;
    Image quit_img = null;
    Image select_hl_img = null;

    public MainMenuState(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        new_game_img = new Image("gfx/main_menu/new_game.png");
        options_img = new Image("gfx/main_menu/options.png");
        quit_img = new Image("gfx/main_menu/quit.png");
        select_hl_img = new Image("gfx/main_menu/highlight.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //  set background to white
        g.setColor(Color.white);
        g.fillRect(0,0,w*s,h*s);


        new_game_img.draw((w*s)/2-new_game_img.getWidth()/2,new_game_y,s);
        options_img.draw((w*s)/2-new_game_img.getWidth()/2,new_game_img.getHeight()+ new_game_y+ menu_item_spacing,s);
        quit_img.draw((w*s)/2-new_game_img.getWidth()/2,
                2*(new_game_img.getHeight()+menu_item_spacing)+new_game_y,s);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        float new_game_minX = (w*s)/2-new_game_img.getWidth()/2;
        if(mouseX >= new_game_minX &&
                mouseX <=new_game_minX + new_game_img.getWidth()){
            System.out.println("over new game");
        }
    }
}
