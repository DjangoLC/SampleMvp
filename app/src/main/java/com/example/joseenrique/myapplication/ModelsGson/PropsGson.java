package com.example.joseenrique.myapplication.ModelsGson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Enrique on 21/08/2018.
 */

public class PropsGson {

    @SerializedName("id")
    private String id;

    @SerializedName("n")
    private String n;

    public PropsGson() {
    }

    public PropsGson(String id, String n) {
        this.id = id;
        this.n = n;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
}
