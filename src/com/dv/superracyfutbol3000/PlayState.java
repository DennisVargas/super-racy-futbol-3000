package com.dv.superracyfutbol3000;

import jig.Collision;
import jig.Entity;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import static com.dv.superracyfutbol3000.Players.Controller.AI;
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
    Goalie goalies;
    Entity winner_banner;
    Entity start_game_banner;
    Entity countdown_start_timer;
    Entity blue_goal_scored;
    Entity red_goal_scored;

    boolean is_red_goal_scored =false, is_blue_goal_scored=false,pause_play = false, is_red_winner = false,
                is_blue_winner = false;


    boolean isWinner = false;
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
    private int time;
    private Goals red_goal;
    private Goals blue_goal;

    private ScoreKeeper score_keeper;
    private ScoreBoard score_board;
    private Entity goal_scored_banner;
    private int countdown_start_time;

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
        this.red_goal = new Goals(true);
        this.blue_goal = new Goals(false);
        this.winner_banner = new Entity();
        this.goal_scored_banner = new Entity();
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = ResourceManager.getImage(SuperRacyFutbol3000.play_field_rsc);
        Ball.setDebug(true);
        score_keeper = new ScoreKeeper();
        score_keeper.setBlueScore(0);
        score_keeper.setRedScore(0);
        score_board = new ScoreBoard();
        score_board.InitDigitEntities();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame,
                       Graphics graphics) throws SlickException {
        background.draw();

        //  debug the bounding areas for containing car and ball movement
        if (SuperRacyFutbol3000.isDebugField) {
            RenderFieldDebugOverlay(graphics);
        }

        RenderTeams(graphics);
        ball.RenderBall(graphics);
//        graphics.drawString("Time : " + time/1000+" seconds", 100, 100);
        //  Render the score board
        RenderScoreBoard(graphics);
    }

    private void RenderScoreBoard(Graphics g) {
        score_board.setRedScoreLeastSigImage();
        score_board.setRedScoreMostSigImage();
        score_board.setBlueScoreLeastSigImage();
        score_board.setBlueScoreMostSigImage();
        score_board.getBlueLeastSigEntity().render(g);
        score_board.getBlueMostSigEntity().render(g);
        score_board.getRedLeastSigEntity().render(g);
        score_board.getRedMostSigEntity().render(g);
    }

    @Override
    public void update(GameContainer gameContainer,
                       StateBasedGame stateBasedGame, int i) throws SlickException {

//        CarCarCollidesTester();
        time+= i;

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
        //  todo: passing in Time into collisions to have a delay between ball collisions for testing later
        CollidesHelper.CheckWorldCollisions(teams, ball,ellipse,ellipse2,rect,time);

        //  Update the Team Position based on collisions and input
        teams.ProcessTeamsNextMove(ellipse,ellipse2,rect);

        //  passing the balls current position and its heading
        // the goalie can more or less guess where the ball will be next
        //  convert time from milliseconds to seconds; divide 1000 into time

        if(teams.GoalieTrackingBallStuck(ball, time/1000))
            System.out.println("ball Stuck OH NO");
        //  Update the Ball based on collisions
        ball.UpdateBall(ellipse,ellipse2,rect);

        // test if goal
        //  and
        //  update score
        DoScoreKeeping();

            //  declare winner if true
    }

    private void DoScoreKeeping() {
        if(!isWinner){

            int new_score =0;
            //   does score keeping stuff
            if((new_score = red_goal.IsGoal(ball.getPosition(), ball.getCoarseGrainedRadius()))> 0){
                //  reset the ball to start
                ball.ResetBallStart();
                score_keeper.IncrementBlueScore(new_score);
                PauseForSplash();
            }else if((new_score = blue_goal.IsGoal(ball.getPosition(), ball.getCoarseGrainedRadius()))> 0){
                ball.ResetBallStart();
                score_keeper.IncrementRedScore(new_score);
            }

//          update the scoreboard for render.
            score_board.setBlueScore(score_keeper.getBlueScore());
            score_board.setRedScore(score_keeper.getRedScore());

            isWinner = score_keeper.IsBlueWinner();
            if(is_blue_winner = score_keeper.IsBlueWinner()){
                DeclareBlueWinner();
                System.out.println("BlueWins");
            }

            if (is_red_winner = score_keeper.IsRedWinner()) {
                DeclareRedWinner();
                System.out.println("RedWins");
            }
        }else{

            System.out.println("winner declared");
        }
    }

    private void DeclareBlueWinner() {

    }

    private void DeclareRedWinner() {

    }

    public void CarCarCollidesTester(){
        //  junit can't test slick stuff to well
        Cars carA = new Cars(26,40, new Players(AI));
        Cars carB = new Cars(0,0,new Players(AI));
        Collision collision = carA.collides(carB);
        if(collision!=null)
            System.out.println("collision here");
    }

    public void RenderTeams(Graphics g){
        for (Cars car:teams.getRed_team()){
            car.render(g);
        }
        for(Cars car:teams.getBlue_team()){
            car.render(g);
        }
        RenderGoalies(g, teams.getRedGoalie(), teams.getBlueGoale());
    }

    private void RenderGoalies(Graphics g, Goalie red_goalie, Goalie blue_goalie) {
        g.setColor(org.newdawn.slick.Color.pink);
        g.fill(red_goalie.getGoalie_rect());
        g.setColor(org.newdawn.slick.Color.cyan);
        g.fill(blue_goalie.getGoalie_rect());
        red_goalie.render(g);
        blue_goalie.render(g);
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
       // System.out.println("high fverscters");
        Vector v = new Vector (-1f,1f);
        Vector x = new Vector(1f,0.5f);
        v =v.add(x);
        ball.translate(v);
    }
}

