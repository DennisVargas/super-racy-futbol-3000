package com.dv.superracyfutbol3000;

public class Players {
    enum Controller {Keyboard, Gamepad}
    String name = "Player ";
    boolean isRed = false;
    Controller control_type = Controller.Keyboard;

    public Controller GetControl_type() {
        return control_type;
    }

    public void SetControl_type(Controller control_type) {
        this.control_type = control_type;
    }

    void SetPlayerName(String name){
        this.name = name;
    }

    void setIsRed(boolean isRed){
        this.isRed = isRed;
    }

    String GetPlayerName(){
        return this.name;
    }

    boolean GetIsRed(){
        return isRed;
    }
}
