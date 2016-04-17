package com.example.student.ctexiv1;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;


public class PreferenceActivity extends AppCompatPreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        Preference myPref = findPreference( "CHANGE_USER" );
        myPref.setOnPreferenceClickListener( new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick( Preference pref ){

                setUser();

                return false;
            }
        } );
    }

    public void setUser(){
        SharedPreferences settings = getSharedPreferences("CHANGE_USER", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        Intent i = null;
        if (settings.getString("CHANGE_USER", "null").equals("rider")){
            editor.putString("CHANGE_USER", "driver");
            i = new Intent(this, DriverActivity.class);
        } else {
            editor.putString("CHANGE_USER", "rider");
            i = new Intent(this, RiderActivity.class);
        }
        editor.commit();
        editor.apply();
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }

}
