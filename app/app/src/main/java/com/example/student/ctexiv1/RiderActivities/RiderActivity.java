package com.example.student.ctexiv1.RiderActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.student.ctexiv1.R;
import com.example.student.ctexiv1.Utils.UserTemplate;

public class RiderActivity extends UserTemplate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider);
        loadSavedPreferences();
    }

    protected void bookTaxi(View view){
        savePreferences();
        Intent i = new Intent(this, RiderWaitActivity.class);
        i.putExtra("PassedMessage", String.valueOf(((EditText) findViewById(R.id.Message)).getText()));
        i.putExtra("PassedName", String.valueOf(((EditText) findViewById(R.id.Name)).getText()));
        i.putExtra("PassedNumber", String.valueOf(((EditText) findViewById(R.id.Number)).getText()));
        startActivity(i);
    }

    protected void previous (View view){
        Intent i = new Intent(this, RiderSecondActivity.class);
        i.putExtra("restore", true);
        startActivity(i);
    }

}
