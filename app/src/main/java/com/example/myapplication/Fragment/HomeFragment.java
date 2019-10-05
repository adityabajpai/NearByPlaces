package com.example.myapplication.Fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Activity.PetrolPumpStations;
import com.example.myapplication.MainActivity;
import com.example.myapplication.Modal.PetrolPumps;
import com.example.myapplication.R;
import com.example.myapplication.Services.LocationMonitoringService;
import com.example.myapplication.adapter.PetrolPumpsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements LocationListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<PetrolPumps> petrolPumpsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PetrolPumpsAdapter petrolPumpsAdapter;
    //    private static String URL_DATA = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=28.7529669,77.4947532&radius=10000&type=gas_station&sensor=true&key=AIzaSyBB8XJoy1ImPGUVUu_SOTwtCgXnosDC3rM";
    private static String URL_DATA = "";
    Location myLoc = new Location("");
    LocationManager locationManager;
    String latti = "", longit = "";
    String latitude = "", longitude = "";
    String finalLat="" , finallng="" ;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        Bundle bundle = this.getArguments();
//        String latti = bundle.getString("latti");
//        String longit = bundle.getString("longit");
        getLocation();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String curr_lat = sharedPreferences.getString("curr_lat","");
        String curr_lng = sharedPreferences.getString("curr_lng","");
        Log.d("current",curr_lat+"\n"+curr_lng);
        URL_DATA = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=+"+Double.parseDouble(curr_lat)+","+Double.parseDouble(curr_lng)+"&radius=10000&type=gas_station&sensor=true&key=AIzaSyBB8XJoy1ImPGUVUu_SOTwtCgXnosDC3rM";
//        LocalBroadcastManager.getInstance(getContext()).registerReceiver(new BroadcastReceiver()
//        {
//            @Override
//            public void onReceive(Context context, Intent intent)
//            {
//                latitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LATITUDE);
//                longitude = intent.getStringExtra(LocationMonitoringService.EXTRA_LONGITUDE);
////                moving.setLatitude(Double.parseDouble(latitude));
////                moving.setLongitude(Double.parseDouble(longitude));
//                Log.d("curr_lattitudeService",latitude);
//                Log.d("curr_longitudeService",longitude);
//            }
//        }, new IntentFilter(LocationMonitoringService.ACTION_LOCATION_BROADCAST));
//        Log.d("curr_lattitudeMethod",latti);
//        Log.d("curr_longitudeMethod",longit);
//        if(!latti.equals("")){
//            double x = Double.parseDouble(latti);
//            double y = Double.parseDouble(longit);
//            finalLat = x+"";
//            finallng = y+"";
////            editor.putString("curr_lattitude",finalLat);
////            editor.putString("curr_longitude",finallng);
//            Log.d("finalMessage1","message");
//            Log.d("finalCurrentLocation1",finalLat+"\n"+finallng);
//            URL_DATA = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=+"+x+","+y+"&radius=10000&type=gas_station&sensor=true&key=AIzaSyBB8XJoy1ImPGUVUu_SOTwtCgXnosDC3rM";
//        }else if(!latitude.equals("")){
//            double x = Double.parseDouble(latitude);
//            double y = Double.parseDouble(longitude);
//            finalLat = x+"";
//            finallng = y+"";
////            editor.putString("curr_lattitude",finalLat);
////            editor.putString("curr_longitude",finallng);
//            Log.d("finalMessage2","message");
//            Log.d("finalCurrentLocation2",finalLat+"\n"+finallng);
//            URL_DATA = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=+"+x+","+y+"&radius=10000&type=gas_station&sensor=true&key=AIzaSyBB8XJoy1ImPGUVUu_SOTwtCgXnosDC3rM";
//        }
//        Log.d("finalMessage","message");
//        Log.d("finalCurrentLocation",finalLat+"\n"+finallng);
//        editor.apply();
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        petrolPumpsList = new ArrayList<>();
        loadData();
        return view;
    }

    private void loadData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("results");

                    PetrolPumps petrolPumps;
                    Log.d("jsonArray",jsonArray+"");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        JSONObject jsonArray1 = jsonObject1.getJSONObject("geometry").getJSONObject("location");
                        Double lat = jsonArray1.getDouble("lat");
                        Double lng = jsonArray1.getDouble("lng");
                        String name = jsonObject1.getString("name");
                        String about = jsonObject1.getString("rating");
                        String image = jsonObject1.getString("icon");
                        String rating = jsonObject1.getString("rating");
                        Location src_loc = new Location("");
                        src_loc.setLatitude(lat);
                        src_loc.setLongitude(lng);
                        myLoc.setLatitude(28.7536166);
                        myLoc.setLongitude(77.4940364);
//                        String calc = ((myLoc.distanceTo(src_loc))/1000)+" km";
                        String calc = String.format("%.2f",(myLoc.distanceTo(src_loc))/1000);
                        calc = "Distance: "+calc+" km";
                        Log.d("name",name+" "+about+" "+image+" "+rating+" "+lat+" "+lng+" "+calc);
                        petrolPumps = new PetrolPumps(name,rating,calc,lat+"", lng+"");
                        petrolPumpsList.add(petrolPumps);
                        petrolPumpsAdapter = new PetrolPumpsAdapter(petrolPumpsList, getContext());
                        recyclerView.setAdapter(petrolPumpsAdapter);
                        petrolPumpsAdapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    void getLocation() {
        try {
            locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latti = location.getLatitude()+"";
        longit = location.getLongitude()+"";
        myLoc.setLatitude(Double.parseDouble(latti));
        myLoc.setLongitude(Double.parseDouble(longit));
        Log.d("Latitude: " , latti + "\n " + longit);
        Log.d("PetrolLatitude",latti+"\n"+longit);

        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
//                    addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
        }catch(Exception e)
        {

        }

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(getContext(), "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
