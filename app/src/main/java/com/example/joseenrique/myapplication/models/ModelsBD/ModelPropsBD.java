package com.example.joseenrique.myapplication.models.ModelsBD;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ModelPropsBD {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "n")
    private String n;

    ModelPropsBD(){

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
