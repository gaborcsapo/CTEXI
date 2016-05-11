package com.example.student.ctexiv1;

import android.test.ActivityInstrumentationTestCase2;

import com.example.student.ctexiv1.DriverActivities.DriverSecondActivity;
import com.example.student.ctexiv1.Utils.InfoSingleton;

/**
 * Created by student on 5/11/16.
 */
public class InfoSingletonTest extends ActivityInstrumentationTestCase2<DriverSecondActivity>{
    InfoSingleton info;
    private DriverSecondActivity activity;

    public InfoSingletonTest(){
        super(DriverSecondActivity.class);
        info = InfoSingleton.getInstance();

    }

    public void setUp() throws Exception{
        super.setUp();
        activity = getActivity();
    }

    public void testLoadNameNumberMessage(DriverSecondActivity){

    }
}
