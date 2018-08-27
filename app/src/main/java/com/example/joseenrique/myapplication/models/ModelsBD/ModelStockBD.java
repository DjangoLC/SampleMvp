package com.example.joseenrique.myapplication.models.ModelsBD;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

@Entity(tableName = "stock",primaryKeys = {"p", "w"} )
public class ModelStockBD {

    @ColumnInfo(name = "p")
    private int product;
    @ColumnInfo(name = "w")
    private int wareHouse;
    @ColumnInfo(name = "s")
    private double stock;

    public ModelStockBD(){

    }


    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public int getWareHouse() {
        return wareHouse;
    }

    public void setWareHouse(int wareHouse) {
        this.wareHouse = wareHouse;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }
}
