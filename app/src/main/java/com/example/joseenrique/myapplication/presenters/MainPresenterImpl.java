package com.example.joseenrique.myapplication.presenters;

import android.content.Context;
import android.util.Log;

import com.example.joseenrique.myapplication.Retrofit.Controller;
import com.example.joseenrique.myapplication.interactors.MainInteractorImpl;
import com.example.joseenrique.myapplication.interfaces.MainInteractor;
import com.example.joseenrique.myapplication.interfaces.MainPresenter;
import com.example.joseenrique.myapplication.interfaces.MainView;
import com.example.joseenrique.myapplication.models.Credencials;
import com.example.joseenrique.myapplication.models.ModelsBD.AppDatabase;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelProductoBD;
import com.example.joseenrique.myapplication.models.ModelsBD.ModelStockBD;
import com.example.joseenrique.myapplication.tasks.GenericTask;

import java.util.List;


public class MainPresenterImpl implements MainPresenter,MainInteractorImpl.allDone{


    MainView view;
    MainInteractor interactor;

    public MainPresenterImpl(MainView view){
        this.view = view;
        interactor = new MainInteractorImpl(this);
    }



    @Override
    public void done(List<ModelProductoBD> data) {
    view.hideProgress();
    view.loaditems(data);
    }

    @Override
    public void downloadClubs(AppDatabase db) {
        view.showProgress();
        interactor.downloadData(db);
    }
}
