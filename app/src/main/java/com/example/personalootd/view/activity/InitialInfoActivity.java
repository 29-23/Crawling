package com.example.personalootd.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalootd.R;

public class InitialInfoActivity extends AppCompatActivity {

    // User 퍼스널컬러 정보 저장
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    private RadioGroup radioGroup;
    private Button btn_move; // 다음
    private boolean check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences( "UserInfo" , MODE_PRIVATE);
        editor = preferences.edit();

        // 만약 user color 정보가 있다면 바로 main 이동
        if (preferences.getString("userColor","")!=null){
            Intent intent = new Intent(InitialInfoActivity.this, MainActivity.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_initial_info);

        radioGroup = findViewById(R.id.radio_group);
        btn_move = findViewById(R.id.btn_move);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio_null:
                        check = true;
                        break;
                    case R.id.radio_spring:
                        check = false;
                        editor.putString("userColor", "spring");
                        break;
                    case R.id.radio_summer:
                        check = false;
                        editor.putString("userColor", "summer");
                        break;
                    case R.id.radio_autumn:
                        check = false;
                        editor.putString("userColor", "autumn");
                        break;
                    case R.id.radio_winter:
                        check = false;
                        editor.putString("userColor", "winter");
                        break;
                }
            }
        });

        //다음 누르면 실행되는 곳 (info -> selfAnalysis)
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check){
                    Intent intent = new Intent(InitialInfoActivity.this, SelfAnalysisActivity.class);
                    startActivity(intent); //액티비티 이동
                }else{
                    Intent intent = new Intent(InitialInfoActivity.this, MainActivity.class);
                    startActivity(intent); //액티비티 이동
                }
            }
        });

    }

}