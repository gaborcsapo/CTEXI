package com.example.student.ctexiv1.DriverActivities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;

import android.content.Context;
import android.widget.Toast;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.LastLocationProvider;
import com.example.student.ctexiv1.Utils.LocationActivity;
import com.example.student.ctexiv1.Utils.RequestSingleton;
import com.example.student.ctexiv1.Utils.ServerCallback;

import android.location.LocationListener;

public class DriverWaitActivity extends LocationActivity {

    private double longitudeGPS;
    private double latitudeGPS;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_wait);
        waitForRequest();
    }

    protected void waitForRequest() {

        final LocationListener locationListenerGPS = new LocationListener() {
            public void onLocationChanged(Location location) {
                longitudeGPS = location.getLongitude();
                latitudeGPS = location.getLatitude();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DriverWaitActivity.this, "GPS Provider update", Toast.LENGTH_SHORT).show();
                        final Intent i = new Intent(context, DriverSecondActivity.class);
                        RequestSingleton.getInstance(context).addToGETRequestQueue("S|0.834|28.577|0097126352855|Chimamanda_Adichie|anyad", new ServerCallback() {
                            @Override
                            public void onSuccess(String result) {

                                String[] parsedData = result.split("|");

                                i.putExtra("PassedMessage", parsedData[4]);
                                i.putExtra("PassedName", parsedData[5]);
                                i.putExtra("PassedNumber", parsedData[3]);
                                i.putExtra("PassedLat", parsedData[1]);
                                i.putExtra("PassedLong", parsedData[2]);

                                //startActivity(i);
                                //finish();
                            }
                        });
                    }
                });
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGPS);
    }
}