package com.example.joseenrique.myapplication.ModelsGson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Enrique on 21/08/2018.
 */

public class ModelProductosGson {

    @SerializedName("dateTime")
    private String dateTime;

    @SerializedName("size")
    private int size;

    @SerializedName("content")
    private ContentGson contentGson;

    public ModelProductosGson() {

    }

    public ModelProductosGson(String dateTime, int size, ContentGson contentGson) {
        this.dateTime = dateTime;
        this.size = size;
        this.contentGson = contentGson;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public ContentGson getContentGson() {
        return contentGson;
    }

    public void setContentGson(ContentGson contentGson) {
        this.contentGson = contentGson;
    }
}
