package com.uiolert.stars_.utils;

public class Random {
    public static float fromTo(float from, float to) {
        return (float) (from + (to - from) * Math.random());
    }
}
