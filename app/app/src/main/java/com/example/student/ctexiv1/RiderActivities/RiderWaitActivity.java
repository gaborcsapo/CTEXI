package com.example.student.ctexiv1.RiderActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.student.ctexiv1.R;

public class RiderWaitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_wait);
        sendRequest();
    }

    protected void sendRequest(){


        //gets the inputs from the intent, then sends request and waits for the answer from the server


        Intent i = new Intent(this, RiderSecondActivity.class);

        i.putExtra("PassedMessage", getIntent().getStringExtra("message"));
        i.putExtra("PassedName", "Donald Trump");
        i.putExtra("PassedNumber", "12345678");

        startActivity(i);
        finish();
    }
}
