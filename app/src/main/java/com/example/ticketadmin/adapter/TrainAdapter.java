package com.example.ticketadmin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ticketadmin.ItemClickListener;
import com.example.ticketadmin.R;
import com.example.ticketadmin.bundle.TrainBundle;

import java.util.List;

public class TrainAdapter extends RecyclerView.Adapter<TrainAdapter.ArtistViewHolder> {

    private Context mCtx;
    private List<TrainBundle> trainList;
    private ItemClickListener clickListener;
    private String from;

    public TrainAdapter(Context mCtx, List<TrainBundle> trainList, String from) {
        this.mCtx = mCtx;
        this.trainList = trainList;
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
        TrainBundle train = trainList.get(position);
        holder.textViewTrainName.setText(train.getTravles_name());
        //holder.textViewTrainNumber.setText("Train Number : " + train.getTrain_number());
        //holder.textViewDate.setText("Journey Date : ");
        holder.textViewFrom.setText(train.getCounter1_name());
        holder.textViewTotalSeat.setVisibility(View.GONE);
        //holder.textViewTotalSeat.setText(train.getTotal_seat());
        holder.textViewTo.setText(train.getDestination());
        holder.textViewCondition.setText(train.getTrain_type());
        holder.textViewCostView.setText(train.getCounter_1_ticket_price());
        holder.textViewTime.setText(train.getCounter1_arrival_time()+"-"+train.getDestination_arrival_time());
    }

    @Override
    public int getItemCount() {
        return trainList.size();
    }
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewTotalSeat, textViewTrainName, textViewTrainNumber, textViewTime, textViewFrom,textViewTo,textViewCondition, textViewCostView;
        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTotalSeat = itemView.findViewById(R.id.total_seat_view);
            textViewTrainName = itemView.findViewById(R.id.text_view_busName);
            textViewTrainNumber = itemView.findViewById(R.id.text_view_busNumber);
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

