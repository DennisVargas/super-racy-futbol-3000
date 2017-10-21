package com.dv.superracyfutbol3000;

import java.util.ArrayList;

public class PlaySettings {
    ArrayList<Players> players = new ArrayList<>();

    private int players_per_team;
    private int human_players;
    private Teams teams;

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
    }

    private void AddPlayer(Players player) {
        players.add(player);
    }
    public int GetHumanPlayers() {
        return human_players;
    }

    public void SetHumanPlayers(int human_players) {
        this.human_players = human_players;
    }

    public int GetPlayersPerTeam() {
        return players_per_team;
    }

    public void SetPlayersPerTeam(int players_per_team) {
        this.players_per_team = players_per_team;
    }

    public void SetTeams(){
        teams = new Teams();
    }
    public Teams GetTeams(){
        return teams;
    }
}
