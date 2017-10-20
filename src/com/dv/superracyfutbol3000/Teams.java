package com.dv.superracyfutbol3000;

import java.util.ArrayList;

public class Teams {
    private ArrayList<Cars> red_team = new ArrayList<Cars>();
    private ArrayList<Cars> blue_team = new ArrayList<Cars>();

    public Teams(ArrayList<Cars> red_team, ArrayList blue_team) {
        this.red_team = red_team;
        this.blue_team = blue_team;
    }

    public Teams(){
        
    }
    public ArrayList<Cars> getRed_team() {
        return red_team;
    }

    public ArrayList<Cars> getBlue_team() {
        return blue_team;
    }




}
