package com.example.student.ctexiv1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class RiderActivity extends UserClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);
        loadSavedPreferences();
    }

    protected void bookTaxi(View view){
        savePreferences();
        Intent i = new Intent(this, RiderSecondActivity.class);
        i.putExtra("message", String.valueOf(((EditText) findViewById(R.id.message)).getText()));
        startActivity(i);
    }

    protected void previous (View view){
        Intent i = new Intent(this, RiderSecondActivity.class);
        i.putExtra("restore", true);
        startActivity(i);
    }

}
