package com.example.ticketadmin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketadmin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class LocationAdapter extends FirebaseRecyclerAdapter<String, LocationAdapter.LocationViewHolder> {

    public LocationAdapter(
            @NonNull FirebaseRecyclerOptions<String> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull LocationViewHolder holder, int position, @NonNull String model) {
        holder.location.setText(model);
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.locationview_baselayout, parent, false);
        return new LocationAdapter.LocationViewHolder(view);

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
