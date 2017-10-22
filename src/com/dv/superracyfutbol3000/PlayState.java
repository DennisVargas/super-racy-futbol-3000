package com.dv.superracyfutbol3000;

import jig.ConvexPolygon;
import jig.Entity;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.StrictMath.sin;
public class PlayState extends BasicGameState {
    int stateID;
    Image background;
    Ellipse ellipse = new Ellipse(320, 360, 320, 365);
    Ellipse ellipse2 = new Ellipse(960, 360, 320, 365);
    Rectangle rect = new Rectangle(320, 0f, 640, 720);
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


 // Create Teams
    Teams teams;
    public PlayState(int stateID) {
        super();
        this.stateID = stateID;
    }

    @Override
    public int getID() {
        return this.stateID;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        this.teams = SuperRacyFutbol3000.play_settings.GetTeams();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = ResourceManager.getImage(SuperRacyFutbol3000.play_field_rsc);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        background.draw();

        //  debug the bounding areas for containing car and ball movement
        if (SuperRacyFutbol3000.isDebug) {
            graphics.setColor(Color.red);
            graphics.fill(ellipse);
            graphics.fill(ellipse2);
            graphics.setColor(Color.cyan);
            graphics.fill(rect);
        }
        teams.RenderTeams(graphics);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

        Input input = gameContainer.getInput();
        float mouseX = input.getMouseX();
        float mouseY= input.getMouseY();
        if(SuperRacyFutbol3000.isMouseDebug)System.out.println("MouseX: " +mouseX+" MouseY: "+mouseY);
        teams.ProcessTeams(input);

        teams.UpdateTeams(ellipse,ellipse2,rect);

    }
}
