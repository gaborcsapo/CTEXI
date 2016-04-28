package com.example.student.ctexiv1.DriverActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.student.ctexiv1.DriverActivities.DriverThirdActivity;
import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.UserClass;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DriverSecondActivity extends UserClass implements OnMapReadyCallback{

    private GoogleMap mMap;
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

        startActivity(i);
        finish();
    }
}
