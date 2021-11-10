package com.uiolert.stars_.modes.parallax;

import android.content.Context;
import android.graphics.Canvas;

import com.uiolert.stars_.Mode;
import com.uiolert.stars_.NamesMode;

public class ModeParallax extends Mode {
    ModelParallax modelParallax;
    RenderParallax renderParallax;
    public ModeParallax(Context context) {
        super(context);
        modelParallax = new ModelParallax(context);
        renderParallax = new RenderParallax();
    }

    @Override
    public void update(Canvas canvas, long elapsedTime) {
        super.update(canvas, elapsedTime);
        modelParallax.update(elapsedTime,canvas);
        renderParallax.draw(canvas,modelParallax);

    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        modelParallax = new ModelParallax(context);
        renderParallax = new RenderParallax();
    }

    @Override
    public String toString() {
        return "ModeParallax{" +
                "modelParallax=" + modelParallax +
                ", renderParallax=" + renderParallax +
                '}';
    }

    @Override
    public NamesMode getNamesMod() {
        return NamesMode.parallax;
    }
}
