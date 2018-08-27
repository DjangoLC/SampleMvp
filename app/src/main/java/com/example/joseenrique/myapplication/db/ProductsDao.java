package com.example.joseenrique.myapplication.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.joseenrique.myapplication.models.ModelsBD.ModelProductoBD;

import java.util.List;

@Dao
public interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(ModelProductoBD... productoBDS);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertProducts(List<ModelProductoBD> productoBDS);

    @Query("SELECT * FROM products")
    List<ModelProductoBD> getAllProducts();

    @Query("DELETE FROM products")
    void deleteAll();


}

