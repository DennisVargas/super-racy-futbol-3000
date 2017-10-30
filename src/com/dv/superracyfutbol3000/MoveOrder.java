package com.dv.superracyfutbol3000;

public class MoveOrder {
    enum CarCommands {none,accelerate, deccelerate, turn_left, turn_right, straight}
    private CarCommands accel_command, turn_command;

    public MoveOrder() {
        setTurn_command(CarCommands.none);
        setAccelCommand(CarCommands.none);
    }

    public MoveOrder(CarCommands accel_command, CarCommands turn_command) {
        setAccelCommand(accel_command);
        setTurn_command(turn_command);
    }

    public void setAccelCommand(CarCommands accel_command){
        this.accel_command = accel_command;
    }

    public void setTurn_command(CarCommands turn_command){
        this.turn_command = turn_command;
    }

    public CarCommands getAccelCommand(){
        return this.accel_command;
    }

    public CarCommands getTurnCommand() {
        return this.turn_command;
    }
}
