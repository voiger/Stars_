package com.uiolert.stars_;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import com.uiolert.stars_.engine.EngineWallpaper;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int height,width;
    EngineWallpaper engineWallpaper;

    SurfaceView surfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random random = new Random();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        height = displayMetrics.heightPixels;

        width = displayMetrics.widthPixels;

        surfaceView = findViewById(R.id.surface);

        EngineWallpaper engineWallpaper = new EngineWallpaper(surfaceView.getHolder());

//        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
//                Canvas canvas = surfaceHolder.lockCanvas();
//
//                Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//                paint.setColor(Color.CYAN);
//                paint.setStyle(Paint.Style.STROKE);
//                paint.setStrokeWidth(1);
//                for (int i = 0; i < 10000; i++) {
//                    canvas.drawPoint(random.nextInt(width),random.nextInt(height),paint);
//                }
//
//                surfaceHolder.unlockCanvasAndPost(canvas);
//            }
//
//            @Override
//            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
//
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}