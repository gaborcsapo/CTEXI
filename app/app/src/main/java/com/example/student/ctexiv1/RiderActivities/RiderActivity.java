package com.example.student.ctexiv1.RiderActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.RiderActivities.RiderWaitActivity;
import com.example.student.ctexiv1.UserClass;

public class RiderActivity extends UserClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);
        loadSavedPreferences();
    }

    protected void bookTaxi(View view){
        savePreferences();
        Intent i = new Intent(this, RiderWaitActivity.class);
        i.putExtra("PassedMessage", String.valueOf(((EditText) findViewById(R.id.Message)).getText()));
        startActivity(i);
    }

    protected void previous (View view){
        Intent i = new Intent(this, RiderSecondActivity.class);
        i.putExtra("restore", true);
        startActivity(i);
    }

}
