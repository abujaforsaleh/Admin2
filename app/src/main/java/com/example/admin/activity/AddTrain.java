package com.example.admin.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

import com.example.admin.DashboardActivity;
import com.example.admin.R;
import com.example.admin.bundle.TrainBundle;
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

public class AddTrain extends AppCompatActivity  implements View.OnClickListener, AdapterView.OnItemSelectedListener{


    DatabaseReference adtrainref, location_ref;
    String seats="", counter1_time="", counter2_time="", counter3_time="", destination_time="";
    ImageView back;
    List<String> trainSeatPlan = new ArrayList<>();

    EditText total_row, total_column, travels_name, bogy_number, counter1_ticket_price, counter2_ticket_price, counter3_ticket_price;
    Button submit;
    Spinner trainType;
    String date;

    int currentHour, currentMunit;

    String spinner_default_item="Select an Option";
    TextView counter1_timer, counter2_timer, counter3_timer, destination_timer;
    String[] train_types = { "AC", "Non AC", "Slipper"};
    String tr_name="", bogy_num="", cou_1_tic_price="", cou_2_tic_price="", cou_3_tic_price="", tot_row="", column_sample="";
    String train_type_selected = "",counter1_name="", counter2_name="", counter3_name="", destination_counter_name="";

    private TimePickerDialog dialog1, dialog2, dialog3, dialog4;

    List<String> locations= new ArrayList<>();

    Spinner counter1, counter2, counter3, destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train);

        back = findViewById(R.id.addtrainback);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adtrainref = database.getReference("Trains");
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

        travels_name= findViewById(R.id.ttrainName);
        bogy_number= findViewById(R.id.bogynumber);
        counter1_ticket_price= findViewById(R.id.counter1_ticket_rice);
        counter2_ticket_price= findViewById(R.id.counter2_ticket_rice);
        counter3_ticket_price= findViewById(R.id.counter3_ticket_rice);
        trainType = findViewById(R.id.trainType);

        counter1_timer = findViewById(R.id.counter1_timer);
        counter2_timer = findViewById(R.id.counter2_timer);
        counter3_timer = findViewById(R.id.counter3_timer);
        destination_timer = findViewById(R.id.destination_timer);

        total_row = findViewById(R.id.total_row);
        total_column = findViewById(R.id.total_column);

        counter1 = findViewById(R.id.counter1);
        counter2 = findViewById(R.id.counter2);
        counter3 = findViewById(R.id.counter3);
        destination = findViewById(R.id.destinationid);

        callLocations();

        submit = findViewById(R.id.addTrain);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,train_types);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        trainType.setAdapter(aa);

        submit.setOnClickListener(this);
        counter1.setOnItemSelectedListener(this);
        counter2.setOnItemSelectedListener(this);
        counter3.setOnItemSelectedListener(this);
        destination.setOnItemSelectedListener(this);
        trainType.setOnItemSelectedListener(this);
        counter1_timer.setOnClickListener(this);
        counter2_timer.setOnClickListener(this);
        counter3_timer.setOnClickListener(this);
        destination_timer.setOnClickListener(this);

    }

    private List<String> creatingSeatPlan(int total_bugy, int total_row, String colum_sample){

        for(int bugy = 0;bugy<total_bugy;bugy++){
            seats="/";
            for(int row1=0;row1<total_row;row1++){

                seats+=colum_sample;
                seats+="/";
            }
            trainSeatPlan.add(seats);
        }

        return trainSeatPlan;

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
        counter1.setAdapter(locationAdapter);
        counter2.setAdapter(locationAdapter);
        counter3.setAdapter(locationAdapter);
        destination.setAdapter(locationAdapter);

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.addTrain){

            tr_name = travels_name.getText().toString();
            bogy_num=bogy_number.getText().toString();
            tot_row = total_row.getText().toString();
            column_sample = total_column.getText().toString();

            if(counter1_name.equals(spinner_default_item) || destination_counter_name.equals(spinner_default_item) || TextUtils.isEmpty(total_row.getText().toString()) || TextUtils.isEmpty(total_column.getText().toString()) || TextUtils.isEmpty(travels_name.getText().toString()) || TextUtils.isEmpty(bogy_number.getText().toString()) || TextUtils.isEmpty(counter1_ticket_price.getText().toString())){
                Toast.makeText(this, "Please fill all the information", Toast.LENGTH_SHORT).show();
            }else if(Integer.parseInt(tot_row)>40){
                Toast.makeText(getApplicationContext(), "Row is unreal", Toast.LENGTH_SHORT).show();
            }
            else{

                List<String> seatPlan = creatingSeatPlan(Integer.parseInt(bogy_num), Integer.parseInt(tot_row) , column_sample);

                //checking counter 1 info
                if(counter1_name.equals(spinner_default_item)){
                    counter1_ticket_price.setError("Select a counter first");
                    return;
                }
                else{
                    cou_1_tic_price=counter1_ticket_price.getText().toString();
                }
                //checking counter 2 info
                if(!TextUtils.isEmpty(counter2_ticket_price.getText().toString())){
                    if(counter2_name.equals(spinner_default_item)){
                        counter2_ticket_price.setError("Select a counter first");
                        return;
                    }
                    else{
                        cou_2_tic_price=counter2_ticket_price.getText().toString();
                    }

                }
                if(!counter2_name.equals(spinner_default_item)){
                    if(TextUtils.isEmpty(counter2_ticket_price.getText().toString())) {
                        counter2_ticket_price.setError("Enter Ticket Price");
                        return;
                    }
                    else{
                        cou_2_tic_price=counter2_ticket_price.getText().toString();
                    }
                }

                //checking counter 3 info
                if(!TextUtils.isEmpty(counter3_ticket_price.getText().toString())){
                    if(counter3_name.equals(spinner_default_item)){
                        counter3_ticket_price.setError("Select a counter first");
                        return;
                    }
                    else{
                        cou_3_tic_price=counter3_ticket_price.getText().toString();
                    }
                }

                if(!counter3_name.equals(spinner_default_item)){
                    if(TextUtils.isEmpty(counter3_ticket_price.getText().toString())) {
                        counter3_ticket_price.setError("Enter Ticket Price");
                        return;
                    }
                    else{
                        cou_3_tic_price=counter3_ticket_price.getText().toString();
                    }
                }
                //checking destination counter info
                if(destination_counter_name.equals(spinner_default_item)){
                    Toast.makeText(this, "Select destination", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(seatPlan.size()>0){
                    Log.d("train_val","ok");
                    TrainBundle trainBundle= new TrainBundle(tr_name, bogy_num, cou_1_tic_price, cou_2_tic_price, cou_3_tic_price, counter1_name, counter2_name, counter3_name, destination_counter_name, train_type_selected, seatPlan, counter1_time, counter2_time, counter3_time, destination_time);
                    adtrainref.child(tr_name+"_"+counter1_name+"_"+destination_counter_name).setValue(trainBundle);

                    Log.d("train_val", tr_name+" "+counter1_name+" "+destination_counter_name);
                    Intent intent = new Intent(this, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    //finish();

                    Toast.makeText(this, "data sent", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "seatplan is empty", Toast.LENGTH_SHORT).show();
                }
            }
        }else if(v.getId()==R.id.counter1_timer){


            dialog1 = new TimePickerDialog(AddTrain.this, new TimePickerDialog.OnTimeSetListener() {
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
                    counter1_time = hour+":"+minute+" "+ampm;

                    counter1_timer.setText(counter1_time);
                    counter1_timer.setTextColor(getResources().getColor(R.color.colortitle));
                }
            }, currentHour, currentMunit, false);
            dialog1.show();

        }
        else if(v.getId()==R.id.counter2_timer){
            dialog2 = new TimePickerDialog(AddTrain.this, new TimePickerDialog.OnTimeSetListener() {
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
                    counter2_time = hour+":"+minute+" "+ampm;

                    counter2_timer.setText(counter2_time);
                    counter2_timer.setTextColor(getResources().getColor(R.color.colortitle));
                }
            }, currentHour, currentMunit, false);
            dialog2.show();
        }
        else if(v.getId()==R.id.counter3_timer){
            dialog3 = new TimePickerDialog(AddTrain.this, new TimePickerDialog.OnTimeSetListener() {
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
                    counter3_time = hour+":"+minute+" "+ampm;

                    counter3_timer.setText(counter3_time);
                    counter3_timer.setTextColor(getResources().getColor(R.color.colortitle));
                }
            }, currentHour, currentMunit, false);
            dialog3.show();
        }
        else if(v.getId()==R.id.destination_timer){
            dialog4 = new TimePickerDialog(AddTrain.this, new TimePickerDialog.OnTimeSetListener() {
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
                    destination_time = hour+":"+minute+" "+ampm;

                    destination_timer.setText(destination_time);
                    destination_timer.setTextColor(getResources().getColor(R.color.colortitle));
                }
            }, currentHour, currentMunit, false);
            dialog4.show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId()==R.id.counter1){
            //Toast.makeText(this, locations.get(position), Toast.LENGTH_SHORT).show();
            counter1_name = locations.get(position);

        }
        else if(parent.getId()==R.id.counter2){
            //Toast.makeText(this, locations.get(position), Toast.LENGTH_SHORT).show();
            counter2_name = locations.get(position);

        }
        else if(parent.getId()==R.id.counter3){

            //Toast.makeText(this, locations.get(position), Toast.LENGTH_SHORT).show();
            counter3_name = locations.get(position);

        }
        else if(parent.getId()==R.id.destinationid){

            //Toast.makeText(this, locations.get(position), Toast.LENGTH_SHORT).show();
            destination_counter_name = locations.get(position);

        }
        else if(parent.getId()==R.id.trainType){

            //Toast.makeText(this, train_types[position], Toast.LENGTH_SHORT).show();
            train_type_selected = train_types[position];

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}