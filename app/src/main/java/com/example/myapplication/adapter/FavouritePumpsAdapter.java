package com.example.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
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

import com.example.myapplication.Helper.DatabaseHelper;
import com.example.myapplication.Modal.PetrolPumps;
import com.example.myapplication.R;

import java.util.List;

public class FavouritePumpsAdapter extends RecyclerView.Adapter<FavouritePumpsAdapter.MyViewHolder> {

    private List<PetrolPumps> petrolPumpsList;
    private Context context;
    DatabaseHelper databaseHelper;

    public FavouritePumpsAdapter(List<PetrolPumps> petrolPumpsList) {
        this.petrolPumpsList = petrolPumpsList;
    }

    public FavouritePumpsAdapter(List<PetrolPumps> petrolPumpsList, Context context) {
        this.petrolPumpsList = petrolPumpsList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavouritePumpsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favouritepumpscard, parent, false);
        FavouritePumpsAdapter.MyViewHolder viewHolder = new FavouritePumpsAdapter.MyViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FavouritePumpsAdapter.MyViewHolder myViewHolder, int i) {
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
//                boolean isInserted = databaseHelper.insertData(petrolPumps.getName(),petrolPumps.getRating(),Double.parseDouble(petrolPumps.getSrc_lat()),Double.parseDouble(petrolPumps.getSrc_lng()));
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
                Log.d("src_lat",petrolPumps.getSrc_lat());
                Integer deleteRows = databaseHelper.deleteData(petrolPumps.getSrc_lat()+"");
                if(deleteRows!=0)
                    Toast.makeText(context,"Rows deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(context,"Rows not deleted",Toast.LENGTH_LONG).show();
            }
        });
        myViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, MapsActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("src_lat",petrolPumps.getSrc_lat());
//                bundle.putString("src_lng",petrolPumps.getSrc_lng());
//                intent.putExtras(bundle);
//                context.startActivity(intent);
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
            relativeLayout = itemView.findViewById(R.id.relativeLayoutFavourite);
        }
    }
}
