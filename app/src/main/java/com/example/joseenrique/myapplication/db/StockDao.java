package com.example.joseenrique.myapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.joseenrique.myapplication.models.ModelsBD.ModelStockBD;

import java.util.List;

@Dao
public interface StockDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertStock(List<ModelStockBD> modelStockBDS);

    @Query("SELECT * FROM stock")
    List<ModelStockBD> getAllStock();

    @Query("DELETE FROM   stock")
    void deleteAll();
}
