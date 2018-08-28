package com.example.joseenrique.myapplication.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

public class GpsProvider implements android.location.LocationListener{

    private LocationManager manager=null;
    private Context context;
    private listener listener;

    public GpsProvider(LocationManager manager,Context context,listener listener){
        this.manager = manager;
        this.context = context;
        this.listener = listener;
        init();
    }

    public interface listener{
        void dataChanged(Location location);
    }

    private final String TAG = getClass().getSimpleName();

    @Override
    public void onLocationChanged(Location location) {
        if (location!=null){
            Log.i(TAG,String.format("Lat: %s, Long: %s",String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude())));
            listener.dataChanged(location);
        } else{
            Log.i(TAG,"Location is null");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    public void init(){
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                2000,
                10, this);
    }
}
