package com.example.joseenrique.myapplication.interfaces;

import android.location.Location;

public interface MapView {

    void setInfo(Location location);

    void showProgress(boolean isShow);

}
