package com.example.student.ctexiv1.RiderActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.RequestSingleton;
import com.example.student.ctexiv1.UserClass;
import com.example.student.ctexiv1.Utils.ServerCallback;

public class RiderWaitActivity extends UserClass {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_wait);
        sendRequest();
    }

    protected void sendRequest(){
        final Intent i = new Intent(this, RiderSecondActivity.class);
        RequestSingleton.getInstance(this).addToRequestQueue("nothing", new ServerCallback() {
            @Override
            public void onSuccess(String result) {

                i.putExtra("PassedMessage", getIntent().getStringExtra("PassedMessage"));
                i.putExtra("PassedName", "Donald Trump");
                i.putExtra("PassedNumber", "12345678");

                startActivity(i);
                finish();
            }
        });


    }
}
