package com.dv.superracyfutbol3000;

import java.util.ArrayList;

public class Teams {
    private ArrayList<Cars> red_team = new ArrayList<Cars>();
    private ArrayList<Cars> blue_team = new ArrayList<Cars>();

    public Teams(ArrayList<Cars> red_team, ArrayList blue_team) {
        this.red_team = red_team;
        this.blue_team = blue_team;
        FillTeams();
    }

    private void FillTeams() {
        Players player = null;
        for (int i = 0; i <SuperRacyFutbol3000.play_settings.getHuman_players();i++){
            player = SuperRacyFutbol3000.play_settings.players.get(i);

            //            this.red_team.add(new Cars(0,0, ))
//            if(player.isRed)

        }
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
