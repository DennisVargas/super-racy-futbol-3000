package com.dv.superracyfutbol3000;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class PlayState extends BasicGameState {
    int stateID;
    Image background;

    Cars car1;

    //  temporary team arrays...create a team class later
    ArrayList<Cars> red_team = new ArrayList<Cars>();
    ArrayList<Cars> blue_team = new ArrayList<Cars>();

    public PlayState(int stateID) {
        super();
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = ResourceManager.getImage(SuperRacyFutbol3000.play_field_rsc);
//        car1 = new Cars(640f,320f, new );
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw();
     //   car1.render(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        //car1.rotate(-0.5f);
        //car1.translate(0,0.1f);        System.out.println(car1.getRotation());
        Input input = gameContainer.getInput();
//        car1.ProcessInput(input);
//        car1.UpdateCar();
        //car1.translate(Vector.getVector(,1f));
    }
}
