package com.dv.superracyfutbol3000;

import jig.Vector;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.LinkedList;

public class AITestState extends BasicGameState {
    Cars car;
    Ball ball;
    CarAI carAI;
    Goals blue_goal, red_goal;
    public AITestState(int aiteststate) {
    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);
        blue_goal = new Goals(false);
        red_goal = new Goals(true);
        car = new Cars(320,360, new Players("Ai Bot 1",false, Players.Controller.AI));
        ball = new Ball(20,200);
        car.InitCarAI(blue_goal.getRectangle(), red_goal.getRectangle(), ball);

        car.rotate(90);
        car.setTurn_rads(0);

        //  initialize car ai object if car is an AI
        //  do this in constructor.
        //if(car.controlling_player.GetControl_type() == Players.Controller.AI)
            //

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        car.render(graphics);

        ball.render(graphics);
        graphics.fill(ball.getBall_Sphere());
        graphics.setColor(Color.red);
        graphics.fill(car.getHealthBar());
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        LinkedList <MoveOrder> orders = new LinkedList<>();
        ball.setTranslateNextMove(new Vector(0.4f,0f));
        ball.UpdateBall();


        car.GenerateNextMove(input);
        car.UpdateCar();
        car.UpdateHealthBarLocation();
    }
}
