package com.example.myapplication.Activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Fragment.FavouriteFragment;
import com.example.myapplication.Fragment.HomeFragment;
import com.example.myapplication.Fragment.ProfileFragment;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Modal.PetrolPumps;
import com.example.myapplication.R;
import com.example.myapplication.Services.LocationMonitoringService;
import com.example.myapplication.adapter.PetrolPumpsAdapter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PetrolPumpStations extends AppCompatActivity  {
//    private TextView mTextMessage;
//    private List<PetrolPumps> petrolPumpsList = new ArrayList<>();
//    private RecyclerView recyclerView;
//    private PetrolPumpsAdapter petrolPumpsAdapter;
//    private static final String URL_DATA = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.7529669,77.4947532&radius=10000&type=gas_station&sensor=true&key=AIzaSyBB8XJoy1ImPGUVUu_SOTwtCgXnosDC3rM";
//    Location myLoc = new Location("");
    MaterialSearchView searchView;
    PlaceAutocompleteFragment placeAutoComplete;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_favourites:
                    fragment = new FavouriteFragment();
                    loadFragment(fragment);
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_userProfile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petrol_pump_stations);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar();
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
//        searchView = findViewById(R.id.search_View);
        placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.d("Maps", "Place selected: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                Log.d("Maps", "An error occurred: " + status);
            }
        });
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                String latitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LATITUDE);
                String longitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LONGITUDE);
//                moving.setLatitude(Double.parseDouble(latitude));
//                moving.setLongitude(Double.parseDouble(longitude));
                Log.d("Maincurr_lattitude",latitude);
                Log.d("Maincurr_longitude",longitude);
            }
        }, new IntentFilter(LocationMonitoringService.ACTION_LOCATION_BROADCAST));
//        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new HomeFragment());

//        PetrolPumps petrolPumps = new PetrolPumps("IGL Petrol Pumps","2");
//        petrolPumpsList.add(petrolPumps);
//        petrolPumps = new PetrolPumps("IGL Petrol Pumps","2");
//        petrolPumpsList.add(petrolPumps);
//        petrolPumps = new PetrolPumps("IGL Petrol Pumps","2");
//        petrolPumpsList.add(petrolPumps);
//        petrolPumpsAdapter = new PetrolPumpsAdapter(petrolPumpsList, getApplicationContext());
//        recyclerView.setAdapter(petrolPumpsAdapter);
//        petrolPumpsAdapter.notifyDataSetChanged();

    }
}
