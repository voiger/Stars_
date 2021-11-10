package com.uiolert.stars_.utils;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.uiolert.stars_.engine.EngineWallpaper;

import java.util.Arrays;

public class Accelerometer {

    public float x = 0;
    public float y = 0;
    public float z = 0;

    private SensorManager sensorManager;

    public Accelerometer(Context context) {
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        Sensor defaultGyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        sensorManager.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];
                //Log.e("XYZ", Arrays.toString(sensorEvent.values));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        },defaultGyroscope,SensorManager.SENSOR_DELAY_NORMAL);
    }
}
