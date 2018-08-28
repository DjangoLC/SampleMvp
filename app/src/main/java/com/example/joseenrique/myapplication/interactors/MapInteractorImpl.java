package com.example.joseenrique.myapplication.interactors;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.example.joseenrique.myapplication.Utils.GpsProvider;
import com.example.joseenrique.myapplication.interfaces.MapInteractor;

public class MapInteractorImpl implements MapInteractor,GpsProvider.listener{


    private final String TAG = getClass().getSimpleName();

    private onDataChanged listener;

    public MapInteractorImpl(onDataChanged listener){
        this.listener = listener;
    }

    @Override
    public void getCurrentInfMaps(LocationManager manager, Context context) {
        new GpsProvider(manager,context,this);
    }

    @Override
    public void dataChanged(Location location) {
        listener.notifyPresenterDataChanged(location);
        Log.i("MapInteractorImpl() ",String.format("Lat: %s, Long: %s",String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude())));
    }

    public interface onDataChanged{
        void notifyPresenterDataChanged(Location location);
    }
}
