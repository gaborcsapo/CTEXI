package com.example.student.ctexiv1.DriverActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import android.content.Context;
import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.LastLocationProvider;
import com.example.student.ctexiv1.Utils.RequestSingleton;
import com.example.student.ctexiv1.Utils.ServerCallback;

public class DriverWaitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_wait);
        waitForRequest();
    }

    protected void waitForRequest(){


        final Intent i = new Intent(this, LastLocationProvider.class);
        RequestSingleton.getInstance(this).addToGETRequestQueue("S|0.834|28.577|0097126352855|Chimamanda_Adichie|anyad", new ServerCallback() {
            @Override
            public void onSuccess(String result) {

                String[] parsedData = result.split("|");

                i.putExtra("PassedMessage", parsedData[4]);
                i.putExtra("PassedName", parsedData[5]);
                i.putExtra("PassedNumber", parsedData[3]);
                i.putExtra("PassedLat", parsedData[1]);
                i.putExtra("PassedLong", parsedData[2]);

                startActivity(i);
                finish();
            }
        });
    }
}
