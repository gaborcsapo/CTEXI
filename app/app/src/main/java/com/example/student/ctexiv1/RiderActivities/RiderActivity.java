package com.example.student.ctexiv1.RiderActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.FirstActivity;

public class RiderActivity extends FirstActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);
        loadOwnInfo();
    }

    protected void bookTaxi(View view){
        saveOwnInfo();
        Intent i = new Intent(this, RiderWaitActivity.class);
        info.setMessage(String.valueOf(((EditText) findViewById(R.id.Message)).getText()));
        info.setName(String.valueOf(((EditText) findViewById(R.id.Name)).getText()));
        info.setNumber(String.valueOf(((EditText) findViewById(R.id.Number)).getText()));
        startActivity(i);
    }

    protected void previous (View view){
        Intent i = new Intent(this, RiderSecondActivity.class);
        i.putExtra("restore", true);
        startActivity(i);
    }

}
