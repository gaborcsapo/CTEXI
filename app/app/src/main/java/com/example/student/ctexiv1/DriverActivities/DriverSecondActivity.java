package com.example.student.ctexiv1.DriverActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.UserTemplate;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class DriverSecondActivity extends UserTemplate implements OnMapReadyCallback{

    String Name;
    String Number;
    String Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_second);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        loadNameNumberMessage();

        addMarker(Double.parseDouble(getIntent().getStringExtra("PassedLat")), Double.parseDouble(getIntent().getStringExtra("PassedLong")));
    }

    public void onDecline(View view){

        //send decline to server

        finish();

    }
    public void onAccept(View view){

        //sends accept to server

        Intent i = new Intent(this, DriverThirdActivity.class);

        i.putExtra("PassedMessage", Message);
        i.putExtra("PassedName", Name);
        i.putExtra("PassedNumber", Number);
        i.putExtra("PassedLat", Double.parseDouble(getIntent().getStringExtra("PassedLat")));
        i.putExtra("PassedLong", Double.parseDouble(getIntent().getStringExtra("PassedLong")));

        startActivity(i);
        finish();
    }
}
