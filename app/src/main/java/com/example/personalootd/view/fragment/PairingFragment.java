package com.example.personalootd.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalootd.R;
import com.example.personalootd.view.RecommendItem;
import com.example.personalootd.view.activity.MainActivity;
import com.example.personalootd.view.adapter.RecommendAdapter;

import java.util.ArrayList;
import java.util.List;

// PicturFragment에서 전달받은 의류와 어울리는 코디 추천하는 곳
public class PairingFragment extends Fragment implements View.OnClickListener{

    MainActivity mainActivity;

    //백 버튼
    private ImageView backBtn;

    // Picture Fragment에서 넘겨 받는 사진
    private ImageView imageView;

    // 퍼스널컬러 적합도 분석 결과 퍼센티지
    private TextView  springPercentage;
    private TextView  summerPercentage;
    private TextView  autumnPercentage;
    private TextView  winterPercentage;

    // 프로그레스바
    private ProgressBar springProgressBar;
    private ProgressBar summerProgressBar;
    private ProgressBar autumnProgressBar;
    private ProgressBar winterProgressBar;

    // 추천 의류 리사이클러뷰
    private RecyclerView recommendRecyclerView;
    private RecommendAdapter recommendAdapter;
    private List<RecommendItem> itemList;

    public PairingFragment() {
        // Required empty public constructor
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


    public static PairingFragment newInstance(String param1, String param2) {
        PairingFragment fragment = new PairingFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_pairing, container, false);

        backBtn = (ImageView) view.findViewById(R.id.back_btn);
        backBtn.setOnClickListener(this);

        imageView = (ImageView) view.findViewById(R.id.imageView);
        ((ImageView)view.findViewById(R.id.cloth_img)).setImageBitmap(mainActivity.picturebm);

        springPercentage = (TextView) view.findViewById(R.id.spring_percentage);
        summerPercentage = (TextView) view.findViewById(R.id.summer_percentage);
        autumnPercentage = (TextView) view.findViewById(R.id.autumn_percentage);
        winterPercentage = (TextView) view.findViewById(R.id.winter_percentage);

        springProgressBar = (ProgressBar) view.findViewById(R.id.spring_progressbar);
        summerProgressBar = (ProgressBar) view.findViewById(R.id.summer_progressbar);
        autumnProgressBar = (ProgressBar) view.findViewById(R.id.autumn_progressbar);
        winterProgressBar = (ProgressBar) view.findViewById(R.id.winter_progressbar);

        setPercentage();
        getRecommendClothes();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recommendRecyclerView = (RecyclerView) view.findViewById(R.id.recommend_cloths_recyclerview);
        initRecyclerView();

    }

    private void initRecyclerView() {
        itemList = new ArrayList<>();
        recommendRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        // 이 아래에 itemList 초기화 코드 작성
        for (int i=0; i<10; i++) {
            itemList.add(new RecommendItem(R.drawable.spring, "aaa"));
        }

        recommendAdapter = new RecommendAdapter(itemList, R.layout.item_recommend);
        recommendRecyclerView.setAdapter(recommendAdapter);
        recommendRecyclerView.setItemAnimator(new DefaultItemAnimator());
        recommendAdapter.notifyDataSetChanged() ;
    }

    private void getRecommendClothes() {
    }

    private void setPercentage() {
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.back_btn:
            {
                // go to Picture Fragment
                mainActivity.goPictureFr();
                break;
            }

        }
    }
}