package com.uiolert.stars_.modes.parallax;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;

import com.uiolert.stars_.utils.Random;

import java.util.ArrayList;

public class ModelParallax {
    Context context;
    ArrayList<Square> squares = new ArrayList<>();

    public ModelParallax(Context context) {
        this.context = context;

    }

    public void update(long elapsedTime, Canvas canvas) {
        int colorAdd = 0xFF;
        for (int i = squares.size(); i < 100; i++) {
//            int colorR = (~0xe0 & 0xff0000) >> 16;
//            int colorG = (0xff00) >> 8;
//            int colorB = (0xff);
//            colorR += colorAdd+i;
//            colorAdd = colorR + 5;

            int color = 0xff000000
                    | (int) (colorAdd) << 16
                    | (int) (0x00) << 8
                    | (int) (0x00);
            colorAdd -= 10;
            float randomW = canvas.getWidth()/2f;
            float randomH = canvas.getHeight()/2f;
            squares.add(new Square(new PointSquare(randomW, i), new PointSquare(randomW + i, randomH +i),
                    i/10f, i/10f, color));

//            float randomW = Random.fromTo(0, canvas.getWidth());
//            float randomH = Random.fromTo(0, canvas.getHeight());
//            squares.add(new Square(new PointSquare(randomW, randomH + Random.fromTo(0, 100)), new PointSquare(randomW + Random.fromTo(0, 100), randomH + Random.fromTo(0, 100)),
//                    Random.fromTo(-5, 5), Random.fromTo(-5, 5), color));
        }
        for (Square square : squares) {
            ArrayList<PointSquare> pointSquares = square.getPointsSquare();

            calculatorSpeed(square);
            PointSquare start = square.getStartPoint();
            PointSquare end = square.getEndPoint();
            if (start.getX() > canvas.getWidth() ||
                    end.getX() > canvas.getWidth()) {
                square.setSpeedX(-square.getSpeedX());

            }
            if (start.getX() < 0 ||
                    end.getX() < 0) {
                square.setSpeedX(-square.getSpeedX());

            }
            if (start.getY() > canvas.getHeight() ||
                    end.getY() > canvas.getHeight()) {
                square.setSpeedY(-square.getSpeedY());
            }
            if (start.getY() < 0 ||
                    end.getY() < 0) {
                square.setSpeedY(-square.getSpeedY());

            }

//            for (PointSquare pointSquare: pointSquares){
//                pointSquare.setX(pointSquare.getX()+square.getSpeedX());
//                pointSquare.setY(pointSquare.getY()+square.getSpeedY());
//                if (pointSquare.getX() > canvas.getWidth()){
//                    square.setSpeedX(-square.getSpeedX());
//                    square.setSpeedY(-square.getSpeedY());
//                }
//                if (pointSquare.getX() < 0){
//                    square.setSpeedX(-square.getSpeedX());
//                    square.setSpeedY(-square.getSpeedY());
//                }
//                if (pointSquare.getY() < canvas.getHeight()){
//                    square.setSpeedX(-square.getSpeedX());
//                    square.setSpeedY(-square.getSpeedY());
//                }
//                if (pointSquare.getY() < 0){
//                    square.setSpeedY(-square.getSpeedY());
//                    square.setSpeedX(-square.getSpeedX());
//                }
//            }

        }

    }

    void calculatorSpeed(Square square) {
        square.getStartPoint().setX(square.getStartPoint().getX() + square.getSpeedX());
        square.getStartPoint().setY(square.getStartPoint().getY() + square.getSpeedY());
        square.getEndPoint().setX(square.getEndPoint().getX() + square.getSpeedX());
        square.getEndPoint().setY(square.getEndPoint().getY() + square.getSpeedY());

    }

    public ArrayList<Square> getSquares() {
        return squares;
    }
}
