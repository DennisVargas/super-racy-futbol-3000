package com.dv.superracyfutbol3000;

import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.StrictMath.abs;
import static java.lang.StrictMath.sin;
public class PlayState extends BasicGameState {
    int stateID;
    Image background;
    Ellipse ellipse = new Ellipse(326, 360, 326, 360);
    Ellipse ellipse2 = new Ellipse(955, 360, 326, 360);
    Rectangle rect = new Rectangle(320, 0f, 640, 720);
    double theta;
    Vector rotation_test = new Vector(0,0);
    Rectangle rect2 = new Rectangle (0,0,23,35);
    Ball ball;

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
        this.ball = new Ball(SuperRacyFutbol3000.WIDTH/2, SuperRacyFutbol3000.HEIGHT/2);
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = ResourceManager.getImage(SuperRacyFutbol3000.play_field_rsc);
        Ball.setDebug(true);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame,
                       Graphics graphics) throws SlickException {
        background.draw();

        //  debug the bounding areas for containing car and ball movement
        if (SuperRacyFutbol3000.isDebugField) {
            RenderFieldDebugOverlay(graphics);

        }

        teams.RenderTeams(graphics);
        ball.RenderBall(graphics);
    }


    @Override
    public void update(GameContainer gameContainer,
                       StateBasedGame stateBasedGame, int i) throws SlickException {

        Input input = gameContainer.getInput();

        float mouseX = input.getMouseX();
        float mouseY= input.getMouseY();
        if(SuperRacyFutbol3000.isMouseDebug) {
            System.out.println("MouseX: " + mouseX);
            System.out.println("MouseY: " + mouseY);
        }
        //  Process the Team Input
        teams.UpdateTeamsNextMove(input);

        //  Check for collisions with the next move before processing
        CollidesHelper.CheckWorldCollisions(teams, ball,ellipse,ellipse2,rect);

        //  Update the Team Position based on collisions and input
        teams.ProcessTeamsNextMove(ellipse,ellipse2,rect);
        Cars car =teams.getRed_team().get(0);
        //  Update the Ball based on collisions
        ball.UpdateBall(ellipse,ellipse2,rect,car);

    }
    private void RenderFieldDebugOverlay(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.fill(ellipse);
        graphics.fill(ellipse2);
        graphics.setColor(Color.cyan);
        graphics.fill(rect);
        if(SuperRacyFutbol3000.isDebugRotationTest){
            graphics.setColor(Color.pink);
            graphics.fill(rect2);
        }
    }

    private void RotationTest() {
//      A test on moving a particle in an elliptical orbit.
        if (SuperRacyFutbol3000.isDebugRotationTest) {
            theta += PI / 360;
            if (theta >= 2 * PI)
                theta %= 2 * PI;

//        rotation_test = rotation_test.setX((float) (rotation_test.getX() + theta*cos(theta)));
//        rotation_test = rotation_test.setY((float) (rotation_test.getY() - theta*sin(theta)));
            float cur_x, cur_y;
            float next_x, next_y;

            next_x = (float) (ellipse.getRadius1() * cos(theta));
            next_y = (float) (ellipse.getRadius2() * sin(theta));

        /*if (abs(theta) < PI/2 || abs(theta) > 3*PI/2)*/
//        if (this.rect2.getX() >= ellipse.getCenterX()){
//            next_x -= rect2.getWidth();
//        }
//        if(this.rect2.getY() >= ellipse.getCenterY()){
//            next_y -= rect2.getHeight();
//        }

            rect2.setX((next_x) + 320f);
            rect2.setY((next_y) + 360f);
        }
    }
    private void VectorTest(){
        //  Testing the vector addition functions
        System.out.println("high fverscters");
        Vector v = new Vector (-1f,1f);
        Vector x = new Vector(1f,0.5f);
        v =v.add(x);
        ball.translate(v);
    }
}

