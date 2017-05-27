package com.gashe.mygeolocation;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by cice on 27/5/17.
 */

public class MyLocationListener implements LocationListener {

    private MainActivity mainActivity;

    public MyLocationListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        log("STATUS CHANGED");
        switch (status){
            case LocationProvider.AVAILABLE:
                log("Proveedor " + provider + " disponible");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                log("Proveedor " + provider + " fuera de servicio");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                log("Proveedor " + provider + " no disponible temporalmente");
                break;


        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        log("SE HA APAGADO LA LOCALIZACION GPS");
    }

    @Override
    public void onProviderEnabled(String provider) {
        log("SE HA ACTIVADO LA LOCALIZACION GPS");
    }

    @Override
    public void onLocationChanged(Location location) {
        log("LOCALIZACION CAMBIA");
        mainActivity.showLocation(location);
    }

    public void log(String s){
        Log.d(getClass().getCanonicalName(), s);
    }

}
