package com.gashe.mygeolocation;

import android.Manifest;

import android.app.FragmentManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private String provider;
    private MyLocationListener myLocationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        provider = LocationManager.GPS_PROVIDER;
        myLocationListener = new MyLocationListener(this);


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // AQUÍ SUPONEMOS QUE EL USUARIO ACEPTA
        log("Permiso concedido");
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(locationManager.isProviderEnabled(provider)){
            log("Tengo el proveedor activado");
            Location location = locationManager.getLastKnownLocation(provider);
            showLocation(location);
        }else{
            log("No tengo el proveedor activado");
            solicitarActivacionGPS();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(locationManager.isProviderEnabled(provider)){
            log("Tengo el proveedor activado");
            Location location = locationManager.getLastKnownLocation(provider);
            locationManager.requestLocationUpdates(provider, 5000, 0, myLocationListener);
            showLocation(location);
        }else{
            log("El usuario no accede a activar el GPS");
            finish();
        }*/
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        locationManager.requestLocationUpdates(provider, 5000, 0, myLocationListener);

    }

    @Override
    protected void onPause() {
        super.onPause();

        log("La aplicación deja de estar visible. Desactivo el seguimiento");
        locationManager.removeUpdates(myLocationListener);

    }

    public void solicitarActivacionGPS(){

        FragmentManager fragmentManager = getFragmentManager();
        DialogGPS dialogGPS = new DialogGPS();
        dialogGPS.show(fragmentManager, "aviso");
    }

    public void showLocation(Location location){

        double lat = 0;
        double lng = 0;
        double alt = 0;

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyyy 'a las ' hh:mm:ss");

        TextView textPosition = (TextView) findViewById(R.id.textPosition);
        TextView textHour = (TextView) findViewById(R.id.textHour);

        textHour.setText(dateFormat.format(calendar.getTime()));

        if(null != location){
            lat = location.getLatitude();
            lng = location.getLongitude();
            alt = location.getAltitude();

            textPosition.setText("lat: " + lat + " lng: " + lng + " alt: " + alt);
        }

    }

    public void log(String s){
        Log.d(getClass().getCanonicalName(), s);
    }

}
