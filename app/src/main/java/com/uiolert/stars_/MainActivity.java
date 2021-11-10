package com.uiolert.stars_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uiolert.stars_.engine.EngineWallpaper;
import com.uiolert.stars_.modes.ball.ModeBalls;
import com.uiolert.stars_.modes.parallax.ModeParallax;
import com.uiolert.stars_.modes.star.ModeStar;
import com.uiolert.stars_.utils.Accelerometer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int height,width;

    ViewPager2 viewPager2;
    SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;


        viewPager2 = findViewById(R.id.ViewPager2);
        surfaceView = findViewById(R.id.surface);
        AdapterViewPager2 adapterViewPager2 = new AdapterViewPager2(this);
        ArrayList<Mode> modes = new ArrayList<>();
        modes.add(new ModeParallax(this));

        modes.add(new ModeBalls(this));
        modes.add(new ModeStar(this));

        adapterViewPager2.setModes(modes);
        viewPager2.setAdapter(adapterViewPager2);
        final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        SharedPreferences sharedPreferences = getSharedPreferences("Mode",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                editor.putString("mode",modes.get(position).getNamesMod().name());
                editor.apply();
            }
        });

        //EngineWallpaper engineWallpaper = new EngineWallpaper(surfaceView.getHolder(),this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}