package com.example.student.ctexiv1.RiderActivities;

import android.os.Bundle;
import android.view.View;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.FirstActivity;
import com.example.student.ctexiv1.Utils.InfoSingleton;

public class RiderSecondActivity extends FirstActivity {
    protected InfoSingleton info = InfoSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_second);
        if (getIntent().getBooleanExtra("restore", false))
            info.loadPrevInfo(this);
        else
            info.loadNameNumberMessage(this);

    }
    @Override
    public void onBackPressed(){
        info.saveBookingState(this);
        super.onBackPressed();
    }

    public void onCancel(View view){
        finish();
    }
}
