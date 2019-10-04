package com.example.myapplication.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.myapplication.adapter.PetrolPumpsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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
