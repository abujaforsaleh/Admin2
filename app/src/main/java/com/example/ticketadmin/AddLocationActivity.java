package com.example.ticketadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.ticketadmin.adapter.LocationAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddLocationActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout locationInput;
    RecyclerView currentLocations;
    LocationAdapter locationAdapter;
    ImageButton addButton;
    DataSnapshot locationSnapshot;
    DatabaseReference myRef;
    List<String> locationList= new ArrayList<>();
    TextInputEditText input_fuild;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Locations");

        locationInput = findViewById(R.id.locationEditTextId);
        currentLocations = findViewById(R.id.availableLocationListViewId);
        addButton = findViewById(R.id.addButtonId);
        input_fuild =findViewById(R.id.text_fild);

        currentLocations.setLayoutManager( new LinearLayoutManager(this));

        FirebaseRecyclerOptions<String> locations = new FirebaseRecyclerOptions.Builder<String>()
                .setQuery(myRef, String.class)
                .build();
        locationAdapter = new LocationAdapter(locations);
        currentLocations.setAdapter(locationAdapter);

        addButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.addButtonId){
            String locationIn;

            if(!TextUtils.isEmpty(locationInput.getEditText().getText().toString())){

                locationIn=locationInput.getEditText().getText().toString();

                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        locationSnapshot=snapshot;

                        if(checkLocationValidity(locationIn)){
                            myRef.child(locationIn).setValue(locationIn);
                            input_fuild.setText("");

                        }else{
                            locationInput.setError("This location already Exists");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        }

    }

    public boolean checkLocationValidity(String locationName){
        Log.d("TAG", "checkLocationValidity: "+locationSnapshot.getChildrenCount());

        Log.d("TAG", "checkLocationValidity: "+locationSnapshot.hasChild(locationName));

        return !locationSnapshot.hasChild(locationName);
    }



}