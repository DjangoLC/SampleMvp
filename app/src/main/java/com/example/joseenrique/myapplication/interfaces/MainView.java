package com.example.joseenrique.myapplication.interfaces;

import com.example.joseenrique.myapplication.models.ModelsBD.ModelProductoBD;


import java.util.List;

public interface MainView {

    void showProgress();
    void hideProgress();

    void loaditems(List<ModelProductoBD> objects);

}
