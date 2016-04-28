package com.example.student.ctexiv1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by student on 4/17/16.
 */
public class UserClass extends AppCompatActivity{

    protected String Name;
    protected String Number;
    protected String Message;

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

    protected void savePreference(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    protected void savePreferences(){
        savePreference("OwnName", String.valueOf(((EditText) findViewById(R.id.name)).getText()));
        savePreference("OwnNumber", String.valueOf(((EditText) findViewById(R.id.number)).getText()));
    }

    public void saveBookingState(){
        savePreference("SavedName", Name);
        savePreference("SavedNumber", Number);
        savePreference("LocationMessage", (String) ((TextView) findViewById(R.id.message)).getText());
        super.onBackPressed();
    }

    protected String loadPreference(String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(key, "null");
    }

    protected void loadSavedPreferences() {
        String name = loadPreference("OwnName");
        String number = loadPreference("OwnNumber");
        if (!(name.equals("null")) )
            ((EditText) findViewById(R.id.name)).setText(name);
        if (!(number.equals("null")) )
            ((EditText) findViewById(R.id.number)).setText(number);
    }

    public void loadNameNumberMessage(){

        if ((getIntent().getStringExtra("PassedName") != null)){
            Name = getIntent().getStringExtra("PassedName");
            Number = getIntent().getStringExtra("PassedNumber");
            Message = getIntent().getStringExtra("PassedMessage");
        }

        else if ((getIntent().getBooleanExtra("restore", false) == true)) {
            Message = loadPreference("LocationMessage");
            Name = loadPreference("SavedName");
            Number = loadPreference("SavedNumber");
        }

        ((TextView) findViewById(R.id.message)).setText(Message);
        ((TextView) findViewById(R.id.name)).setText(Name);
        ((TextView) findViewById(R.id.number)).setText(Number);
    }

    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void onCancel(View view){
        finish();
    }
}
