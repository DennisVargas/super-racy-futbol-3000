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
    int menu_item_spacing = 90;

    boolean on_new_game = false;
    Image [] new_game = new Image[2];
    boolean on_options = false;
    Image [] options = new Image[2];
    boolean on_quit = false;
    Image [] quit = new Image[2];
    Image background = null;
    //Image select_hl_img = null;

    public MainMenuState(int stateID){
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return stateID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        new_game[0] = new Image("gfx/main_menu/new_game1.png");
        new_game[1] = new Image("gfx/main_menu/new_game2.png");
        options[0] = new Image("gfx/main_menu/options1.png");
        options[1] = new Image("gfx/main_menu/options2.png");
        quit[0] = new Image("gfx/main_menu/quit1.png");
        quit[1] = new Image("gfx/main_menu/quit2.png");
        background = new Image("gfx/main_menu/MainMenu.png");
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //  set background to white
        g.setColor(Color.white);
        g.fillRect(0,0,w*s,h*s);

        background.draw(0,0,s);
        if (on_new_game)
            new_game[0].draw((w*s)/2-new_game[0].getWidth()/2,new_game_y,s);
        else
            new_game[1].draw((w*s)/2-new_game[1].getWidth()/2,new_game_y,s);
        if (on_options)
            options[0].draw((w*s)/2-new_game[0].getWidth()/2,
                    new_game[0].getHeight()+ new_game_y+ menu_item_spacing,s);
        else
            options[1].draw((w*s)/2-new_game[0].getWidth()/2,
                    new_game[0].getHeight()+ new_game_y+ menu_item_spacing,s);
        if (on_quit)
            quit[0].draw((w*s)/2-new_game[0].getWidth()/2,
                     new_game_y+ 2*(new_game[0].getHeight()+menu_item_spacing),s);
        else
            quit[1].draw((w*s)/2-new_game[0].getWidth()/2,
                     new_game_y+ 2*(new_game[0].getHeight()+ menu_item_spacing),s);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        float new_game_minX = (w*s)/2-new_game[0].getWidth()/2;
        float options_minX = (w*s)/2 - options[0].getWidth()/2;
        float quit_minX = (w*s)/2 - quit[0].getWidth()/2;

        if(mouseX >= new_game_minX &&
                mouseX <=new_game_minX + new_game[0].getWidth()){
            if (mouseY >= new_game_y && mouseY <= new_game_y+new_game[0].getHeight()){
                System.out.println("y in new game");
                on_new_game = true;
            }
            else
                on_new_game = false;
        }else
            on_new_game = false;
        if(mouseX >= options_minX+30 &&
                mouseX <= options_minX-30+options[0].getWidth()){
            if(mouseY >= new_game_y+new_game[0].getHeight()+menu_item_spacing &&
                    mouseY <= new_game_y+2*new_game[0].getHeight()+menu_item_spacing){
                System.out.println("y in options");
                on_options = true;
            }else
                on_options = false;
        }else
            on_options = false;
        if(mouseX >= quit_minX+60 &&
                mouseX <= quit_minX-60+quit[0].getWidth()){
            if(mouseY >= new_game_y + 2*(new_game[0].getHeight()+menu_item_spacing)&&
                    mouseY <= new_game_y+3*new_game[0].getHeight()+2*menu_item_spacing){
                System.out.println("y in quit");
                on_quit = true;
            }
            else
                on_quit = false;
        }else
            on_quit = false;
    }
}
