package com.example.personalootd.view.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.personalootd.R;
import com.example.personalootd.view.fragment.HomeFragment;
import com.example.personalootd.view.fragment.PictureFragment;
import com.example.personalootd.view.fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    HomeFragment homeFragment;
    PictureFragment pictureFragment;
    SettingsFragment settingsFragment;
    BottomNavigationView bottomNavigationView;

    ImageView goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeFragment = new HomeFragment();
        pictureFragment = new PictureFragment();
        settingsFragment = new SettingsFragment();
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigationBar);
        bottomNavigationView.setSelectedItemId(R.id.home);

        goBtn = (ImageView) findViewById(R.id.go_btn);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationBar);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        goBtn.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.pic:
                        goBtn.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, pictureFragment).commit();

                        return true;
                    case R.id.settings:
                        goBtn.setVisibility(View.GONE);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, settingsFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}