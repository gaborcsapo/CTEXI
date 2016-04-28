package com.example.student.ctexiv1.DriverActivities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.student.ctexiv1.DriverActivities.DriverWaitActivity;
import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.UserClass;

public class DriverActivity extends UserClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        loadSavedPreferences();
    }

    protected void setAvailable(View view) {
        startActivity(new Intent(this, DriverWaitActivity.class));
    }
}
