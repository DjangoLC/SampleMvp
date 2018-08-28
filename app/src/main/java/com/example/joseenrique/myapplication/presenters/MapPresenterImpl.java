package com.example.joseenrique.myapplication.presenters;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import com.example.joseenrique.myapplication.interactors.MapInteractorImpl;
import com.example.joseenrique.myapplication.interfaces.MapInteractor;
import com.example.joseenrique.myapplication.interfaces.MapPresenter;
import com.example.joseenrique.myapplication.interfaces.MapView;

public class MapPresenterImpl implements MapPresenter,MapInteractorImpl.onDataChanged{

    private MapView view;
    private MapInteractor interactor;

    public MapPresenterImpl(MapView view) {
        this.view = view;
        this.interactor = new MapInteractorImpl(this);

    }

    @Override
    public void setInfo(LocationManager manager,Context mContext) {
        if (view != null) {
            view.showProgress(true);
            interactor.getCurrentInfMaps(manager,mContext);
        }
    }

    @Override
    public void notifyPresenterDataChanged(Location location) {
        view.showProgress(false);
        view.setInfo(location);
    }
}
