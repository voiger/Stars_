package com.uiolert.stars_.modes.parallax;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class RenderParallax{
    Paint paint;
    public RenderParallax() {
        paint = new Paint();
    }

    public void draw(Canvas canvas, ModelParallax modelParallax){

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

//        paint.setStyle(Paint.Style.STROKE);
        for(Square square: modelParallax.getSquares()){
            paint.setColor(square.getColor());
            //canvas.drawRect(square.getPointTopLeft().getX(),square.getPointTopRight().getX(),square.getPointBottomLeft().getX(),square.getPointBottomRight().getX(),paint);
            canvas.drawRect(square.getStartPoint().getX(),square.getStartPoint().getY(),square.getEndPoint().getX(),square.getEndPoint().getY(),paint);
            //canvas.drawRect(500,500,100,100,paint);
        }
    }
}
