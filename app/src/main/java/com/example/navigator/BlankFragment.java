package com.example.navigator;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    MapView mMapView;
    View mView;
    String lat;
    String lon;
    SecondActivity activity;
    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_blank, container, false);
        activity = (SecondActivity) getActivity();
        String latlon = activity.getLatLon();
        String[] tempparse = latlon.split(":");
        lat = tempparse[0];
        lon = tempparse[1];
        return mView;

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView = (MapView) mView.findViewById(R.id.map);
        if(mMapView != null){
            mMapView.onCreate(null);
            mMapView.onResume();
            mMapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Float latnum = Float.parseFloat(lat);
        Float lonnum = Float.parseFloat(lon);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(latnum, lonnum);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in New Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera( CameraUpdateFactory.zoomTo(12.0f));
    }
}
