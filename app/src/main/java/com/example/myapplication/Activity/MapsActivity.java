package com.example.myapplication.Activity;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double src_lattitude;
    double src_longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        src_lattitude = Double.parseDouble(bundle.getString("src_lat"));
        src_longitude = Double.parseDouble(bundle.getString("src_lng"));
        SharedPreferences sharedPreferences =  getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String curr_lat = sharedPreferences.getString("curr_lattitude","");
        String curr_lng = sharedPreferences.getString("curr_longitude","");
        Log.d("src",src_lattitude+"\n"+src_longitude);
        Log.d("current",curr_lat+"\n"+curr_lng);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng source = new LatLng(src_lattitude, src_longitude);
        mMap.addMarker(new MarkerOptions().position(source).title("Source"));
        LatLng current = new LatLng(28.7529669, 77.4947532);
        mMap.addMarker(new MarkerOptions().position(current).title("Source"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(source));
    }
}
