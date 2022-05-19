package com.example.personalootd.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.personalootd.R;
import com.example.personalootd.view.fragment.HomeFragment;
import com.example.personalootd.view.fragment.PairingFragment;
import com.example.personalootd.view.fragment.PictureFragment;
import com.example.personalootd.view.fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    PictureFragment pictureFragment;
    SettingsFragment settingsFragment;
    PairingFragment pairingFragment;

    BottomNavigationView bottomNavigationView;

    ImageView backBtn;
    ImageView logo;
    ImageView goBtn;

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        pictureFragment = new PictureFragment();
        settingsFragment = new SettingsFragment();
        pairingFragment = new PairingFragment();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigationBar);
        bottomNavigationView.setSelectedItemId(R.id.home);

        backBtn = (ImageView) findViewById(R.id.back_btn);
        logo = (ImageView) findViewById(R.id.logo);
        goBtn = (ImageView) findViewById(R.id.go_btn);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationBar);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        goBtn.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.pic:
                        goBtn.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, pictureFragment).commit();

                        return true;
                    case R.id.settings:
                        goBtn.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, settingsFragment).commit();
                        return true;
                }
                return false;
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pairingFragment!=null){
                    getSupportFragmentManager().beginTransaction()
                            .remove(pairingFragment)
                            .commit();
                }

            }
        });

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomNavigationView.setVisibility(View.GONE);
                backBtn.setVisibility(View.VISIBLE);
                logo.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, pairingFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}