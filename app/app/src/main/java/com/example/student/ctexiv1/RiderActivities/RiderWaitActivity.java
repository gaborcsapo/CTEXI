package com.example.student.ctexiv1.RiderActivities;

import android.content.Intent;
import android.os.Bundle;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.RequestSingleton;
import com.example.student.ctexiv1.Utils.UserTemplate;
import com.example.student.ctexiv1.Utils.ServerCallback;

public class RiderWaitActivity extends UserTemplate {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_wait);
        sendRequest();
    }

    protected void sendRequest(){
        final Intent i = new Intent(this, RiderSecondActivity.class);



        RequestSingleton.getInstance(this).addToGETRequestQueue("S|0.834|28.577|0097126352855|Chimamanda_Adichie", new ServerCallback() {
            @Override
            public void onSuccess(String result) {

                String[] parsedData = result.split("|");

                i.putExtra("PassedMessage", getIntent().getStringExtra("PassedMessage"));
                i.putExtra("PassedName", parsedData[5]);
                i.putExtra("PassedNumber", parsedData[3]);

                startActivity(i);
                finish();
            }
        });


    }
}
