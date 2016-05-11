package com.example.student.ctexiv1.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import com.example.student.ctexiv1.PreferenceActivity;
import com.example.student.ctexiv1.R;


/**
 * Created by student on 4/17/16.
 */
public class FirstActivity extends AppCompatActivity{
    protected InfoSingleton info = InfoSingleton.getInstance();

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

    protected void saveOwnInfo() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("OwnName", String.valueOf(((EditText) findViewById(R.id.Name)).getText()));
        editor.putString("OwnNumber", String.valueOf(((EditText) findViewById(R.id.Number)).getText()));
        editor.commit();
    }

    protected void loadOwnInfo() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String name = sharedPreferences.getString("OwnName", "null");
        String number = sharedPreferences.getString("OwnNumber", "null");
        if (!(name.equals("null")) )
            ((EditText) findViewById(R.id.Name)).setText(name);
        if (!(number.equals("null")) )
            ((EditText) findViewById(R.id.Number)).setText(number);
   }

}