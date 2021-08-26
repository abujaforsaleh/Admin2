package com.example.admin.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.R;

public class BusStatistics extends AppCompatActivity {
    TextView staview, title;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_statistics);


        back = findViewById(R.id.staback);
        title = findViewById(R.id.title_sta);

        staview = findViewById(R.id.bus_sta_view);
        String val_received = getIntent().getStringExtra("statis");
        String bus_number = getIntent().getStringExtra("number");
        title.setText(bus_number);

        if(TextUtils.isEmpty(val_received)){
            staview.setText("No Data Available");
        }else{
            staview.setText(val_received);
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}