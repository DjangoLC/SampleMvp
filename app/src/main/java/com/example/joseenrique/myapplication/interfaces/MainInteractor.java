package com.example.joseenrique.myapplication.interfaces;

import android.content.Context;

import com.example.joseenrique.myapplication.Retrofit.Controller;
import com.example.joseenrique.myapplication.models.ModelsBD.AppDatabase;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelProductoBD;
import com.example.joseenrique.myapplication.tasks.GenericTask;

import java.util.List;

public interface MainInteractor {

    public void downloadData(AppDatabase db);


}
