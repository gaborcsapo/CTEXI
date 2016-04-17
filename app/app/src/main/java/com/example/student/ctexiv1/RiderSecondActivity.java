package com.example.student.ctexiv1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

public class RiderSecondActivity extends UserClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_second);
        Log.d(RiderSecondActivity.class.getSimpleName(), "huhuhuhuhuhuhuhuhuhuhuhuh");
        if ((getIntent().getStringExtra("message") != null))
            ((TextView) findViewById(R.id.message)).setText(getIntent().getStringExtra("message"));
        else if ((getIntent().getBooleanExtra("restore", false) == true))
            ((TextView) findViewById(R.id.message)).setText(loadPreference("locationmessage"));
    }
    @Override
    public void onBackPressed(){
        savePreference("drivername", "Donald Trump");
        savePreference("drivernumber", "+546782345");
        savePreference("locationmessage", (String) ((TextView) findViewById(R.id.message)).getText());
        super.onBackPressed();
    }

}
