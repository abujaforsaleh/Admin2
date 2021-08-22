package com.example.ticketadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.example.ticketadmin.ItemClickListener;
import com.example.ticketadmin.R;
import com.example.ticketadmin.adapter.TrainAdapter;
import com.example.ticketadmin.bundle.TrainBundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Train extends AppCompatActivity implements ItemClickListener, View.OnClickListener{

    RecyclerView trainlist;
    private TrainAdapter adapter;
    private List<TrainBundle> trainList;
    Button addTrain;
    LazyLoader lazyLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bytrain);


        trainlist = findViewById(R.id.trainlist_view_id);
        addTrain = findViewById(R.id.addTrain_Id);

        trainlist.setHasFixedSize(true);
        trainlist.setLayoutManager(new LinearLayoutManager(this));
        trainlist.setItemAnimator(new DefaultItemAnimator());

        //setting progressbar
        lazyLoader = findViewById(R.id.lazyLoader_id);


        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());

        lazyLoader.addView(loader);


        //configuring adapdter
        trainList = new ArrayList<>();
        adapter = new TrainAdapter(this, trainList, "");
        trainlist.setAdapter(adapter);
        adapter.setClickListener(this);
        addTrain.setOnClickListener(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference("Trains");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot trainsnapshot : dataSnapshot.getChildren()) {
                    TrainBundle train = trainsnapshot.getValue(TrainBundle.class);

                    trainList.add(train);
                }

                adapter.notifyDataSetChanged();
                lazyLoader.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("busses", String.valueOf(error));
            }
        });
    }

    @Override
    public void onClick(View view, int position) {

        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addTrain_Id){
            Intent intent = new Intent(Train.this, AddTrain.class);
            startActivity(intent);
        }
    }
    
}