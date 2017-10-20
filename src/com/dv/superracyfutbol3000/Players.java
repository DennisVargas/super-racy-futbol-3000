package com.dv.superracyfutbol3000;

public class Players {

    enum PlayerType {Human, CPU}
    enum Controller {Keyboard, Gamepad, AI}

    String name = "Player ";
    boolean isRed = false;
    Controller control_type = Controller.Keyboard;
    int controller_number = -1;

    public int GetControllerNumber() {
        return controller_number;
    }

    public void SetControllerNumber(int controller_number) {
        this.controller_number = controller_number;
    }


    public Players(String name, boolean isRed, Controller control_type) {
        this.name = name;
        this.isRed = isRed;
        this.control_type = control_type;
    }


    public Controller GetControl_type() {
        return control_type;
    }

    public void SetControl_type(Controller control_type) {
        this.control_type = control_type;
    }

    void SetPlayerName(String name){
        this.name = name;
    }

    void SetIsRed(boolean isRed){
        this.isRed = isRed;
    }

    String GetPlayerName(){
        return this.name;
    }

    boolean GetIsRed(){
        return isRed;
    }
}
