package com.example.personalootd.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalootd.R;

public class InitialInfoActivity extends AppCompatActivity {

    private Button btn_move; // 다음

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_info);

        btn_move = findViewById(R.id.btn_move);

        //다음 누르면 실행되는 곳 (info -> selfAnalysis)
        btn_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InitialInfoActivity.this, SelfAnalysisActivity.class);
                startActivity(intent); //액티비티 이동

            }
        });

    }
}