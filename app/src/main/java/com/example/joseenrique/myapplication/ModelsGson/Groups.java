package com.example.joseenrique.myapplication.ModelsGson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Enrique on 21/08/2018.
 */

public class Groups {

    @SerializedName("id")
    private int id;

    @SerializedName("n")
    private String n;

    public Groups() {

    }

    public Groups(int id, String n) {
        this.id = id;
        this.n = n;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
}
