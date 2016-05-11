package com.example.student.ctexiv1.DriverActivities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.FirstActivity;
import com.example.student.ctexiv1.Utils.MapActivity;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class DriverSecondActivity extends MapActivity implements OnMapReadyCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_second);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        info.loadNameNumberMessage(this);
    }

    public void onDecline(View view){
        //send decline to server
        finish();
    }

    public void onAccept(View view){
        //sends accept to server
        Intent i = new Intent(this, DriverThirdActivity.class);
        startActivity(i);
        finish();
    }
}
