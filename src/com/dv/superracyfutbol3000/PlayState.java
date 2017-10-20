package com.dv.superracyfutbol3000;

import jig.ResourceManager;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

public class PlayState extends BasicGameState {
    int stateID;
    Image background;
    //  field area by left and right goal ellipse
    //  center = 320x352
    //  Xradius = 320px
    //  yradius = 360px
    //  second ellipse
    //  center = 960, 352
    //  same x y radius
    //  field rect = w:576 h:719
    //  center = 288x, 359.5
    //  to test the object is in the field
    //  get the xMin. check if xMin < 640 if true test if object is in the left circle zone
    //  else check the object being in the rigt circle
    //  if circle check fails to find object check

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
        //  debug the bounding areas for containing car and ball movement
        if (SuperRacyFutbol3000.isDebug) {
            Ellipse ellipse = new Ellipse(319, 360, 320, 361);
            Ellipse ellipse2 = new Ellipse(960, 360, 320, 361);
            Rectangle rect = new Rectangle(352, 0f, 576, 719);

            graphics.setColor(Color.red);
            graphics.fill(ellipse);
            graphics.fill(ellipse2);
            graphics.setColor(Color.cyan);
            graphics.fill(rect);
        }

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
