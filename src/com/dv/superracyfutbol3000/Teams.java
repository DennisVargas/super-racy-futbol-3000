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
        int red_i =1;//    for spacing teams on the board
        int blue_i =1;//   first player is at top second middle.

        for (i =0; i < total_players; i++){
            if (human_players-- > 0)
                player = SuperRacyFutbol3000.play_settings.players.get(i);
            else if(this.red_team.size()<players_per_team)
                player = new Players("CPU-"+i,true, Players.Controller.AI);
            else
                player = new Players("CPU-"+i, false, Players.Controller.AI);

            if(player.isRed){
                switch(players_per_team){
                    case 1:
                        AddPlayer(player, 128f, red_i*SuperRacyFutbol3000.HEIGHT*0.5f/*(128f)+224f*/);
                        red_i++;
                        break;
                    case 2:
                        AddPlayer(player, 128f, red_i*SuperRacyFutbol3000.HEIGHT*(1/3f)/*(128f)+224f*/);
                        red_i++;
                        break;
                    case 3:
                        AddPlayer(player, 128f, red_i*SuperRacyFutbol3000.HEIGHT*0.25f/*(128f)+224f*/);
                        red_i++;
                        break;
                    default:
                        break;
                }

            }else{
                switch(players_per_team){
                    case 1:
                        AddPlayer(player, 1152f, blue_i*SuperRacyFutbol3000.HEIGHT*0.5f/*(128f)+224f*/);
                        blue_i++;
                        break;
                    case 2:
                        AddPlayer(player, 1152f, blue_i*SuperRacyFutbol3000.HEIGHT*(1/3f)/*(128f)+224f*/);
                        blue_i++;
                        break;
                    case 3:
                        AddPlayer(player, 1152f, blue_i*SuperRacyFutbol3000.HEIGHT*0.25f/*(128f)+224f*/);
                        blue_i++;
                        break;
                    default:
                        break;
                }
            }

        }
    }

    private void AddPlayer(Players player, float x, float y){
        Cars car = new Cars(x,y,player);
        if(player.isRed){
            car.rotate(90);
            car.setTurn_angle(0);
            car.UpdateCar();
            this.red_team.add(car);
        }
        else{
            car.rotate(270f);
            car.setTurn_angle(Math.PI);
            car.UpdateCar();
            this.blue_team.add(car);
        }

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
