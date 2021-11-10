package com.uiolert.stars_.modes.ball;

import android.content.Context;
import android.graphics.Canvas;

import com.uiolert.stars_.Mode;
import com.uiolert.stars_.NamesMode;
import com.uiolert.stars_.modes.star.ModelStar;
import com.uiolert.stars_.modes.star.RenderStar;

public class ModeBalls extends Mode {
    RenderBall renderBall;
    ModelBall modelBall;
    public ModeBalls(Context context) {
        super(context);
        renderBall = new RenderBall();
        modelBall = new ModelBall(context);
    }

    @Override
    public void update(Canvas canvas,long elapsedTime) {
        super.update(canvas,elapsedTime);
        modelBall.update(canvas);
        renderBall.draw(canvas,modelBall);
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        renderBall = new RenderBall();
        modelBall = new ModelBall(context);
    }

    @Override
    public NamesMode getNamesMod() {
        return NamesMode.ball;
    }
}
