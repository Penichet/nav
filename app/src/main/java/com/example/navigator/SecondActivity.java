package com.example.navigator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private static final String locSend = "location";

    RequestQueue queue;
    String location = "fromSecond";
    //String latitude = null;
    //String longitude = "also empty";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //queue = Volley.newRequestQueue(this);

    }
    public String getMyData() {
        Intent intent = getIntent();
        String lat = intent.getStringExtra("lat").toString();
        String lon = intent.getStringExtra("long").toString();
        String humid = intent.getStringExtra("humid").toString();
        String temp = intent.getStringExtra("temp").toString();
        String prec = intent.getStringExtra("prec").toString();
        String wind = intent.getStringExtra("wind").toString();
        return lat +":"+ lon+":"+humid+":"+temp+":"+prec+":"+wind;
    }
    public String getLatLon(){
        Intent intent = getIntent();
        String lat = intent.getStringExtra("lat").toString();
        String lon = intent.getStringExtra("long").toString();
        return lat+":"+lon;
    }

//    public void addQueue(JsonObjectRequest req){
//        queue.add(req);
//
//    }

}
