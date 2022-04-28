package com.example.personalootd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PersonalRes extends AppCompatActivity {


    private Button btn_start; // 퍼스널 ootd 시작
    private Button btn_reAnalysis; // 다시 진단

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_res);

        btn_start = findViewById(R.id.btn_start);
        btn_reAnalysis = findViewById(R.id.btn_reAnalysis);

        //시작 클릭하면 PersonalRes -> MainActivity
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalRes.this, MainActivity.class);
                startActivity(intent);

            }
        });

        // 다시 진단 클릭하면 PersonalRes -> SelfAnalysisActivity
        btn_reAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalRes.this, SelfAnalysisActivity.class);
                startActivity(intent);
            }
        });


    }
}