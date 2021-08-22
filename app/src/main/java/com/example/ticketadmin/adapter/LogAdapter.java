package com.example.ticketadmin.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketadmin.R;
import com.example.ticketadmin.bundle.BookingBundle;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LogAdapter extends FirebaseRecyclerAdapter<String, LogAdapter.LocationViewHolder> {

    public LogAdapter(
            @NonNull FirebaseRecyclerOptions<String> options)
    {
        super(options);

    }

    @Override
    protected void onBindViewHolder(@NonNull LocationViewHolder holder, int position, @NonNull String model) {
        holder.location.setText(model);
        if(position%2==0){
            holder.location.setBackgroundColor(Color.parseColor("#4F4B8E"));
            holder.location.setTextColor(Color.parseColor("#ffffff"));
        }else{
            holder.location.setBackgroundColor(Color.parseColor("#8981F1"));
            holder.location.setTextColor(Color.parseColor("#ffffff"));
        }

    }



    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.locationview_baselayout, parent, false);
        return new LogAdapter.LocationViewHolder(view);

    }


    class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView location;
        public LocationViewHolder(@NonNull View itemView)
        {
            super(itemView);

            location = itemView.findViewById(R.id.location_item_id);

        }
    }
}
