package com.example.personalootd.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.personalootd.R;
import com.example.personalootd.view.fragment.HomeFragment;
import com.example.personalootd.view.fragment.PairingFragment;
import com.example.personalootd.view.fragment.PictureFragment;
import com.example.personalootd.view.fragment.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment;
    private PictureFragment pictureFragment;
    private SettingsFragment settingsFragment;
    private PairingFragment pairingFragment;

    private BottomNavigationView bottomNavigationView;

    // Picture, Pairing Fragment에 사용될 이미지
    public Bitmap picturebm;
    // Home Fragmen OOTD 이미지. 파이어베이스에서 코드 가져오는걸로 고쳐야 함
    public int tmpImg;

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

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationBar);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.pic:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, pictureFragment).commit();
                        return true;
                    case R.id.settings:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, settingsFragment).commit();
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

    public void goBack() {
        if (pairingFragment!=null){
            getSupportFragmentManager().beginTransaction()
                    .remove(pairingFragment)
                    .commit();
        }
    }

    public void goPairingFr(){
        bottomNavigationView.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, pairingFragment)
                .addToBackStack(null)
                .commit();
    }

    public void goPictureFr(){
        bottomNavigationView.setVisibility(View.VISIBLE);
        if (pairingFragment!=null){
            getSupportFragmentManager().beginTransaction()
                    .remove(pairingFragment)
                    .commit();
        }

    }

}