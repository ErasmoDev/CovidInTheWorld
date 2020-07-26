package com.erasmodev.covidintheworld;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    TextView titleTv;
    ImageButton refreshBtn;
    BottomNavigationView navigationView;

    Fragment homeFragment, statsFragment;
    Fragment activeFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleTv = findViewById(R.id.titleTv);
        refreshBtn = findViewById(R.id.refreshBtn);
        navigationView = findViewById(R.id.navigationView);

        initFragments();

        refreshBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragment.onResume();
                statsFragment.onResume();
            }
        });

        navigationView.setOnNavigationItemSelectedListener(this);

    }

    private void initFragments() {

        homeFragment = new HomeFragment();
        statsFragment = new StatsFragment();

        fragmentManager = getSupportFragmentManager();
        activeFragment = homeFragment;

        fragmentManager.beginTransaction().add(R.id.frame, homeFragment, "homeFragment").commit();
        fragmentManager.beginTransaction().add(R.id.frame, statsFragment, "StatsFragment").hide(statsFragment).commit();
    }

    private void loadHomeFragment(){
        titleTv.setText("Home");
        fragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit();
        activeFragment = homeFragment;
    }
    private void loadStatsFragment(){
        titleTv.setText("COVID19 Stats");
        fragmentManager.beginTransaction().hide(activeFragment).show(statsFragment).commit();
        activeFragment = statsFragment;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch  (item.getItemId()){
            case R.id.nav_home:
                loadHomeFragment();
                return true;
            case R.id.nav_stats:
                loadStatsFragment();
                return true;
        }
        return false;
    }
}