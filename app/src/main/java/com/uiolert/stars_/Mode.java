package com.uiolert.stars_;

import android.content.Context;
import android.graphics.Canvas;

public class Mode {
    Context context;
    NamesMode namesMod = NamesMode.mode;

    public NamesMode getNamesMod() {
        return namesMod;
    }

    public void setNamesMod(NamesMode namesMod) {
        this.namesMod = namesMod;
    }

    public void update(Canvas canvas, long elapsedTime) {
    }

    public Mode(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Mode{" +
                "context=" + context +
                '}';
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
