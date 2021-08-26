package com.example.admin.activity;

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

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;
import com.example.admin.ItemClickListener;
import com.example.admin.R;
import com.example.admin.adapter.BusAdapter;
import com.example.admin.bundle.BusBundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Bus extends AppCompatActivity implements ItemClickListener, View.OnClickListener {
    RecyclerView buslist;
    private BusAdapter adapter;
    private List<BusBundle> busList;
    Button addBuss;
    String statisticsList="";
    LazyLoader lazyLoader;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bybus);

        buslist = findViewById(R.id.busList_view_id);
        addBuss = findViewById(R.id.addBus_Id);

        buslist.setHasFixedSize(true);
        buslist.setLayoutManager(new LinearLayoutManager(this));
        buslist.setItemAnimator(new DefaultItemAnimator());

        //progressbar
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
        busList = new ArrayList<>();
        adapter = new BusAdapter(this, busList, "");
        buslist.setAdapter(adapter);
        adapter.setClickListener(this);
        addBuss.setOnClickListener(this);


        DatabaseReference ref = database.getReference("Busses");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot bussnapshot : dataSnapshot.getChildren()) {
                    BusBundle bus = bussnapshot.getValue(BusBundle.class);
                    busList.add(bus);
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
        DatabaseReference statistic_ref = database.getReference("Statistics").child(busList.get(position).getBus_number());

        Log.d("staval", String.valueOf(statistic_ref));

        lazyLoader.setVisibility(View.VISIBLE);

        statistic_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot staSnapshot:snapshot.getChildren()){
                    //Log.d("staval", staSnapshot.getKey());

                    String temp = staSnapshot.getKey()+":"+staSnapshot.getValue(String.class);
                    statisticsList+=temp+"\n";
                }
                Log.d("1staval", statisticsList);
                Intent intent = new Intent(Bus.this, BusStatistics.class);
                intent.putExtra("statis", statisticsList);
                intent.putExtra("number", busList.get(position).getBus_number());
                lazyLoader.setVisibility(View.GONE);
                statisticsList = "";
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("staval", "cancle");
            }
        });


        //Toast.makeText(this, busList.get(position).getBus_number(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addBus_Id){

            Intent intent = new Intent(Bus.this, AddBuss.class);
            startActivity(intent);
        }
    }
}