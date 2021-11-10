package com.uiolert.stars_.modes.star;

import android.content.Context;
import android.graphics.Canvas;

import com.uiolert.stars_.Mode;
import com.uiolert.stars_.NamesMode;

public class ModeStar extends Mode {
    ModelStar modelStar;
    RenderStar renderStar;
    public ModeStar(Context context) {
        super(context);
        modelStar = new ModelStar();
        renderStar = new RenderStar();
    }

    @Override
    public void update(Canvas canvas,long elapsedTime) {
        super.update(canvas,elapsedTime);
        modelStar.update(elapsedTime);
        renderStar.draw(canvas,modelStar);
    }

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        modelStar = new ModelStar();
        renderStar = new RenderStar();
    }

    @Override
    public NamesMode getNamesMod() {
        return NamesMode.star;
    }
}
