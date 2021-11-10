package com.uiolert.stars_.modes.parallax;

import com.uiolert.stars_.utils.Random;

public class PointSquare {
    private float x;
    private float y;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public PointSquare(float x, float y) {
        this.x = x;
        this.y = y;
    }
    public PointSquare() {
        x = Random.fromTo(0,500);
        y = Random.fromTo(0,500);
    }
}
