package com.uiolert.stars_.modes.parallax;

import java.util.ArrayList;

public class Square {
    private PointSquare startPoint;
    private PointSquare endPoint;
    private float speedX;
    private float speedY;
    private int color;

    public Square(PointSquare startPoint, PointSquare endPoint, float speedX, float speedY, int color) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.speedX = speedX;
        this.speedY = speedY;
        this.color = color;
    }

    public PointSquare getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(PointSquare startPoint) {
        this.startPoint = startPoint;
    }

    public PointSquare getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(PointSquare endPoint) {
        this.endPoint = endPoint;
    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public ArrayList<PointSquare> getPointsSquare(){
        ArrayList<PointSquare> squares = new ArrayList<>();
        squares.add(startPoint);
        squares.add(endPoint);

        return squares;
    }


}
