package com.example.student.ctexiv1.DriverActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.RiderActivities.RiderWaitActivity;
import com.example.student.ctexiv1.Utils.UserTemplate;

public class DriverActivity extends UserTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        loadSavedPreferences();
    }

    protected void setAvailable(View view) {
        Intent i = new Intent(this, DriverWaitActivity.class);
        i.putExtra("PassedName", String.valueOf(((EditText) findViewById(R.id.Name)).getText()));
        i.putExtra("PassedNumber", String.valueOf(((EditText) findViewById(R.id.Number)).getText()));
        startActivity(i);
    }
}
