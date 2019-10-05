package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Activity.MapsActivity;
import com.example.myapplication.Helper.DatabaseHelper;
import com.example.myapplication.Modal.PetrolPumps;
import com.example.myapplication.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class PetrolPumpsAdapter extends RecyclerView.Adapter<PetrolPumpsAdapter.MyViewHolder> {

    private List<PetrolPumps> petrolPumpsList;
    private Context context;
    DatabaseHelper databaseHelper;

    public PetrolPumpsAdapter(List<PetrolPumps> petrolPumpsList) {
        this.petrolPumpsList = petrolPumpsList;
    }

    public PetrolPumpsAdapter(List<PetrolPumps> petrolPumpsList, Context context) {
        this.petrolPumpsList = petrolPumpsList;
        this.context = context;
    }

    @NonNull
    @Override
    public PetrolPumpsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.petrolpumpscard, parent, false);
        PetrolPumpsAdapter.MyViewHolder viewHolder = new PetrolPumpsAdapter.MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PetrolPumpsAdapter.MyViewHolder myViewHolder, int i) {
        databaseHelper = new DatabaseHelper(context);
        ArrayList<String> al = new ArrayList<>();
        Cursor res = databaseHelper.getAllData();
        Log.d("res",res+"");
        if(res.getCount()==0){
            Log.d("Count","0");
//            Snackbar.make(context,"No Favourites Yet",Snackbar.LENGTH_LONG).show();
//            Toast.makeText(context,"No Favourites Yet",Toast.LENGTH_LONG).show();
        }else{
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
//                buffer.append("name "+res.getString(0));
//                buffer.append("rating "+res.getString(1));
//                buffer.append("lat "+res.getString(2));
//                buffer.append("lng "+res.getString(3));
                al.add(res.getString(0));
//                Log.d("database",buffer+"\n");
//                petrolPumps = new PetrolPumps(res.getString(0),res.getString(1),"Distance: 2km",res.getString(2),res.getString(3));
//                petrolPumpsList.add(petrolPumps);
//                FavouritePumpsAdapter = new FavouritePumpsAdapter(petrolPumpsList, getContext());
//                recyclerView.setAdapter(FavouritePumpsAdapter);
//                FavouritePumpsAdapter.notifyDataSetChanged();
//                res.moveToNext();
            }
        }
        Log.d("petrolPumpArrayList",al+"");
        final PetrolPumps petrolPumps = petrolPumpsList.get(i);
        myViewHolder.ratingBar.setText((petrolPumps.getRating())+"");
        myViewHolder.textView_distance.setText(petrolPumps.getDistance());
        myViewHolder.textView_name.setText(petrolPumps.getName());
        databaseHelper = new DatabaseHelper(context);
        Log.d("petrolPumpName",petrolPumps.getName());
        Log.d("petrolPumpCheck",al.contains(petrolPumps.getName())+"");
        if(al.contains(petrolPumps.getName())){
            myViewHolder.grey_image.setVisibility(View.GONE);
            myViewHolder.red_image.setVisibility(View.VISIBLE);
        }
        myViewHolder.grey_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.grey_image.setVisibility(View.GONE);
                myViewHolder.red_image.setVisibility(View.VISIBLE);
//                databaseHelper = new DatabaseHelper(context);
                boolean isInserted = databaseHelper.insertData(petrolPumps.getName(),petrolPumps.getRating(),(petrolPumps.getSrc_lat()),(petrolPumps.getSrc_lng()));
//                if(isInserted)
//                    Toast.makeText(context,"Data is Inserted",Toast.LENGTH_LONG).show();
//                else
//                    Toast.makeText(context,"Data is Inserted",Toast.LENGTH_LONG).show();
//                Set<PetrolPumps> al = new HashSet<>();
//                PetrolPumps petrolPumps1 = new PetrolPumps(petrolPumps.getName(),petrolPumps.getRating(),petrolPumps.getDistance(),"red");
//                al.add(petrolPumps1);
            }
        });
        myViewHolder.red_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.red_image.setVisibility(View.GONE);
                myViewHolder.grey_image.setVisibility(View.VISIBLE);
            }
        });
        myViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("src_lat",petrolPumps.getSrc_lat());
                bundle.putString("src_lng",petrolPumps.getSrc_lng());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return petrolPumpsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_name, textView_distance;
        TextView ratingBar;
        ImageView red_image, grey_image;
        RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.petrolPumps_name);
            textView_distance = itemView.findViewById(R.id.petrolPumps_distance);
            ratingBar = itemView.findViewById(R.id.rating);
            red_image = itemView.findViewById(R.id.red_favourite);
            grey_image = itemView.findViewById(R.id.grey_favourite);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutAll);
        }
    }
}
