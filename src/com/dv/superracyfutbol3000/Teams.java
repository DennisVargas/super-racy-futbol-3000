package com.dv.superracyfutbol3000;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;

public class Teams {
    private ArrayList<Cars> red_team = new ArrayList<Cars>();
    private ArrayList<Cars> blue_team = new ArrayList<Cars>();

    int players_per_team = SuperRacyFutbol3000.play_settings.GetPlayersPerTeam();
    int total_players = 2*players_per_team;
    int human_players;
    int cpu_players = total_players -human_players;

    public Teams(ArrayList<Cars> red_team, ArrayList blue_team) {
        this.red_team = red_team;
        this.blue_team = blue_team;
    }

    //  fills teams with human players then fills the teams with computer players
    private void FillTeams() {
        Players player = null;
        human_players = SuperRacyFutbol3000.play_settings.GetHumanPlayers();
        cpu_players = total_players - human_players;
        int i = 0;
        int red_i =0;//    for spacing teams on the board
        int blue_i =0;//   first player is at top second middle.

        for (i = 0; i < human_players;i++){
            player = SuperRacyFutbol3000.play_settings.players.get(i);
            if(player.isRed)
                AddPlayer(player, 120f, (i+1)*80f);
            else
                AddPlayer(player, 720f,(i+1)*80f);
        }
        if(i < total_players){
            for(i = human_players; i <= cpu_players; i++){
                if(this.red_team.size()<3)
                    player = new Players("CPU-"+i,true, Players.Controller.AI);
                else
                    player = new Players("CPU-"+i, false, Players.Controller.AI);
                if(player.isRed)
                    AddPlayer(player, 120f, i*80f);
                else
                    AddPlayer(player, 800f,i*80f);
            }
        }
    }

    private void AddPlayer(Players player, float x, float y){
        if(player.isRed)
            this.red_team.add(new Cars(x,y,player));
        else
            this.blue_team.add(new Cars(x,y, player));
    }

    public void RenderTeams(Graphics g){
        for (Cars car:blue_team){
            car.render(g);
        }
        for(Cars car:red_team){
            car.render(g);
        }
    }

    public void UpdateTeams(){
        for (Cars car: red_team){
            car.UpdateCar();
        }
        for (Cars car: blue_team){
            car.UpdateCar();
        }
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


    public void ProcessTeams(Input input) {
        for (Cars car: red_team){
            car.ProcessInput(input);
        }
        for (Cars car: blue_team){
            car.ProcessInput(input);
        }
    }
}
