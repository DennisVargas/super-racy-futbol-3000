package com.dv.superracyfutbol3000;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

public class AITestState extends BasicGameState {
    Cars car;
    Ball ball;
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
        car = new Cars(320,360, new Players("Ai Bot 1",false, Players.Controller.AI));
        //  initialize car ai object if car is an AI
        //  do this in constructor.
        //if(car.controlling_player.GetControl_type() == Players.Controller.AI)
            //

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        car.render(graphics);
        graphics.setColor(Color.red);
        graphics.fill(car.getHealthBar());
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
//        car.ProcessInput(input);
        car.GenerateNextMove(input);
        car.UpdateCar();
        car.UpdateHealthBarLocation();
    }
}
