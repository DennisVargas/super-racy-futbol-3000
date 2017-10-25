package com.dv.superracyfutbol3000

import groovy.time.TimeDuration
import jig.Entity
import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.state.StateBasedGame

class GoalieTest extends GroovyTestCase {
    AppGameContainer app;
    Goalie goalie;
    Ball ball;

    void setUp() {
        super.setUp()

        Entity.setCoarseGrainedCollisionBoundary(Entity.CIRCLE)
//        app = new AppGameContainer(new SuperRacyFutbol3000());
//        app.setDisplayMode((int)(SuperRacyFutbol3000.WIDTH*SuperRacyFutbol3000.SCALE),
//                (int)(SuperRacyFutbol3000.HEIGHT*SuperRacyFutbol3000.SCALE),false);
//        app.setVSync(true);
//        app.start();

////         red goalie in center of field
        goalie = new Goalie(300,360,true);
        ball = new Ball()
    }

    void tearDown() {
    }

    void testRenderGoalie() {

    }

    void testTrackBall() {

    }

    void testOneIsBallStuck() {

        //  test for function that detects if the ball has not moved
//            Goalie()


        //  need a difference in time to measure for time out.
        long start_time = System.nanoTime()
        long diff_time=System.nanoTime()-start_time

    //  test if the ball is detected as stuck after 5 second
        assertFalse(goalie.IsBallStuck(ball.getPosition(),(int)start_time))
        //  timout length is 5 seconds
        for (diff_time; diff_time/1000000000<=5f;diff_time){
            diff_time = System.nanoTime()-start_time
//            System.out.printf("%.2f\n", diff_time/1000000000)
        }
        assertTrue(goalie.IsBallStuck(ball.getPosition(),(int)diff_time))
    }
}
