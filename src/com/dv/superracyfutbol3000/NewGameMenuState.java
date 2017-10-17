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
    //  set at center of arrows
    Vector left_arrow_pos = new Vector(835*s,215*s);
    Vector right_arrow_pos = new Vector(1032*s,215*s);

    //  set at upper left corner
    Vector ppt_pos = new Vector(930*s,195*s );

    int players_per_team = 3;
    ArrayList<String> players_per_team_img_path = new ArrayList<String>();
    Image players_per_team_img = null;
    MenuItem left_arrow;
    MenuItem right_arrow;

    boolean on_left_arrow = false;
    boolean white_left_arrow = false;
    boolean on_right_arrow = false;
    boolean white_right_arrow = false;
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
        left_arrow = new MenuItem(left_arrow_pos.getX(),left_arrow_pos.getY(),
                SuperRacyFutbol3000.new_game_grey_left_arrow_rsc,SuperRacyFutbol3000.new_game_white_left_arrow_rsc);
        right_arrow = new MenuItem(right_arrow_pos.getX(),right_arrow_pos.getY(),
                SuperRacyFutbol3000.new_game_grey_right_arrow_rsc, SuperRacyFutbol3000.new_game_white_right_arrow_rsc);
        players_per_team_img = ResourceManager.getImage(players_per_team_img_path.get(players_per_team-1));
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

        white_left_arrow = left_arrow.SwapImage(on_left_arrow, white_left_arrow);
        white_right_arrow = right_arrow.SwapImage(on_right_arrow, white_right_arrow);
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
            }else
                beenClicked = false;
        }else
            on_right_arrow = false;


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
