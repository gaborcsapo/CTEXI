package com.example.student.ctexiv1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.student.ctexiv1.DriverActivities.DriverActivity;
import com.example.student.ctexiv1.RiderActivities.RiderActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings = getSharedPreferences("CHANGE_USER", MODE_PRIVATE);
        Intent i = null;

        if ((settings.getString("CHANGE_USER", "null")).equals("driver")){
            i = new Intent(this, DriverActivity.class);
        } else {
            i = new Intent(this, RiderActivity.class);
        }
        startActivity(i);
        finish();
    }
}
