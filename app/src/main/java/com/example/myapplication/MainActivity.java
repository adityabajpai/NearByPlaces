package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.PetrolPumpStations;
import com.example.myapplication.Services.GPSTracker;
import com.example.myapplication.Services.LocationMonitoringService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button btn;
    boolean checked=true;
    GPSTracker gps;
    String curr_lat = "";
    String curr_lng = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gps = new GPSTracker(getApplicationContext());
        if(gps.canGetLocation()){
            Location location = gps.getLocation();
            Log.d("Mainlat",location.getLatitude()+"");
            Log.d("Mainlong",location.getLongitude()+"");
        }
//        getLocation();
        Log.e("started","started");
//        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new BroadcastReceiver()
//        {
//            @Override
//            public void onReceive(Context context, Intent intent)
//            {
//                String latitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LATITUDE);
//                String longitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LONGITUDE);
////                moving.setLatitude(Double.parseDouble(latitude));
////                moving.setLongitude(Double.parseDouble(longitude));
//                Log.d("Maincurr_lattitude",latitude);
//                Log.d("Maincurr_longitude",longitude);
//            }
//        }, new IntentFilter(LocationMonitoringService.ACTION_LOCATION_BROADCAST));
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("first",true);
        editor.apply();
//        CheckPermission();
        btn = findViewById(R.id.findPetrolPumps);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getLocation();
//                Bundle bundle = new Bundle();
//                bundle.putString("latti",latti);
//                bundle.putString("longit",longit);
                if(gps.canGetLocation()){
                    Location location = gps.getLocation();
                    Log.d("Mainlat",location.getLatitude()+"");
                    Log.d("Mainlong",location.getLongitude()+"");
                    curr_lat = location.getLatitude()+"";
                    curr_lng = location.getLongitude()+"";
                    editor.putString("curr_lat",curr_lat);
                    editor.putString("curr_lng",curr_lng);
                    editor.apply();
                }
                Intent intent = new Intent(MainActivity.this, PetrolPumpStations.class);
//                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }




}
