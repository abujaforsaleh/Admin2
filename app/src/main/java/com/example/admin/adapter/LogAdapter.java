package com.example.admin.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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
