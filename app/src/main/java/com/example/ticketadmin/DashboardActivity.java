package com.example.ticketadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.ticketadmin.activity.Bus;
import com.example.ticketadmin.activity.Train;
import com.example.ticketadmin.activity.Plane;
import com.example.ticketadmin.activity.DataLogActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        /*********************  Finding View  **********************/
        toolbar = findViewById(R.id.toolbarId);
        drawerLayout = findViewById(R.id.drawerLayoutId);
        navigationView = findViewById(R.id.nav_view);

        /*********************   ActionBar and Toolbar  **********************/
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu_icon);

        /**********************   ActionBarDrawerToggle For combining all  **********************/
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_navigation_drawer, R.string.close_navigation_drawer);
        drawerLayout.addDrawerListener(toggle);
        //toggle.syncState(); //Better bit its change the default icon

        /*********************  NavigationView Listener  **********************/
        navigationView.setNavigationItemSelectedListener(this);


        /*********************  NavigationView Default Checked Item**********************/
        navigationView.setCheckedItem(R.id.nev_home);


        /**********************    Menu Item Hide and Showing     **********************/
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nev_logout).setVisible(true);

    }


    public void toast(String msg) { Toast.makeText(this, msg, Toast.LENGTH_SHORT).show(); }

    public void byBus(View view) {

        startActivity(new Intent(DashboardActivity.this, Bus.class));


    }


    public void byTrain(View view) {
        startActivity(new Intent(DashboardActivity.this, Train.class));


    }



    public void byPlane(View view) {
        startActivity(new Intent(DashboardActivity.this, Plane.class));


    }



    public void byBoat(View view) {
        startActivity(new Intent(DashboardActivity.this,ByBoat.class));


    }



    public void addLocation(View view) {
        startActivity(new Intent(DashboardActivity.this,AddLocationActivity.class));

    }



    public void dataLog(View view) {
        startActivity(new Intent(DashboardActivity.this, DataLogActivity.class));


       //toast("byPlane_3");
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            super.onBackPressed();
        }
    }


    /*********************  NavigationView Item Selection**********************/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Menu menu = navigationView.getMenu();
        switch (item.getItemId()){
            case R.id.nev_home :
                break;
            case R.id.nev_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;

        }
        drawerLayout.closeDrawer(Gravity.LEFT);
        return true;
    }
}
