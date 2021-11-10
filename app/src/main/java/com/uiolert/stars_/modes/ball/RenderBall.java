package com.uiolert.stars_.modes.ball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RenderBall {

    Paint paint;

    boolean white = false;

    public RenderBall() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(4);
    }

    public void draw(Canvas canvas, ModelBall modelBall){
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

            paint.setColor(Color.WHITE);
        for (Ball ball: modelBall.getBalls()) {
            if(ball.color != 0){
                paint.setColor(ball.color);
            }
            canvas.drawCircle(ball.x,ball.y,ball.radius,paint);
            paint.setColor(Color.WHITE);
        }

//        paint.setStyle(Paint.Style.FILL);
//        if (white){
//            paint.setColor(Color.WHITE);
//            white = false;
//        }else {
//            paint.setColor(Color.BLACK);
//            white = true;
//        }
//        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
    }

}
