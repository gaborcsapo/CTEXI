package com.example.student.ctexiv1.DriverActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.FirstActivity;
import com.example.student.ctexiv1.Utils.MapActivity;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class DriverThirdActivity extends MapActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_third);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        info.loadNameNumberMessage(this);
        ((Chronometer)findViewById(R.id.Countdown)).setBase(100);
    }

    public void onCancel(View view){
        //cancels the booking
        finish();
    }

    @Override
    public void onBackPressed(){
        info.saveBookingState(this);
        super.onBackPressed();
    }
}
