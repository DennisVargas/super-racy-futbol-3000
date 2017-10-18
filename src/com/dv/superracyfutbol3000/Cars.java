package com.dv.superracyfutbol3000;

import jig.Entity;

public class Cars extends Entity {
    float health_level = 0f;
    float boost_level = 0f;
    float acceleration = 0f;
    float turn_angle = 0f;
    float top_speed = 0f;
    float top_boost_speed = 0f;
    float max_accel = 0f;
    float max_boost_vel = 0f;
    float deceleration = 0f; // simulates friction, gravity, and mass

    public Cars(float x, float y) {
        super(x, y);
    }
}
