package com.example.student.ctexiv1.Utils;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.widget.Toast;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import com.example.student.ctexiv1.DriverActivities.DriverSecondActivity;
import com.example.student.ctexiv1.RiderActivities.RiderSecondActivity;

import android.location.LocationListener;
import android.location.Location;
import android.location.Criteria;
import android.location.LocationManager;


public class LocationSMSActivity extends AppCompatActivity implements LocationListener {

    protected LocationManager locationManager;
    public LocationListener locationListenerGPS = null;
    protected InfoSingleton info = InfoSingleton.getInstance();
    protected Location location;
    boolean gpsStatus = false;
    boolean networkStatus = false;
    String provider;
    private final Context mContext = this;


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
        if (!(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))){
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
        return true;
    }

    public void getLocation() {
        locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = locationManager.getBestProvider(criteria, true);

        locationManager.requestLocationUpdates(provider, 60 * 100000, 0, this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;
        location = locationManager.getLastKnownLocation(provider);
        Toast.makeText(mContext, "Lat: " + location.getLatitude() + " Long: " + location.getLongitude(), Toast.LENGTH_LONG).show();
    }

    //SMS stuff
    //Method to send SMS.
    protected void sendSMS(String phoneNumber, String message) {
        PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this, LocationSMSActivity.class), 0);
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

    protected void onSMS(String message){
        SharedPreferences settings = getSharedPreferences("CHANGE_USER", MODE_PRIVATE);
        Intent i;

        if ((settings.getString("CHANGE_USER", "null")).equals("driver")){
            i = new Intent(this, DriverSecondActivity.class);
            String[] parsedData = message.split("\\|");

            info.setMessage(parsedData[4]);
            info.setName(parsedData[5]);
            info.setNumber(parsedData[3]);
            info.setLat(Double.valueOf(parsedData[1]));
            info.setLong(Double.valueOf(parsedData[2]));

            startActivity(i);
            finish();
        } else {
            i = new Intent(this, RiderSecondActivity.class);
            String[] parsedData = message.split("\\|");

            info.setMessage(parsedData[4]);
            info.setName(parsedData[5]);
            info.setNumber(parsedData[3]);
            info.setLat(Double.valueOf(parsedData[1]));
            info.setLong(Double.valueOf(parsedData[2]));

            startActivity(i);
            finish();
        }
    }
    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(mContext, "UPDATE Lat: " + location.getLatitude() + " Long: " + location.getLongitude(), Toast.LENGTH_LONG).show();
    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}
    @Override
    public void onProviderEnabled(String s) {}
    @Override
    public void onProviderDisabled(String s) {}
}

