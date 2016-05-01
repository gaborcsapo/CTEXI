package com.example.student.ctexiv1;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.student.ctexiv1.Utils.ServerCallback;

/**
 * Created by student on 5/1/16.
 */
public class RequestSingleton {
    private static RequestSingleton mInstance;
    private RequestQueue mRequestQueue;
    private StringRequest stringRequest;
    private static Context mCtx;

    private RequestSingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized RequestSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new RequestSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public void addToRequestQueue(String req, final ServerCallback callback) {

        String url ="https://stark-coast-40612.herokuapp.com/S|0.834|28.577|0097126352855|Chimamanda_Adichie";

        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("myTag", response);
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myTag", "error");
            }
        });
        getRequestQueue().add(stringRequest);
    }

    public StringRequest getStringRequest() {
        return stringRequest;
    }
}
