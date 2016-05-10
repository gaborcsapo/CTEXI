package com.example.student.ctexiv1.DriverActivities;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;

import android.content.Context;
import android.widget.Toast;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.SMSReceiver;
import com.example.student.ctexiv1.Utils.LocationSMSActivity;

import android.location.LocationListener;

public class DriverWaitActivity extends LocationSMSActivity {

    private double lon;
    private double lat;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_wait);
        sendRequest();
    }

    protected void sendRequest(){
        //Send last location
        getLocation();
        lon = location.getLongitude();
        lat= location.getLatitude();
        final Intent i = new Intent(context, DriverSecondActivity.class);
        String x = info.getNumber();
        String u = info.getName();
        String y = info.getServerNumber(context);
        sendSMS(info.getServerNumber(context), "S|" + lat + "|" + lon+ "|" + info.getNumber()+"||"+ info.getName());

        //REGISTER SMS BROADCAST RECEIVER
        SMSReceiver smslist = new SMSReceiver();
        smslist.setActivity(this);
        IntentFilter fltr_smsreceived = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(smslist,fltr_smsreceived);
    }
}



/*

HTTP REQUEST CODE --- FOR FUTURE REFERENCE


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