package com.example.navigator;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {


    //private static EditText area;
    View Globalview;
    SecondActivity activity;
    public WeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        Globalview = inflater.inflate(R.layout.fragment_weather, container, false);
        activity = (SecondActivity) getActivity();
        String latlon = activity.getMyData();
        String[] tempparse = latlon.split(":");
        String lat = tempparse[0];
        String lon = tempparse[1];
        String humid = tempparse[2];
        String temp = tempparse[3];
        String prec = tempparse[4];
        String wind = tempparse[5];
        TextView TextH = Globalview.findViewById(R.id.humid);
        TextView TextT = Globalview.findViewById(R.id.temp);
        TextView TextP = Globalview.findViewById(R.id.prec);
        TextView TextW = Globalview.findViewById(R.id.wind);
        TextH.setText("Humidity: " + humid);
        TextT.setText("Temperature: " + temp);
        TextP.setText("Precipitation: " + prec);
        TextW.setText("Wind: " +wind);
        //activity.addQueue(getLocation(location));
        //String location = getArguments().getString("location");


        //area.setText("Humidity: " + humid + " Temp: " + temp + );

        return Globalview;
    }


}
