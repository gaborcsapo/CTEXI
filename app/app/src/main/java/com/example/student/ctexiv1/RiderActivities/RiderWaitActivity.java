package com.example.student.ctexiv1.RiderActivities;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.student.ctexiv1.DriverActivities.DriverSecondActivity;
import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.SMSReceiver;
import com.example.student.ctexiv1.Utils.LocationSMSActivity;

public class RiderWaitActivity extends LocationSMSActivity {

    private double lon;
    private double lat;
    private Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_wait);
        sendRequest();
    }

    protected void sendRequest(){
        //Send last location
        getLocation();
        lon = location.getLongitude();
        lat= location.getLatitude();
        final Intent i = new Intent(context, RiderSecondActivity.class);
        String x = info.getNumber();
        String u = info.getName();
        String y = info.getServerNumber(context);
        sendSMS(info.getServerNumber(context), "R|" + lat + "|" + lon+ "|" + info.getNumber()+"||"+ info.getName());

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