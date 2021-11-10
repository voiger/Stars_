package com.uiolert.stars_.engine;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.uiolert.stars_.Mode;
import com.uiolert.stars_.NamesMode;
import com.uiolert.stars_.modes.ball.ModeBalls;
import com.uiolert.stars_.modes.parallax.ModeParallax;
import com.uiolert.stars_.modes.star.ModeStar;

import java.util.jar.Attributes;


public class EngineWallpaper {

    private long timer = System.currentTimeMillis();
    private int fpsMath = 0;
    boolean lockCanvas = false;
    private SurfaceHolder.Callback callback;
    private SurfaceHolder surfaceHolder;
    private volatile boolean stopped;
    long time = System.nanoTime();

    Context context;
    Mode mode;
    Runnable threadRunnable = new Runnable() {
        @Override
        public void run() {
            while (!stopped) {
                Canvas canvas;
                if (surfaceHolder == null || lockCanvas) {
                    synchronized (EngineWallpaper.this) {
                        try {
                            EngineWallpaper.this.wait();
                        } catch (InterruptedException e) {

                        }
                    }
                } else {
                    long elapsedTime = System.nanoTime() - time;
                    time = System.nanoTime();
                    try {
                        canvas = surfaceHolder.lockCanvas();
                        lockCanvas = true;

                        mode.update(canvas, elapsedTime);

                        surfaceHolder.unlockCanvasAndPost(canvas);

                    } catch (Exception e) {

                    }
                    lockCanvas = false;
                    fpsMath++;
                    if (System.currentTimeMillis() - timer > 1000) {
                        timer += 1000;
                        Log.e("Fps", fpsMath + "");
                        fpsMath = 0;
                    }
                }
            }
        }
    };

    public void stop() {
        this.stopped = true;
    }

    Gson gson = new Gson();

    public EngineWallpaper(SurfaceHolder surfaceHolder, Context context) {
        this.context = context;
        this.surfaceHolder = surfaceHolder;


        SharedPreferences sharedPreferences = context.getSharedPreferences("Mode", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedPreferences.edit();
        NamesMode namesMode = NamesMode.valueOf(sharedPreferences.getString("mode", ""));
        switch (namesMode){
            case parallax:
                mode = new ModeParallax(context);
                break;
            case ball:
                mode = new ModeBalls(context);
                break;
            case star:
                mode = new ModeStar(context);
                break;
        }

        Log.e("Mode", "EngineWallpaper: "+mode.toString() );

        init();
    }

    public EngineWallpaper(SurfaceHolder surfaceHolder, Context context, Mode mode) {
        this.context = context;
        this.mode = mode;
        this.surfaceHolder = surfaceHolder;

        init();
    }

    private void init() {
        Thread engineThread = new Thread(threadRunnable, "EngineThread"+mode.getNamesMod().name());
        engineThread.start();

        callback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                EngineWallpaper.this.surfaceHolder = surfaceHolder;
                synchronized (EngineWallpaper.this) {
                    lockCanvas = false;
                    EngineWallpaper.this.notify();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                EngineWallpaper.this.surfaceHolder = surfaceHolder;
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                EngineWallpaper.this.surfaceHolder = null;
            }
        };

        surfaceHolder.addCallback(callback);
    }

}

