package com.example.student.ctexiv1.DriverActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.UserTemplate;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class DriverThirdActivity extends UserTemplate implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_third);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        addMarker(getIntent().getDoubleExtra("PassedLat", 0), getIntent().getDoubleExtra("PassedLong", 0));
    }

    public void onCancel(View view){

        //cancels the booking

        finish();
    }

    @Override
    public void onBackPressed(){
        savePreference("SavedName", Name);
        savePreference("SavedNumber", Number);
        savePreference("LocationMessage", (String) ((TextView) findViewById(R.id.Message)).getText());
        super.onBackPressed();
    }
}
