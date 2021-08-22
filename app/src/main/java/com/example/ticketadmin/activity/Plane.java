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
import com.example.ticketadmin.adapter.BusAdapter;
import com.example.ticketadmin.adapter.PlaneAdapter;
import com.example.ticketadmin.bundle.BusBundle;
import com.example.ticketadmin.bundle.PlaneBundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Plane extends AppCompatActivity implements ItemClickListener, View.OnClickListener{

    RecyclerView planelist;
    private PlaneAdapter adapter;
    private List<PlaneBundle> planeList;
    Button addPlane;
    LazyLoader lazyLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byplane);
        
        planelist = findViewById(R.id.PlaneList_view_id);
        addPlane = findViewById(R.id.addPlane_Id);

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

        planelist.setHasFixedSize(true);
        planelist.setLayoutManager(new LinearLayoutManager(this));
        planelist.setItemAnimator(new DefaultItemAnimator());

        //configuring adapdter
        planeList = new ArrayList<>();
        adapter = new PlaneAdapter(this, planeList);
        planelist.setAdapter(adapter);
        adapter.setClickListener(this);
        addPlane.setOnClickListener(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference("Planes");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot planesnapshot : dataSnapshot.getChildren()) {
                    PlaneBundle plane = planesnapshot.getValue(PlaneBundle.class);

                    Log.d("plval", plane.getAgencyNameVariable()+" ");
                    Log.d("plval", plane.getPlaneNumberVariable()+" ");
                    Log.d("plval", plane.getSourceAirportNameVariable()+" ");
                    Log.d("plval", plane.getDestinationAirportNameVariable()+" ");
                    Log.d("plval", plane.getSourceCounterTicketPriceVariable()+" ");
                    Log.d("plval", plane.getSourceAirportTime()+" ");
                    Log.d("plval", plane.getDestinationAirportTime()+" ");
                    Log.d("plval", plane.getSeatPlan()+" ");



                    planeList.add(plane);
                }

                adapter.notifyDataSetChanged();
                lazyLoader.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("planes", String.valueOf(error));
            }
        });
        
        
    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.addPlane_Id){
            Intent intent = new Intent(Plane.this, AddPlane.class);
            startActivity(intent);
        }
    }
}