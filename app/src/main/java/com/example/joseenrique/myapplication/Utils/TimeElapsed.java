package com.example.joseenrique.myapplication.Utils;

public class TimeElapsed {

    private static long starTime;
    private static long endTime;

    public static void StarTime(){
        starTime = System.currentTimeMillis();
    }

    public static void EndTime(){
        endTime = System.currentTimeMillis();
    }

    public static long getDuration(){
        return (endTime) - (starTime);
    }

    public static long getStarTime() {
        return starTime;
    }

    public static void setStarTime(long starTime) {
        TimeElapsed.starTime = starTime;
    }

    public static long getEndTime() {
        return endTime;
    }

    public static void setEndTime(long endTime) {
        TimeElapsed.endTime = endTime;
    }
}
