package com.uiolert.stars_.modes.ball;

import android.graphics.Color;

public class Ball {
    private static int idStatic = 0;
    public int id;
    float x;
    float y;
    float speedX;
    float speedY;
    float radius;
    float density;
    int color = 0;


    public Ball(float x, float y, float speedX, float speedY, float radius, float density) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.radius = radius;
        this.density = density;
        id = idStatic;
        idStatic++;
    }
}
