package com.example.navigator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    private static final String locSend = "location";
    //String address;
    String location;
    EditText locationInput;
    Button searchButton;
    Intent randomIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void randomMe (View v) {
        locationInput = (EditText) findViewById(R.id.locationInput);
        //String address = locationInput.getText().toString();
        String address = "129 Camp Verde Drive, Georgetown, Tx 78633";
        ///////////////////////////////////////////////////////////////////
        randomIntent = new Intent(this, SecondActivity.class);

        String key = "AIzaSyDW15574Ln4g3dQ1wI-lffYjWYgWci0qyo";
        String req = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + key;
        //final RequestQueue queue = Volley.newRequestQueue(this);
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = req;
        //final String lat;
        //final String lon;
        //final TextView textView = (TextView) findViewById(R.id.text);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject res = (JSONObject) response.getJSONArray("results").get(0);
                            JSONObject geom = (JSONObject) res.get("geometry");
                            JSONObject loc = (JSONObject) geom.get("location");
                            String lat = loc.get("lat").toString();
                            String lon = loc.get("lng").toString();
                            TextView textView = (TextView) findViewById(R.id.textView);
                            textView.setText(lat);
                            randomIntent.putExtra("lat", (String) lat);
                            randomIntent.putExtra("long", (String) lon);
                            String darkKey = "4cb69f239372b85805964dd89e804575";
                            String darkurl = "https://api.darksky.net/forecast/" + darkKey + "/" + lat + "," + lon;
                            JsonObjectRequest darkObjectRequest = new JsonObjectRequest
                                    (Request.Method.GET, darkurl, null, new Response.Listener<JSONObject>() {
                                        @Override
                                        public void onResponse(JSONObject darkData) {
                                            try {
                                                //textView.setText(darkData.toString());
                                                JSONObject current = (JSONObject) darkData.get("currently");
                                                String humid = current.get("humidity").toString();
                                                String temp = current.get("temperature").toString();
                                                String prec = current.get("precipProbability").toString();
                                                String wind = current.get("windSpeed").toString();
                                                //textView.setText(humid + " " + temp + " " + prec + " " + wind);
                                                randomIntent.putExtra("humid", humid);
                                                randomIntent.putExtra("temp", temp);
                                                randomIntent.putExtra("prec", prec);
                                                randomIntent.putExtra("wind", wind);
                                                startActivity(randomIntent);
                                            } catch (Exception e) {
                                            }
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            // TODO: Handle error
                                        }
                                    });
                            queue.add(darkObjectRequest);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }});
        queue.add(jsonObjectRequest);
    }
}
