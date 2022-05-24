package com.example.personalootd.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.personalootd.R;
import com.example.personalootd.view.activity.MainActivity;

// Home에서 OOTD 누르면 정보 설명하는 곳
public class InfoFragment extends Fragment implements View.OnClickListener{

    MainActivity mainActivity;

    //백 버튼
    private ImageView backBtn;

    private ImageView imageView;

    // 퍼스널컬러 적합도 분석 결과 퍼센티지
    private TextView springPercentage;
    private TextView  summerPercentage;
    private TextView  autumnPercentage;
    private TextView  winterPercentage;

    // 프로그레스바
    private ProgressBar springProgressBar;
    private ProgressBar summerProgressBar;
    private ProgressBar autumnProgressBar;
    private ProgressBar winterProgressBar;

    // 코디 제품
    private ImageView ootdImg1;
    private TextView ootdName1;
    private ImageView ootdImg2;
    private TextView ootdName2;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(String param1, String param2) {
        InfoFragment fragment = new InfoFragment();

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity =(MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity =null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_info, container, false);

        backBtn = view.findViewById(R.id.back_btn2);
        backBtn.setOnClickListener(this);

        imageView = view.findViewById(R.id.cloth_img);
        imageView.setImageResource(R.drawable.summer_background);
        //((ImageView)view.findViewById(R.id.cloth_img)).setImageBitmap(mainActivity.);

        springPercentage = view.findViewById(R.id.spring_percentage);
        summerPercentage = view.findViewById(R.id.summer_percentage);
        autumnPercentage = view.findViewById(R.id.autumn_percentage);
        winterPercentage = view.findViewById(R.id.winter_percentage);

        springProgressBar = view.findViewById(R.id.spring_progressbar);
        summerProgressBar = view.findViewById(R.id.summer_progressbar);
        autumnProgressBar = view.findViewById(R.id.autumn_progressbar);
        winterProgressBar = view.findViewById(R.id.winter_progressbar);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPercentage();
    }

    private void setPercentage() {

        // spring, summer autumn, winter 순서

        String[] text = new String [4];
        int [] percent = new int [4];

        // 이 아래 코드에 옷 사진 퍼스널컬러 분석 결과 넣으면 됨
        text[0] = "10%";
        text[1] = "20%";
        text[2] = "30%";
        text[3] = "90%";

        percent[0] = 10;
        percent[1] = 20;
        percent[2] = 30;
        percent[3] = 90;

        // 이 아래는 안 봐도 됨
        springPercentage.setText(text[0]);
        summerPercentage.setText(text[1]);
        autumnPercentage.setText(text[2]);
        winterPercentage.setText(text[3]);

        springProgressBar.setProgress(percent[0]);
        summerProgressBar.setProgress(percent[1]);
        autumnProgressBar.setProgress(percent[2]);
        winterProgressBar.setProgress(percent[3]);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.back_btn:
            {
                Log.d("aa", "back button clicked");
                // go to Picture Fragment
                mainActivity.goHomeFr();
                break;
            }

        }
    }
}