package com.example.personalootd.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalootd.R;

public class PersonalResActivity extends AppCompatActivity {

    SharedPreferences preferences;

    private Button btn_start; // 퍼스널 ootd 시작
    private Button btn_reAnalysis; // 다시 진단

    private TextView pTypeText;     // 퍼스널컬러 텍스트
    private TextView pTypeTextEng;  // 퍼스널컬러 텍스트 영어
    private ImageView pTypeImg;     // 배경
    private ImageView bestColors;     // 베스트컬러 모음
    private ImageView worstColors;     // 워스트컬러 모음


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_res);

        btn_start = findViewById(R.id.btn_start);
        btn_reAnalysis = findViewById(R.id.btn_reAnalysis);

        pTypeText = (TextView) findViewById(R.id.p_type_text);
        pTypeTextEng = (TextView) findViewById(R.id.p_type_eng);
        pTypeImg = (ImageView) findViewById(R.id.p_type_bg);
        bestColors = (ImageView) findViewById(R.id.image_bestColor);
        worstColors = (ImageView) findViewById(R.id.image_worstColor);

        preferences = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String inputText = preferences.getString("userColor","");
        if (inputText == "spring"){
            pTypeText.setText("봄 웜톤");
            pTypeTextEng.setText("Spring Warm");
            pTypeImg.setImageResource(R.drawable.spring_background);
            bestColors.setImageResource(R.drawable.spring_best);
            worstColors.setImageResource(R.drawable.winter_best);
        }
        else if (inputText == "summer"){
            pTypeText.setText("여름 쿨톤");
            pTypeTextEng.setText("Summer Cool");
            pTypeImg.setImageResource(R.drawable.summer_background);
            bestColors.setImageResource(R.drawable.summer_best);
            worstColors.setImageResource(R.drawable.autumn_best);

        }else if (inputText == "autumn"){
            pTypeText.setText("가을 웜톤");
            pTypeTextEng.setText("Autumn Warm");
            pTypeImg.setImageResource(R.drawable.autumn_background);
            bestColors.setImageResource(R.drawable.autumn_best);
            worstColors.setImageResource(R.drawable.summer_best);

        }else if (inputText == "winter"){
            pTypeText.setText("겨울 쿨톤");
            pTypeTextEng.setText("Winter Cool");
            pTypeImg.setImageResource(R.drawable.winter_background);
            bestColors.setImageResource(R.drawable.winter_best);
            worstColors.setImageResource(R.drawable.spring_best);

        }

        //시작 클릭하면 PersonalRes -> MainActivity
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalResActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });

        // 다시 진단 클릭하면 PersonalRes -> SelfAnalysisActivity
        btn_reAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PersonalResActivity.this, SelfAnalysisActivity.class);
                startActivity(intent);
            }
        });


    }
}