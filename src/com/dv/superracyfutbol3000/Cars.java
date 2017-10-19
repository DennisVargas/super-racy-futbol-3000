package com.dv.superracyfutbol3000;

import jig.Entity;
import org.newdawn.slick.Input;

public class Cars extends Entity {
    private float health_level = 0f;
    private float boost_level = 0f;
    private float acceleration = 0f;
    private float turn_angle = 0f;
    private float top_speed = 0f;
    private float top_boost_speed = 0f;
    private float max_accel = 0f;
    private float max_boost_vel = 0f;
    private float deceleration = 0f; // simulates friction, gravity, and mass

    public Cars(float x, float y) {
        super(x, y);
    }

    public float GetHealthLevel() {
        return health_level;
    }

    public void SetHealthLevel(float health_level) {
        this.health_level = health_level;
    }

    public float GetBoostLevel() {
        return boost_level;
    }

    public void SetBoostLevel(float boost_level) {
        this.boost_level = boost_level;
    }

    public float GetAcceleration() {
        return acceleration;
    }

    public void SetAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float GetTurnAngle() {
        return turn_angle;
    }

    public void SetTurnAngle(float turn_angle) {
        this.turn_angle = turn_angle;
    }

    public float GetTopSpeed() {
        return top_speed;
    }

    public void SetTopSpeed(float top_speed) {
        this.top_speed = top_speed;
    }

    public float GetTopBoostSpeed() {
        return top_boost_speed;
    }

    public void SetTopBoostSpeed(float top_boost_speed) {
        this.top_boost_speed = top_boost_speed;
    }

    public float GetMaxAccel() {
        return max_accel;
    }

    public void SetMaxAccel(float max_accel) {
        this.max_accel = max_accel;
    }

    public float GetMaxBoost_vel() {
        return max_boost_vel;
    }

    public void SetMaxBoostVel(float max_boost_vel) {
        this.max_boost_vel = max_boost_vel;
    }

    public float GetDeceleration() {
        return deceleration;
    }

    public void SetDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    void ProcessInput(Input i){
        //  if i matches
    }
}
