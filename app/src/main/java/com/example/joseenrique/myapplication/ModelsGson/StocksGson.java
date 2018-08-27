package com.example.joseenrique.myapplication.ModelsGson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Enrique on 21/08/2018.
 */

public class StocksGson {

    @SerializedName("p")
    private int p;

    @SerializedName("w")
    private int w;

    @SerializedName("s")
    private double s;

    public StocksGson() {
    }

    public StocksGson(int p, int w, double s) {
        this.p = p;
        this.w = w;
        this.s = s;
    }

    public int getP() {
        return p;
    }

    public void setP(int p) {
        this.p = p;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public double getS() {
        return s;
    }

    public void setS(double s) {
        this.s = s;
    }
}
