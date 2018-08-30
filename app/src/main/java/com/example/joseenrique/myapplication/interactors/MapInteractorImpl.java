package com.example.joseenrique.myapplication.interactors;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import com.example.joseenrique.myapplication.Utils.GpsProvider;
import com.example.joseenrique.myapplication.interfaces.MapInteractor;
import com.example.joseenrique.myapplication.tasks.LocationProvider;

public class MapInteractorImpl implements MapInteractor,LocationProvider.LocationCallback{


    private final String TAG = getClass().getSimpleName();

    private onDataChanged listener;
    private LocationProvider locationProvider;

    public MapInteractorImpl(onDataChanged listener){
        this.listener = listener;
    }

    @Override
    public void getCurrentInfMaps(LocationManager manager, Context context) {
        locationProvider = new LocationProvider(context,this);
        locationProvider.connect();
    }

    @Override
    public void handleNewLocation(Location location) {
        if (location!=null){
            listener.notifyPresenterDataChanged(location);
        }
        locationProvider.disconnect();
        Log.i("MapInteractorImpl() ",String.format("Lat: %s, Long: %s",String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude())));
    }

    public interface onDataChanged{
        void notifyPresenterDataChanged(Location location);
    }
}
