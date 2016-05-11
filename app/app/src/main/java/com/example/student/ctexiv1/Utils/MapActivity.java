package com.example.student.ctexiv1.Utils;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.student.ctexiv1.PreferenceActivity;
import com.example.student.ctexiv1.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity {
    GoogleMap mMap;
    protected InfoSingleton info = InfoSingleton.getInstance();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        addMarker(info.getLat(), info.getLong());
    }

    protected void addMarker(double passedLat, double passedLong) {
        LatLng marker = new LatLng(passedLat, passedLong);
        mMap.addMarker(new MarkerOptions().position(marker).title("Your Rider is here"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }

    public void onCancel(View view){
        finish();
    }

}
