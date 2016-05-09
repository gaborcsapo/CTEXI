package com.example.student.ctexiv1.Utils;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.ctexiv1.DriverActivities.DriverActivity;
import com.example.student.ctexiv1.DriverActivities.DriverSecondActivity;
import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.RiderActivities.RiderActivity;
import com.example.student.ctexiv1.RiderActivities.RiderSecondActivity;


public class LocationSMSActivity extends AppCompatActivity {

    protected LocationManager locationManager;
    public LocationListener locationListenerGPS = null;
    double longitudeBest;
    double longitudeGPS;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!checkLocation())
            return;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void onStop() {
        super.onStop();
        /*
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(locationListenerGPS);
        */
    }

    //SMS stuff
    // Method to send SMS.
    protected void sendSMS(String phoneNumber, String message)
    {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, LocationSMSActivity.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    protected void onSMS(String message){
        SharedPreferences settings = getSharedPreferences("CHANGE_USER", MODE_PRIVATE);
        Intent i = null;

        if ((settings.getString("CHANGE_USER", "null")).equals("driver")){
            i = new Intent(this, DriverSecondActivity.class);
            String[] parsedData = message.split("\\|");

            i.putExtra("PassedMessage", parsedData[4]);
            i.putExtra("PassedName", parsedData[5]);
            i.putExtra("PassedNumber", parsedData[3]);
            i.putExtra("PassedLat", parsedData[1]);
            i.putExtra("PassedLong", parsedData[2]);

            startActivity(i);
            finish();
        } else {
            i = new Intent(this, RiderSecondActivity.class);
            String[] parsedData = message.split("\\|");

            i.putExtra("PassedMessage", parsedData[4]);
            i.putExtra("PassedName", parsedData[5]);
            i.putExtra("PassedNumber", parsedData[3]);
            i.putExtra("PassedLat", parsedData[1]);
            i.putExtra("PassedLong", parsedData[2]);

            startActivity(i);
            finish();
        }

    }
}

