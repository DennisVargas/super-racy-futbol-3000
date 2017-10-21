package com.dv.superracyfutbol3000;

import java.util.ArrayList;

public class PlaySettings {
    ArrayList<Players> players = new ArrayList<Players>();

//    private boolean player1_red; //   determines the team player 1 plays for
//    private boolean player2_red; //   determines the team player 1 plays for
//    private boolean player3_red; //   determines the team player 1 plays for
//    private boolean player4_red; //   determines the team player 1 plays for
//    private boolean player5_red; //   determines the team player 1 plays for
//    private boolean player6_red; //   determines the team player 1 plays for

    private int players_per_team;
    private int human_players;

    public PlaySettings(ArrayList<Players> players, int players_per_team, int human_players) {
        this.players = players;
        this.players_per_team = players_per_team;
        this.human_players = human_players;
    }

    public PlaySettings(int players_per_team, int human_players) {
        this.players_per_team = players_per_team;
        this.human_players = human_players;
    }

    public PlaySettings() {
        players_per_team = 1;
        human_players = 1;
//        player1_red = true;
    }

    private void AddPlayer(Players player) {
        players.add(player);
    }
    public int getHuman_players() {
        return human_players;
    }

    public void setHuman_players(int human_players) {
        this.human_players = human_players;
    }

    public int getPlayers_per_team() {
        return players_per_team;
    }

    public void setPlayers_per_team(int players_per_team) {
        this.players_per_team = players_per_team;
    }
}
