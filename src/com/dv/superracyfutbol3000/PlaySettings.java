package com.dv.superracyfutbol3000;

public class PlaySettings {
    private boolean player1_red; //   determines the team player 1 plays for
    private boolean player2_red; //   determines the team player 1 plays for
    private boolean player3_red; //   determines the team player 1 plays for
    private boolean player4_red; //   determines the team player 1 plays for
    private boolean player5_red; //   determines the team player 1 plays for
    private boolean player6_red; //   determines the team player 1 plays for

    private int players_per_team;
    private int human_players;

    public PlaySettings() {
        players_per_team = 1;
        human_players = 1;
        player1_red = true;
    }

    public boolean isPlayer1_red() {
        return player1_red;
    }

    public void setPlayer1_red(boolean player1_red) {
        this.player1_red = player1_red;
    }

    public boolean isPlayer2_red() {
        return player2_red;
    }

    public void setPlayer2_red(boolean player2_red) {
        this.player2_red = player2_red;
    }

    public boolean isPlayer3_red() {
        return player3_red;
    }

    public void setPlayer3_red(boolean player3_red) {
        this.player3_red = player3_red;
    }

    public boolean isPlayer6_red() {
        return player6_red;
    }

    public void setPlayer6_red(boolean player6_red) {
        this.player6_red = player6_red;
    }

    public boolean isPlayer5_red() {
        return player5_red;
    }

    public void setPlayer5_red(boolean player5_red) {
        this.player5_red = player5_red;
    }

    public boolean isPlayer4_red() {
        return player4_red;
    }

    public void setPlayer4_red(boolean player4_red) {
        this.player4_red = player4_red;
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
