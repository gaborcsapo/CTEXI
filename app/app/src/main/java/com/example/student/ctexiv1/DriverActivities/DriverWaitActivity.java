package com.example.student.ctexiv1.DriverActivities;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;

import android.content.Context;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.IncomingSMS;
import com.example.student.ctexiv1.Utils.LocationSMSActivity;
import com.example.student.ctexiv1.Utils.RequestSingleton;
import com.example.student.ctexiv1.Utils.ServerCallback;

import android.location.LocationListener;

public class DriverWaitActivity extends LocationSMSActivity {

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
                        Toast.makeText(DriverWaitActivity.this, "GPS Provider update" + longitudeGPS + latitudeGPS, Toast.LENGTH_SHORT).show();
                        final Intent i = new Intent(context, DriverSecondActivity.class);
                        sendSMS("0971563052867", "S|" + latitudeGPS + "|" + latitudeGPS+ "|" + getIntent().getStringExtra("PassedNumber")+"||"+ getIntent().getStringExtra("PassedName"));

                        /*
                        RequestSingleton.getInstance(context).addToGETRequestQueue("S|" + latitudeGPS + "|" + latitudeGPS+ "|" + getIntent().getStringExtra("PassedNumber")+"||"+ getIntent().getStringExtra("PassedName"), new ServerCallback() {
                            @Override
                            public void onSuccess(String result) {

                                String[] parsedData = result.split("|");

                                i.putExtra("PassedMessage", parsedData[4]);
                                i.putExtra("PassedName", parsedData[5]);
                                i.putExtra("PassedNumber", parsedData[3]);
                                i.putExtra("PassedLat", parsedData[1]);
                                i.putExtra("PassedLong", parsedData[2]);

                                startActivity(i);
                                finish();
                            }
                        });
                        */
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

        IncomingSMS smslist = new IncomingSMS();
        smslist.setActivity(this);
        IntentFilter fltr_smsreceived = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smslist,fltr_smsreceived);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGPS);
    }
}