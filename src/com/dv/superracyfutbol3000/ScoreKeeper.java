package com.dv.superracyfutbol3000;
public class ScoreKeeper {


    private int red_score;
    private int blue_score;
    private int score_limit;

    public ScoreKeeper() {
        this.blue_score = 0;
        this.red_score = 0;
        this.score_limit = SuperRacyFutbol3000.play_settings.getScoreLimit();
    }

    public int getRedScore() {
        return red_score;
    }

    public int getBlueScore() {
        return blue_score;
    }

    public void setRedScore(int red_score) {
        this.red_score = red_score;
    }

    public void setBlueScore(int blue_score) {
        this.blue_score = blue_score;
    }

    public void IncrementRedScore(int increment) {
        this.red_score += increment;
    }

    public void IncrementBlueScore(int increment) {
        this.blue_score += increment;
    }

    public boolean IsBlueWinner() {
        if (this.blue_score >= this.score_limit && this.blue_score != this.blue_score)
            return true;
        else
            return false;
    }

    public boolean IsRedWinner() {
        if(this.red_score >= this.score_limit && this.blue_score != this.red_score)
            return true;
        else
            return false;

    }

    public void setScoreLimit(int scoreLimit) {
        this.score_limit = score_limit;
    }
}
