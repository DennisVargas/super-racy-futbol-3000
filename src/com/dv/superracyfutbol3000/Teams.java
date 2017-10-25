package com.dv.superracyfutbol3000;

import jig.Vector;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

import static java.lang.Math.PI;

public class Teams {
    private ArrayList<Cars> red_team = new ArrayList<Cars>();
    private ArrayList<Cars> blue_team = new ArrayList<Cars>();

    //  goalies are controlled seperate from car driven players
    Goalie red_goalie = new Goalie(74,360,true);
    Goalie blue_goalie = new Goalie(1210,360,false);

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
            car.setTurn_rads(0);
            this.red_team.add(car);
        }
        else{
            car.rotate(270);
            car.setTurn_rads(PI);
            this.blue_team.add(car);
        }
    }


    public void ProcessTeamsNextMove(Ellipse ellipse1, Ellipse ellipse2, Rectangle rect){

        for (Cars car: red_team){
            if(car.getX() < SuperRacyFutbol3000.WIDTH/2){
//                if(SuperRacyFutbol3000.isWallDebug)System.out.println("Ellipse1 RED: " + car.controlling_player.name);
                car.UpdateCar(ellipse1, rect);
            }
            else{
//                if(SuperRacyFutbol3000.isWallDebug)System.out.println("Ellipse2 Red: " + car.controlling_player.name);
                car.UpdateCar(ellipse2, rect);
            }

        }
        for (Cars car: blue_team){
            if(car.getX() < SuperRacyFutbol3000.WIDTH/2){
//                if(SuperRacyFutbol3000.isWallDebug)System.out.println("Ellipse1 Blue: " + car.controlling_player.name+" width/2: "+SuperRacyFutbol3000.WIDTH/2 +" CarX: "+car.getX());
                car.UpdateCar(ellipse1, rect);
            }

            else{
//                if(SuperRacyFutbol3000.isWallDebug)System.out.println("Ellipse2 Blue: " + car.controlling_player.name);
                car.UpdateCar(ellipse2, rect);
            }

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


    public void UpdateTeamsNextMove(Input input) {
        for (Cars car: red_team){
            car.GenerateNextMove(input);

        }
        for (Cars car: blue_team){
            car.GenerateNextMove(input);
        }
    }
}
