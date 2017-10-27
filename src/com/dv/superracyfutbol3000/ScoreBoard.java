package com.dv.superracyfutbol3000;

import jig.Entity;
import jig.ResourceManager;
import jig.Vector;
import org.newdawn.slick.Image;

public class ScoreBoard{
    Vector red_most_sig_pos = new Vector(47 ,69);
    Vector red_least_sig_pos = new Vector(81,69);
    Vector blue_most_sig_pos = new Vector(1206,69);
    Vector blue_least_sig_pos = new Vector(1240,69);

    Entity red_least_sig;
    Entity blue_least_sig;
    Entity red_most_sig;
    Entity blue_most_sig;

    private int red_score;
    private int red_least_sig_value, prev_red_least_sig_value;
    private int red_most_sig_value, prev_red_most_sig_value;
    private int blue_score;
    private int blue_most_sig_value, prev_blue_most_sig_value;
    private int blue_least_sig_value, prev_blue_least_sig_value;
    private String current_image_path = SuperRacyFutbol3000.num_0_rsc;

    public String getCurrent_image_path() {
        return current_image_path;
    }

    public ScoreBoard() {

        prev_red_least_sig_value =0;
        prev_red_most_sig_value =0;
        prev_blue_least_sig_value =0;
        prev_blue_most_sig_value=0;

    }

    public void setRedScore(int red_score) {
        this.red_score = red_score;
        prev_red_least_sig_value = this.red_least_sig_value;
        this.red_least_sig_value = red_score%10;
        prev_red_most_sig_value = this.red_most_sig_value;
        this.red_most_sig_value = Math.floorDiv(red_score,10);
    }

    public int getRedLeastSigValue() {
        return red_least_sig_value;
    }

    public int getRedMostSigValue(){
        return red_most_sig_value;
    }

    public void setBlueScore(int blue_score) {
        this.blue_score = blue_score;
        prev_blue_least_sig_value = this.blue_least_sig_value;
        this.blue_least_sig_value = this.blue_score%10;
        prev_blue_most_sig_value = this.blue_most_sig_value;
        this.blue_most_sig_value = Math.floorDiv(this.blue_score,10);
    }

    public int getBlueLeastSigValue() {
        return blue_least_sig_value;
    }

    public void InitDigitEntities(){
        red_most_sig = new Entity(red_most_sig_pos);
        red_least_sig = new Entity(red_least_sig_pos);
        blue_most_sig = new Entity(blue_most_sig_pos);
        blue_least_sig = new Entity(blue_least_sig_pos);
    }

    public int getBlueMostSigValue() {
        return blue_most_sig_value;
    }

    public int setRedScoreLeastSigImage() {
        switch(this.red_least_sig_value){
            case 0:
                if(prev_red_least_sig_value == 9)
                    this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_9_rsc));
                else
                    this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_0_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                return 0;
            case 1:
                this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_1_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_1_rsc));
                return 1;
            case 2:
                this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_1_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_2_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_2_rsc));
                return 2;
            case 3:
                this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_2_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_3_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_3_rsc));
                return 3;
            case 4:
                this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_3_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_4_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_4_rsc));
                return 4;
            case 5:
                this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_4_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_5_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_5_rsc));
                return 5;
            case 6:
                this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_5_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_6_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_6_rsc));
                return 6;
            case 7:
                this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_6_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_7_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_7_rsc));
                return 7;
            case 8:
                this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_7_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_8_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_8_rsc));
                return 8;
            case 9:
                this.red_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_8_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_9_rsc);
                this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_9_rsc));
                return 9;
            default:
                return -1;
        }
    }
    //  returns the value of the most sig that was chosen
    public int setRedScoreMostSigImage(){

        switch(red_most_sig_value){
            case 0:
                if(prev_red_most_sig_value == 9)
                    this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_9_rsc));
                else
                    this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_0_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                return 0;
            case 1:
                this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_1_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_1_rsc));
                return 1;
            case 2:
                this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_1_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_2_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_2_rsc));
                return 2;
            case 3:
                this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_2_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_3_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_3_rsc));
                return 3;
            case 4:
                this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_3_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_4_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_4_rsc));
                return 4;
            case 5:
                this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_4_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_5_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_5_rsc));
                return 5;
            case 6:
                this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_5_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_6_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_6_rsc));
                return 6;
            case 7:
                this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_6_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_7_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_7_rsc));
                return 7;
            case 8:
                this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_7_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_8_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_8_rsc));
                return 8;
            case 9:
                this.red_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_8_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_9_rsc);
                this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_9_rsc));
                return 9;
            default:
                return -1;
        }

    }
    public int setBlueScoreMostSigImage() {
        switch(this.blue_most_sig_value){
            case 0:
                if(prev_blue_most_sig_value == 9)
                    this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_9_rsc));
                else
                    this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_0_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                return 0;
            case 1:
                this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_1_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_1_rsc));
                return 1;
            case 2:
                this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_1_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_2_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_2_rsc));
                return 2;
            case 3:
                this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_2_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_3_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_3_rsc));
                return 3;
            case 4:
                this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_3_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_4_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_4_rsc));
                return 4;
            case 5:
                this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_4_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_5_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_5_rsc));
                return 5;
            case 6:
                this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_5_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_6_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_6_rsc));
                return 6;
            case 7:
                this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_6_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_7_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_7_rsc));
                return 7;
            case 8:
                this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_7_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_8_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_8_rsc));
                return 8;
            case 9:
                this.blue_most_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_8_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_9_rsc);
                this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_9_rsc));
                return 9;
            default:
                return -1;
        }
    }

    public int setBlueScoreLeastSigImage(){
        switch(this.blue_least_sig_value){
            case 0:
                if(prev_blue_least_sig_value == 9)
                    this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_9_rsc));
                else
                    this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_0_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                return 0;
            case 1:
                this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_1_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_1_rsc));
                return 1;
            case 2:
                this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_1_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_2_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_2_rsc));
                return 2;
            case 3:
                this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_2_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_3_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_3_rsc));
                return 3;
            case 4:
                this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_3_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_4_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_4_rsc));
                return 4;
            case 5:
                this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_4_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_5_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_5_rsc));
                return 5;
            case 6:
                this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_5_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_6_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_6_rsc));
                return 6;
            case 7:
                this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_6_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_7_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_7_rsc));
                return 7;
            case 8:
                this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_7_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_8_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_8_rsc));
                return 8;
            case 9:
                this.blue_least_sig.removeImage(ResourceManager.getImage(SuperRacyFutbol3000.num_8_rsc));
                setCurrent_image_path(SuperRacyFutbol3000.num_9_rsc);
                this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_9_rsc));
                return 9;
            default:
                return -1;
        }
    }
    public Entity getBlueLeastSigEntity() {
        return blue_least_sig;
    }

    public Entity getBlueMostSigEntity() {
        return blue_most_sig;
    }

    public Entity getRedLeastSigEntity() {
        return red_least_sig;
    }

    public Entity getRedMostSigEntity() {
        return red_most_sig;
    }


    public void setCurrent_image_path(String current_image_path) {
        this.current_image_path = current_image_path;
        System.out.println(current_image_path);
    }

    public void InitScoreBoardImages(){
        this.red_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
        this.red_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
        this.blue_least_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
        this.blue_most_sig.addImage(ResourceManager.getImage(SuperRacyFutbol3000.num_0_rsc));
    }

}
