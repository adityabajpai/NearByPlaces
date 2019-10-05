package com.example.myapplication.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.Helper.DatabaseHelper;
import com.example.myapplication.Modal.PetrolPumps;
import com.example.myapplication.R;
import com.example.myapplication.adapter.FavouritePumpsAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link FavouriteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavouriteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    DatabaseHelper databaseHelper;
    private List<PetrolPumps> petrolPumpsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FavouritePumpsAdapter FavouritePumpsAdapter;
    private PetrolPumps petrolPumps;

//    private OnFragmentInteractionListener mListener;

    public FavouriteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavouriteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
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
        View view =  inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        petrolPumpsList = new ArrayList<>();
        databaseHelper = new DatabaseHelper(getContext());
        Cursor res = databaseHelper.getAllData();
        Log.d("res",res+"");
        if(res.getCount()==0){
            Log.d("Count","0");
            Snackbar.make(container,"No Favourites Yet",Snackbar.LENGTH_LONG).show();
        }else{
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                buffer.append("name "+res.getString(0));
                buffer.append("rating "+res.getString(1));
                buffer.append("lat "+res.getString(2));
                buffer.append("lng "+res.getString(3));
                Log.d("database",buffer+"\n");
                petrolPumps = new PetrolPumps(res.getString(0),res.getString(1),"Distance: 2km",res.getString(2),res.getString(3));
                petrolPumpsList.add(petrolPumps);
                FavouritePumpsAdapter = new FavouritePumpsAdapter(petrolPumpsList, getContext());
                recyclerView.setAdapter(FavouritePumpsAdapter);
                FavouritePumpsAdapter.notifyDataSetChanged();
//                res.moveToNext();
            }
        }

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
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
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
