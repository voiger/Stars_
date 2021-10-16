package com.uiolert.stars_.engine;

import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class EngineWallpaper {

    private Model model;
    private Render render;

    private SurfaceHolder.Callback callback;
    private SurfaceHolder surfaceHolder;

    private volatile boolean stopped;

    long time = System.nanoTime();

    Runnable threadRunnable = new Runnable() {
        @Override
        public void run() {
            while (!stopped)  {
                Canvas canvas;
                if (surfaceHolder == null || (canvas = surfaceHolder.lockCanvas()) == null) {
                    synchronized (EngineWallpaper.this) {
                        try {
                            EngineWallpaper.this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    long timeElapsed = System.nanoTime() - time;
                    time = System.nanoTime();
                    model.update(timeElapsed);
                    render.draw(canvas, model);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    };

    public void stop() {
        this.stopped = true;
    }

    public EngineWallpaper(SurfaceHolder surfaceHolder) {
        model = new Model();
        render = new Render();

        Thread engineThread = new Thread(threadRunnable, "EngineThread");
        engineThread.start();

        callback = new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                EngineWallpaper.this.surfaceHolder = surfaceHolder;
                synchronized (EngineWallpaper.this) {
                    EngineWallpaper.this.notifyAll();
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