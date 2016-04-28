package com.example.student.ctexiv1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by student on 4/17/16.
 */
public class UserClass extends AppCompatActivity{

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void savePreferences(){
        savePreference("name", String.valueOf(((EditText) findViewById(R.id.name)).getText()));
        savePreference("number", String.valueOf(((EditText) findViewById(R.id.number)).getText()));
    }

    protected void savePreference(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    protected String loadPreference(String key){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return sharedPreferences.getString(key, "null");
    }

    protected void loadSavedPreferences() {
        String name = loadPreference("name");
        String number = loadPreference("number");
        if (!(name.equals("null")) )
            ((EditText) findViewById(R.id.name)).setText(name);
        if (!(number.equals("null")) )
            ((EditText) findViewById(R.id.number)).setText(number);
    }

    public void onCancel(View view){
        finish();
    }
}
