package com.example.student.ctexiv1.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.student.ctexiv1.R;

import java.util.jar.Attributes;

/**
 * Created by student on 5/10/16.
 */
public class InfoSingleton {
    private static InfoSingleton instance = null;

    private String Name;
    private String Number;
    private String Message;
    private Double Lat;
    private Double Long;

    private InfoSingleton(){}

    public static InfoSingleton getInstance(){
        if (instance == null)
            instance = new InfoSingleton();
        return instance;
    }

    //Setter methods
    public void setName(String name){
        this.Name = name;
    }
    public void setNumber(String number){
        this.Number = number;
    }
    public void setMessage(String message){
        this.Message = message;
    }
    public void setLat(Double lat){
        this.Lat = lat;
    }
    public void setLong(Double lon){
        this.Long = lon;
    }


    //Getter methods
    public String getName(){
        return Name;
    }
    public String getNumber(){
        return Number;
    }
    public String getMessage(){
        return Message;
    }
    public Double getLat(){
        return Lat;
    }
    public Double getLong(){
        return Long;
    }
    public String getServerNumber(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString("PREF_SERVER", "null");
    }

    //Load Everything on screen
    public void loadNameNumberMessage(Activity c){
        ((TextView) c.findViewById(R.id.Message)).setText(Message);
        ((TextView) c.findViewById(R.id.Name)).setText(Name);
        ((TextView) c.findViewById(R.id.Number)).setText(Number);
    }

    //Loading up previous booking
    public void loadPrevInfo(Activity c){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        Message = sharedPreferences.getString("Message", "");
        Name = sharedPreferences.getString("Name", "No Previous Booking");
        Number = sharedPreferences.getString("Number", "");
        ((TextView) c.findViewById(R.id.Message)).setText(Message);
        ((TextView) c.findViewById(R.id.Name)).setText(Name);
        ((TextView) c.findViewById(R.id.Number)).setText(Number);
    }

    //Save booking info
    public void saveBookingState(Activity c){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(c);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Name", Name);
        editor.putString("Number", Number);
        editor.putString("Message", (String) ((TextView) c.findViewById(R.id.Message)).getText());
        editor.commit();;
    }
}
