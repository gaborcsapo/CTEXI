package com.example.student.ctexiv1.RiderActivities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.UserClass;

public class RiderSecondActivity extends UserClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_second);

        loadNameNumberMessage();

    }
    @Override
    public void onBackPressed(){
        savePreference("SavedName", Name);
        savePreference("SavedNumber", Number);
        savePreference("LocationMessage", (String) ((TextView) findViewById(R.id.message)).getText());
        super.onBackPressed();
    }

}
