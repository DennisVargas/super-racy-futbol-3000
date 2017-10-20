package com.dv.superracyfutbol3000;

import java.util.ArrayList;

public class Teams {
    private ArrayList<Cars> red_team = new ArrayList<Cars>();
    private ArrayList<Cars> blue_team = new ArrayList<Cars>();

    int players_per_team = SuperRacyFutbol3000.play_settings.getPlayers_per_team();
    int total_players = 2*players_per_team;
    int human_players = SuperRacyFutbol3000.play_settings.getHuman_players();
    int cpu_players = human_players-total_players;

    public Teams(ArrayList<Cars> red_team, ArrayList blue_team) {
        this.red_team = red_team;
        this.blue_team = blue_team;
    }

    //  fills teams with human players then fills the teams with computer players
    private void FillTeams() {
        Players player = null;
        int i = 0;

        for (i = 0; i < human_players;i++){
            player = SuperRacyFutbol3000.play_settings.players.get(i);
            AddPlayer(player);
        }
        if(i < total_players){
            for(i=0; i < cpu_players; i++){
                if(this.red_team.size()<3)
                    player = new Players("CPU-"+i,true, Players.Controller.AI);
                else
                    player = new Players("CPU-"+i, false, Players.Controller.AI);
                AddPlayer(player);
            }
        }
    }

    private void AddPlayer(Players player){
        if(player.isRed)
            this.red_team.add(new Cars(0,0,player));
        else
            this.blue_team.add(new Cars(0,0, player));
    }

    public Teams(){
        FillTeams();
    }

    public ArrayList<Cars> getRed_team() {
        return red_team;
    }

    public ArrayList<Cars> getBlue_team() {
        return blue_team;
    }




}
