package com.example.admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.admin.R;
import com.example.admin.bundle.PlaneBundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddPlane extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{


    DatabaseReference adbusref, location_ref;

    ImageView back;
    
    EditText total_row, columnSample, agency_name, plane_number, ticket_price;
    TextView sourceAirportTimer, destinationAirportTimer;
    Button submit;
    private TimePickerDialog dialog1, dialog4;
    Spinner sourceAirport, destinationAirport;

    List<String> locations= new ArrayList<>();
    int currentHour, currentMunit;
    String seats="", sourceAirportTime="", destinationAirportTime="", date, spinner_default_item="Select an Option";
    String agencyNameVariable, planeNumberVariable, SourceCounterTicketPriceVariable, tot_row, columnSampleVar;
    String sourceAirportNameVariable="", DestinationAirportNameVariable="";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plane);

        back = findViewById(R.id.addplaneback);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adbusref = database.getReference("Planes");
        location_ref = database.getReference("Locations");
        date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        //getting current time
        TimePicker timePicker = new TimePicker(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            currentHour = timePicker.getHour();
            currentMunit = timePicker.getMinute();
        }else{
            currentHour = 1;
            currentMunit = 0;
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //adding initial option for spinner
        locations.add(spinner_default_item);

        agency_name= findViewById(R.id.travelsName);
        plane_number= findViewById(R.id.busNumber);
        ticket_price= findViewById(R.id.counter1_ticket_rice);

        sourceAirportTimer = findViewById(R.id.sourceAirportTimer);
        destinationAirportTimer = findViewById(R.id.destinationAirportTimer);

        total_row = findViewById(R.id.total_row);
        columnSample = findViewById(R.id.column_sample);

        sourceAirport = findViewById(R.id.sourceAirport);
        destinationAirport = findViewById(R.id.destinationid);

        callLocations();

        submit = findViewById(R.id.addPlane);

        //Setting the ArrayAdapter data on the Spinner

        submit.setOnClickListener(this);
        sourceAirport.setOnItemSelectedListener(this);
        destinationAirport.setOnItemSelectedListener(this);
        sourceAirportTimer.setOnClickListener(this);
        destinationAirportTimer.setOnClickListener(this);

    }

    private String creatingSeatPlan(int total_row, String column_sample){
        seats="";
        for(int row1=0;row1<total_row;row1++){

            seats+=column_sample;
        }
        return seats;

    }

    private void callLocations() {

        location_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot pairsnapshot : snapshot.getChildren()) {
                    //Log.d("output", String.valueOf(snapshot.getChildrenCount()));
                    String floc = pairsnapshot.getValue(String.class);
                    locations.add(floc);
                }
                setSpinnerValue();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setSpinnerValue() {

        ArrayAdapter locationAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,locations);
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceAirport.setAdapter(locationAdapter);
        destinationAirport.setAdapter(locationAdapter);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.addPlane){

            if(TextUtils.isEmpty(total_row.getText().toString()) ||(TextUtils.isEmpty(columnSample.getText().toString()) && columnSample.getText().toString().length()>=2)||TextUtils.isEmpty(agency_name.getText().toString()) || TextUtils.isEmpty(plane_number.getText().toString()) || TextUtils.isEmpty(ticket_price.getText().toString())){
                Toast.makeText(this, "Please fill all the information", Toast.LENGTH_SHORT).show();
            }else{
                agencyNameVariable = agency_name.getText().toString();
                planeNumberVariable=plane_number.getText().toString();
                tot_row = total_row.getText().toString();
                columnSampleVar = '/'+columnSample.getText().toString();

                String seatPlan = creatingSeatPlan(Integer.parseInt(tot_row) , columnSampleVar);

                //checking counter 1 info
                if(sourceAirportNameVariable.equals(spinner_default_item)){
                    ticket_price.setError("Select a counter first");
                    return;
                }
                else{
                    SourceCounterTicketPriceVariable=ticket_price.getText().toString();
                }

                //checking destinationAirport counter info
                if(DestinationAirportNameVariable.equals(spinner_default_item)){
                    Toast.makeText(this, "Select destinationAirport", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(!seatPlan.isEmpty()){
                    PlaneBundle planeBundle= new PlaneBundle(agencyNameVariable, planeNumberVariable, SourceCounterTicketPriceVariable, sourceAirportNameVariable, DestinationAirportNameVariable, seatPlan, sourceAirportTime, destinationAirportTime);
                    adbusref.child(planeNumberVariable).setValue(planeBundle);
                    finish();
                    Toast.makeText(this, "data sent", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "seatplan is empty", Toast.LENGTH_SHORT).show();
                }
            }
        }else if(v.getId()==R.id.sourceAirportTimer){


            dialog1 = new TimePickerDialog(AddPlane.this, new TimePickerDialog.OnTimeSetListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String ampm="";
                    int hour;
                    if(hourOfDay>=12){
                        ampm="PM";
                        if(hourOfDay>12){
                            hour=hourOfDay-12;
                        }else{
                            hour=12;
                        }

                    }else{
                        ampm="AM";
                        hour=hourOfDay;
                    }
                    if(hour==0) hour = 12;
                    sourceAirportTime = hour+":"+minute+" "+ampm;

                    sourceAirportTimer.setText(sourceAirportTime);
                    sourceAirportTimer.setTextColor(getResources().getColor(R.color.colortitle));
                }
            }, currentHour, currentMunit, false);
            dialog1.show();

        }

        else if(v.getId()==R.id.destinationAirportTimer){
            dialog4 = new TimePickerDialog(AddPlane.this, new TimePickerDialog.OnTimeSetListener() {
                @SuppressLint("ResourceAsColor")
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                    String ampm="";
                    int hour;
                    if(hourOfDay>=12){
                        ampm="PM";
                        if(hourOfDay>12){
                            hour=hourOfDay-12;
                        }else{
                            hour=12;
                        }
                    }else{
                        ampm="AM";
                        hour=hourOfDay;
                    }
                    if(hour==0) hour = 12;
                    destinationAirportTime = hour+":"+minute+" "+ampm;

                    destinationAirportTimer.setText(destinationAirportTime);
                    destinationAirportTimer.setTextColor(getResources().getColor(R.color.colortitle));
                }
            }, currentHour, currentMunit, false);
            dialog4.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.sourceAirport){
            //Toast.makeText(this, locations.get(position), Toast.LENGTH_SHORT).show();
            sourceAirportNameVariable = locations.get(position);
        }
        
        else if(parent.getId()==R.id.destinationid){

            //Toast.makeText(this, locations.get(position), Toast.LENGTH_SHORT).show();
            DestinationAirportNameVariable = locations.get(position);

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}