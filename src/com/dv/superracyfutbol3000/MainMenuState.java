package com.dv.superracyfutbol3000;

import jig.ResourceManager;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

public class MainMenuState extends MenuBase {

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
    private MenuItem new_game;
    private MenuItem options;
    private MenuItem quit;


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
        //  initialize Menu Items
        new_game = new MenuItem(left_justified_x,mid_selectionY-menu_item_spacing,
                SuperRacyFutbol3000.main_menu_new_game3_rsc,SuperRacyFutbol3000.main_menu_new_game4_rsc);
        options = new MenuItem(left_justified_x,mid_selectionY,
                SuperRacyFutbol3000.main_menu_options3_rsc, SuperRacyFutbol3000.main_menu_options4_rsc);
        quit = new MenuItem(left_justified_x,(mid_selectionY + menu_item_spacing),
                SuperRacyFutbol3000.main_menu_quit3_rsc, SuperRacyFutbol3000.main_menu_quit4_rsc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

//      Draw The Background Image
        background.draw(0,0,s);
//      Is new game white
        white_new_game = new_game.SwapImage( on_new_game, white_new_game);

//      Is new game white
        white_options = options.SwapImage( on_options, white_options);

//      Is quit game white
        white_quit = quit.SwapImage( on_quit, white_quit);

    //  render menu items
        new_game.render(g);
        options.render(g);
        quit.render(g);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        input = gc.getInput();
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();
//      Check if mouse is over new game
        if(on_new_game = isMouseHover(new_game.getCoarseGrainedMinX(), new_game.getCoarseGrainedMinY(),
                new_game.getCoarseGrainedMaxX(), new_game.getCoarseGrainedMaxY(),mouseX,mouseY)){
            if(input.isMouseButtonDown(0))
                sbg.enterState(SuperRacyFutbol3000.NEWGAMEMENUSTATE);
        }

//      Check if mouse is over options
        if(on_options = isMouseHover(options.getCoarseGrainedMinX(), options.getCoarseGrainedMinY(),
                options.getCoarseGrainedMaxX(), options.getCoarseGrainedMaxY(),mouseX,mouseY)){
            if(input.isMouseButtonDown(0))
                sbg.enterState(SuperRacyFutbol3000.OPTIONSMENUSTATE);
        }
//      Check if mouse is over quit
        if(on_quit = isMouseHover(quit.getCoarseGrainedMinX(), quit.getCoarseGrainedMinY(),
                quit.getCoarseGrainedMaxX(), quit.getCoarseGrainedMaxY(),mouseX,mouseY)){
            if(input.isMouseButtonDown(0))
                System.exit(0);
        }

    }
}
