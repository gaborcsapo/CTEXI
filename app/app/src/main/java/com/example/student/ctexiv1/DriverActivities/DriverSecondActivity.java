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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DriverSecondActivity extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    String Name;
    String Number;
    String Message;

    protected String loadPreference(String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(key, "null");
    }
    protected void savePreference(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_second);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if ((getIntent().getStringExtra("name") != null)){
            Name = getIntent().getStringExtra("name");
            Number = getIntent().getStringExtra("number");
            Message = getIntent().getStringExtra("message");
        }

        else if ((getIntent().getBooleanExtra("restore", false) == true)) {
            Message = loadPreference("locationmessage");
            Name = loadPreference("ridername");
            Number = loadPreference("ridernumber");
        }

        ((TextView) findViewById(R.id.message)).setText(Message);
        ((TextView) findViewById(R.id.ridername)).setText(Name);
        ((TextView) findViewById(R.id.ridernumber)).setText(Number);

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
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

    @Override
    public void onBackPressed(){
        savePreference("drivername", Name);
        savePreference("drivernumber", Number);
        savePreference("locationmessage", (String) ((TextView) findViewById(R.id.message)).getText());
        super.onBackPressed();
    }
}
