package com.example.ticketadmin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ticketadmin.R;
import com.example.ticketadmin.adapter.LocationAdapter;
import com.example.ticketadmin.adapter.LogAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DataLogActivity extends AppCompatActivity {

    RecyclerView dataLogView;
    LogAdapter locationAdapter;
    DatabaseReference myRef, myRef2;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_log);

        dataLogView = findViewById(R.id.data_lod_view_id);
        back = findViewById(R.id.logback);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Log");
        //myRef = database.getReference("log_info");
        dataLogView.setLayoutManager( new LinearLayoutManager(this));

        FirebaseRecyclerOptions<String> locations = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(myRef, String.class)
                .build();
        /*List<String> idlist = new ArrayList<>();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap:snapshot.getChildren()){

                    idlist.add(snap.getKey());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        locationAdapter = new LogAdapter(locations);
        dataLogView.setAdapter(locationAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        locationAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        locationAdapter.stopListening();
    }
}