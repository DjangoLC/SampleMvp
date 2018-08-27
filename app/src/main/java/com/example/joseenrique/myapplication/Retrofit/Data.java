package com.example.joseenrique.myapplication.Retrofit;


import com.google.gson.annotations.Expose;

public class Data {
    @Expose
    String data;

    public Data(){

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
