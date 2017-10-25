package com.dv.superracyfutbol3000;
import org.lwjgl.util.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;


public class Goalies {

    Rectangle red_goalie = new Rectangle(64,360, 20,50);
    Rectangle blue_goalie = new Rectangle(1000,360,20,50);

    public Goalies() {

    }

    void RenderGoalies(Graphics g){
        g.setColor(org.newdawn.slick.Color.pink);
        g.fill(red_goalie);
        g.setColor(org.newdawn.slick.Color.cyan);
        g.fill(blue_goalie);
    }
}
