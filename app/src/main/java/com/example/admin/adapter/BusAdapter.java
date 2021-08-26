package com.example.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.admin.ItemClickListener;
import com.example.admin.R;
import com.example.admin.bundle.BusBundle;

import java.util.List;

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.ArtistViewHolder> {

    private Context mCtx;
    private List<BusBundle> busList;
    private ItemClickListener clickListener;
    private String from;

    public BusAdapter(Context mCtx, List<BusBundle> busList, String from) {
        this.mCtx = mCtx;
        this.busList = busList;
        this.from = from;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_buses, parent, false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        BusBundle bus = busList.get(position);
        holder.textViewBusName.setText(bus.getTravles_name());
        //holder.textViewBusNumber.setText("Bus Number : " + bus.getBus_number());
        //holder.textViewDate.setText("Journey Date : ");
        holder.textViewFrom.setText(bus.getCounter1_name());
        holder.textViewTotalSeat.setText(bus.getTotal_seat());
        holder.textViewTo.setText(bus.getDestination());
        holder.textViewCondition.setText(bus.getBus_type());
        holder.textViewCostView.setText(bus.getCounter_1_ticket_price());
        holder.textViewTime.setText(bus.getCounter1_arrival_time()+"-"+bus.getDestination_arrival_time());
    }

    @Override
    public int getItemCount() {
        return busList.size();
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
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}

