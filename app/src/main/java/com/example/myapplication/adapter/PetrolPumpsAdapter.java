package com.example.myapplication.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Helper.DatabaseHelper;
import com.example.myapplication.Modal.PetrolPumps;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        final PetrolPumps petrolPumps = petrolPumpsList.get(i);
        myViewHolder.ratingBar.setText((petrolPumps.getRating())+"");
        myViewHolder.textView_distance.setText(petrolPumps.getDistance());
        myViewHolder.textView_name.setText(petrolPumps.getName());
        databaseHelper = new DatabaseHelper(context);
        myViewHolder.grey_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewHolder.grey_image.setVisibility(View.GONE);
                myViewHolder.red_image.setVisibility(View.VISIBLE);
//                databaseHelper = new DatabaseHelper(context);
                boolean isInserted = databaseHelper.insertData(petrolPumps.getName(),petrolPumps.getRating(),(petrolPumps.getSrc_lat()),(petrolPumps.getSrc_lng()));
                if(isInserted)
                    Toast.makeText(context,"Data is Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context,"Data is Inserted",Toast.LENGTH_LONG).show();
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
    }

    @Override
    public int getItemCount() {
        return petrolPumpsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_name, textView_distance;
        TextView ratingBar;
        ImageView red_image, grey_image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_name = itemView.findViewById(R.id.petrolPumps_name);
            textView_distance = itemView.findViewById(R.id.petrolPumps_distance);
            ratingBar = itemView.findViewById(R.id.rating);
            red_image = itemView.findViewById(R.id.red_favourite);
            grey_image = itemView.findViewById(R.id.grey_favourite);
        }
    }
}