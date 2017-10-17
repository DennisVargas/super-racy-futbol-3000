package com.dv.superracyfutbol3000;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class NewGameMenuState extends MenuBase {
    Image background = null;

    //  set at center of entity
    Vector left_arrow_pos = new Vector(835*s,215*s);
    Vector right_arrow_pos = new Vector(1032*s,215*s);
    Vector back_button_pos = new Vector(90*s,670*s);
    Vector player1__red_pos = new Vector(221*s,445*s);
    Vector player1__blue_pos = new Vector(860*s,445*s);
    Vector red_team_selector_pos = new Vector(318,580);
    Vector blue_team_selector_pos = new Vector(960,580);
    //  set at upper left corner of image
    Vector ppt_pos = new Vector(930*s,195*s );

    int players_per_team = 3;
    ArrayList<String> players_per_team_img_path = new ArrayList<String>();
    Image players_per_team_img = null;
    MenuItem left_arrow;
    MenuItem right_arrow;
    MenuItem back_button;
    MenuItem red_team_selector;
    MenuItem blue_team_selector;
    Image player1_icon;

    boolean on_left_arrow = false;
    boolean white_left_arrow = false;
    boolean on_right_arrow = false;
    boolean white_right_arrow = false;
    boolean on_back_button = false;
    boolean white_back_button = false;
    boolean on_team_selector = false;
    boolean white_team_selector = false;
    boolean isRedTeam = true;

    boolean beenClicked = false;


    public NewGameMenuState(int stateID) {
        super();
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = ResourceManager.getImage(SuperRacyFutbol3000.new_game_menu_bkgrnd_rsc);
        players_per_team_img_path.add(SuperRacyFutbol3000.new_game_menu_ppt1_rsc);
        players_per_team_img_path.add(SuperRacyFutbol3000.new_game_menu_ppt2_rsc);
        players_per_team_img_path.add(SuperRacyFutbol3000.new_game_menu_ppt3_rsc);
        players_per_team_img = ResourceManager.getImage(players_per_team_img_path.get(players_per_team-1));

        left_arrow = new MenuItem(left_arrow_pos.getX(),left_arrow_pos.getY(),
                SuperRacyFutbol3000.new_game_grey_left_arrow_rsc,SuperRacyFutbol3000.new_game_white_left_arrow_rsc);
        right_arrow = new MenuItem(right_arrow_pos.getX(),right_arrow_pos.getY(),
                SuperRacyFutbol3000.new_game_grey_right_arrow_rsc, SuperRacyFutbol3000.new_game_white_right_arrow_rsc);
        back_button = new MenuItem(back_button_pos.getX(),back_button_pos.getY(),
                SuperRacyFutbol3000.new_game_grey_back_button_rsc, SuperRacyFutbol3000.new_game_white_back_button_rsc);

        red_team_selector = new MenuItem(red_team_selector_pos.getX(), red_team_selector_pos.getY(),
                SuperRacyFutbol3000.new_game_red_team_selector_rsc,
                SuperRacyFutbol3000.new_game_red_team_selector_rsc);

        blue_team_selector = new MenuItem(blue_team_selector_pos.getX(), blue_team_selector_pos.getY(),
                SuperRacyFutbol3000.new_game_blue_team_selector_rsc,
                SuperRacyFutbol3000.new_game_blue_team_selector_rsc);

        player1_icon = ResourceManager.getImage(SuperRacyFutbol3000.new_game_player1_white_rsc);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Rectangle rect = new Rectangle(0,0,this.w*s,this.h*s);
        graphics.fill(rect);
        graphics.setColor(Color.orange);
        background.draw();
        players_per_team_img.draw(ppt_pos.getX(),ppt_pos.getY());

        right_arrow.render(graphics);
        left_arrow.render(graphics);
        back_button.render(graphics);
        red_team_selector.render(graphics);
        blue_team_selector.render(graphics);

        white_left_arrow = left_arrow.SwapImage(on_left_arrow, white_left_arrow);
        white_right_arrow = right_arrow.SwapImage(on_right_arrow, white_right_arrow);
        white_back_button = back_button.SwapImage(on_back_button, white_back_button);

        if (isRedTeam)
            player1_icon.draw(player1__red_pos.getX(), player1__red_pos.getY());
        else
            player1_icon.draw(player1__blue_pos.getX(), player1__blue_pos.getY());
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        input = gameContainer.getInput();
        mouseX = input.getMouseX();
        mouseY = input.getMouseY();

        //if( SuperRacyFutbol3000.isDebug )System.out.println("mouseX: "+mouseX +" mouseY:"+mouseY);

        //  Check for mouse hover over the left arrow
        if (isMouseHover(left_arrow.getCoarseGrainedMinX(),left_arrow.getCoarseGrainedMinY(),
                left_arrow.getCoarseGrainedMaxX(),left_arrow.getCoarseGrainedMaxY(),mouseX,mouseY)){
            if(SuperRacyFutbol3000.isDebug)System.out.println("Left Arrow Hover!!");
            on_left_arrow = true;
            //  if left arrow pressed with left click only decrement on first click
            if(input.isMousePressed(0)&& !beenClicked){
                beenClicked = true;
                DecrementPlayersPerTeam();
            }else
                beenClicked = false;
        }else
            on_left_arrow = false;

        //  check for mouse hover over the right arrow
        if (isMouseHover(right_arrow.getCoarseGrainedMinX(),right_arrow.getCoarseGrainedMinY(),
                right_arrow.getCoarseGrainedMaxX(),right_arrow.getCoarseGrainedMaxY(),mouseX,mouseY)){
            if(SuperRacyFutbol3000.isDebug)System.out.println("Right Arrow Hover!!");
            on_right_arrow = true;
            //  if left arrow pressed with left click only decrement on first click
            if(input.isMousePressed(0)&& !beenClicked){
                beenClicked = true;
                IncrementPlayersPerTeam();
            }else if(!input.isMousePressed(0)&& beenClicked)
                beenClicked = false;
        }else
            on_right_arrow = false;

        //  check for mouse hover over the back button
        if (isMouseHover(back_button.getCoarseGrainedMinX(),back_button.getCoarseGrainedMinY(),
                back_button.getCoarseGrainedMaxX(),back_button.getCoarseGrainedMaxY(),mouseX,mouseY)){
            if(SuperRacyFutbol3000.isDebug)System.out.println("Back Button Hover!!");
            on_back_button = true;
            //  if left arrow pressed with left click only decrement on first click
            if(input.isMousePressed(0)){
                stateBasedGame.enterState(SuperRacyFutbol3000.MAINMENUSTATE);
            }
        }else
            on_back_button = false;

        //  check for mouse hover over the team selection region
        if (isMouseHover(red_team_selector.getCoarseGrainedMinX(),red_team_selector.getCoarseGrainedMinY(),
                red_team_selector.getCoarseGrainedMaxX(), red_team_selector.getCoarseGrainedMaxY(), mouseX, mouseY)){
            if(input.isMouseButtonDown(0) && !beenClicked){
                isRedTeam = true;
                beenClicked = true;
            }
            else if(!input.isMouseButtonDown(0)&&beenClicked)
                beenClicked = false;
        }
        if (isMouseHover(blue_team_selector.getCoarseGrainedMinX(), blue_team_selector.getCoarseGrainedMinY(),
                blue_team_selector.getCoarseGrainedMaxX(), blue_team_selector.getCoarseGrainedMaxY(), mouseX, mouseY)){
            if(input.isMouseButtonDown(0)&&!beenClicked){
                isRedTeam = false;
                beenClicked = true;
            }else if (!input.isMouseButtonDown(0)&&beenClicked)
                beenClicked = false;
        }
    }

    private void IncrementPlayersPerTeam() {
        //  increase the number of players per team by one and changes the graphic of the image being
        // rendered to the appropriate number.
        if(players_per_team < 3){
            players_per_team++;
            players_per_team_img = ResourceManager.getImage(players_per_team_img_path.get(players_per_team-1));
        }
    }

    private void DecrementPlayersPerTeam() {
        //  decrease the number of players per team by one and changes the graphic of the image being
        // rendered to the appropriate number.
        if(players_per_team > 1){
            players_per_team--;
            players_per_team_img = ResourceManager.getImage(players_per_team_img_path.get(players_per_team-1));
        }
    }
}
