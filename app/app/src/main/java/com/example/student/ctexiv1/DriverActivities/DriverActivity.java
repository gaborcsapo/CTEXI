package com.example.student.ctexiv1.DriverActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.FirstActivity;

public class DriverActivity extends FirstActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        loadOwnInfo();
    }

    protected void setAvailable(View view) {
        Intent i = new Intent(this, DriverWaitActivity.class);
        info.setName(String.valueOf(((EditText) findViewById(R.id.Name)).getText()));
        info.setNumber(String.valueOf(((EditText) findViewById(R.id.Number)).getText()));
        startActivity(i);
    }
}
