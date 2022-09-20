package com.rinkusaini.a2_myappteam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity_admin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout_admin);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new com.rinkusaini.a2_myappteam.HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        auth = FirebaseAuth.getInstance();
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new com.rinkusaini.a2_myappteam.HomeFragment()).commit();
                break;
            case R.id.nav_inbox:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InboxFragment()).commit();
                break;
            case R.id.nav_chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ChatFragment()).commit();
                break;
            case R.id.nav_employee:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EmployeeFragment()).commit();
                break;
            case R.id.nav_salesman:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SalesmanFragment()).commit();
                break;
            case R.id.nav_summary:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SummaryFragment()).commit();
                break;
            case R.id.nav_target:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TargetFragment()).commit();
                break;

            case R.id.nav_logout:
                auth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(this, "Logged Out !", Toast.LENGTH_SHORT).show();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }
}