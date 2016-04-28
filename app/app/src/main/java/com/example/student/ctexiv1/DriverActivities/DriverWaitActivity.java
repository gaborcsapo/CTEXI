package com.example.student.ctexiv1.DriverActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.RiderActivities.RiderSecondActivity;

public class DriverWaitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_wait);
        waitForRequest();
    }

    protected void waitForRequest(){

        //sends and updates location to server and waits for a request

        Intent i = new Intent(this, DriverSecondActivity.class);

        i.putExtra("PassedMessage", "message");
        i.putExtra("PassedName", "Donald Trump");
        i.putExtra("PassedNumber", "12345678");

        startActivity(i);
        finish();
    }

}
