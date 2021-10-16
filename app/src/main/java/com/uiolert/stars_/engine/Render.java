package com.uiolert.stars_.engine;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Render {

    Paint paint;

    public Render() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(4);
    }

    public void draw(Canvas canvas, Model model) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

        paint.setStyle(Paint.Style.STROKE);

        for (Point point : model.getPoints()) {
            float sx = canvas.getWidth() / 2f + (canvas.getWidth() / 2f * point.x / point.z);
            float sy = canvas.getHeight() / 2f + (canvas.getHeight() / 2f * point.y / point.z);

            int isx = Math.round(sx);
            int isy = Math.round(sy);

            int sx2 = Math.round(sx + ((sx - isx > 0) ? 1 : -1));
            int sy2 = Math.round(sy + ((sy - isy > 0) ? 1 : -1));

            if (sx2 < canvas.getWidth() && sx2 >= 0
                    && sy2 < canvas.getHeight() && sy2 >= 0) {
                float gain = (Math.abs(sx - isx) + Math.abs(sy - isy)) / 2;
                float colorGain = ((float) (255 + (int) (point.z * (255 / Math.abs(Model.INITIAL_Z_COORD))))) / 255f;
                colorGain = colorGain * gain;
                int colorR = (point.color & 0xff0000) >> 16;
                int colorG = (point.color & 0xff00) >> 8;
                int colorB = (point.color & 0xff);
                int color = 0xff000000
                        | (int) (colorR * colorGain) << 16
                        | (int) (colorG * colorGain) << 8
                        | (int) (colorB * colorGain);
                paint.setColor(color);
                canvas.drawPoint(sx2, sy2, paint);
            }

            if (isx < canvas.getWidth() && isx >= 0
                    && isy < canvas.getHeight() && isy >= 0) {
                float colorGain = ((float) (255 + (int) (point.z * (255 / Math.abs(Model.INITIAL_Z_COORD))))) / 255f;
                int colorR = (point.color & 0xff0000) >> 16;
                int colorG = (point.color & 0xff00) >> 8;
                int colorB = (point.color & 0xff);
                int color = 0xff000000
                        | (int) (colorR * colorGain) << 16
                        | (int) (colorG * colorGain) << 8
                        | (int) (colorB * colorGain);
                paint.setColor(color);
                canvas.drawPoint(isx, isy, paint);
            }
        }
    }
}