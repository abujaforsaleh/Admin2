package com.example.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.admin.ItemClickListener;
import com.example.admin.R;
import com.example.admin.bundle.PlaneBundle;

import java.util.List;

public class PlaneAdapter extends RecyclerView.Adapter<PlaneAdapter.ArtistViewHolder> {

    private Context mCtx;
    private List<PlaneBundle> planeList;
    private ItemClickListener clickListener;


    public PlaneAdapter(Context mCtx, List<PlaneBundle> planeList) {
        this.mCtx = mCtx;
        this.planeList = planeList;

    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_buses, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        PlaneBundle plane = planeList.get(position);
        holder.textViewBusName.setText(plane.getAgencyNameVariable());
        //holder.textViewBusNumber.setText("Bus Number : " + plane.getBus_number());
        //holder.textViewDate.setText("Journey Date : ");
        holder.textViewTotalSeat.setVisibility(View.GONE);
        holder.textViewCondition.setVisibility(View.GONE);
        holder.textViewFrom.setText(plane.getSourceAirportNameVariable());
        holder.textViewTo.setText(plane.getDestinationAirportNameVariable());
        holder.textViewCostView.setText(plane.getSourceCounterTicketPriceVariable());
        holder.textViewTime.setText(plane.getSourceAirportTime() + "-" + plane.getDestinationAirportTime());
    }

    @Override
    public int getItemCount() {
        return planeList.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewTotalSeat, textViewBusName, textViewBusNumber, textViewTime, textViewFrom,textViewTo,textViewCondition, textViewCostView;
        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTotalSeat = itemView.findViewById(R.id.total_seat_view);
            textViewBusName = itemView.findViewById(R.id.text_view_busName);
            textViewBusNumber = itemView.findViewById(R.id.text_view_busNumber);
            textViewTime = itemView.findViewById(R.id.text_view_time);
            textViewFrom = itemView.findViewById(R.id.text_view_from);
            textViewTo = itemView.findViewById(R.id.text_view_to);
            textViewCondition = itemView.findViewById(R.id.text_view_condition);
            textViewCostView = itemView.findViewById(R.id.cost_view);


            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            Toast.makeText(mCtx, "click", Toast.LENGTH_SHORT).show();
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}

