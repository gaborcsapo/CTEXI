package com.example.student.ctexiv1.RiderActivities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.UserClass;

public class RiderSecondActivity extends UserClass {

    String Name;
    String Number;
    String Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_second);

        if ((getIntent().getStringExtra("name") != null)){
            Name = getIntent().getStringExtra("name");
            Number = getIntent().getStringExtra("number");
            Message = getIntent().getStringExtra("message");
        }

        else if ((getIntent().getBooleanExtra("restore", false) == true)) {
            Message = loadPreference("locationmessage");
            Name = loadPreference("drivername");
            Number = loadPreference("drivernumber");
        }

        ((TextView) findViewById(R.id.message)).setText(Message);
        ((TextView) findViewById(R.id.drivername)).setText(Name);
        ((TextView) findViewById(R.id.drivernumber)).setText(Number);


    }
    @Override
    public void onBackPressed(){
        savePreference("drivername", Name);
        savePreference("drivernumber", Number);
        savePreference("locationmessage", (String) ((TextView) findViewById(R.id.message)).getText());
        super.onBackPressed();
    }



}
