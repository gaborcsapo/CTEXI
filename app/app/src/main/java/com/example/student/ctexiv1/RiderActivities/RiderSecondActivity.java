package com.example.student.ctexiv1.RiderActivities;

import android.os.Bundle;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.UserTemplate;

public class RiderSecondActivity extends UserTemplate {

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
        savePreference("LocationMessage", Message);
        super.onBackPressed();
    }

}
