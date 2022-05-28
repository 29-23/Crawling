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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.personalootd.R;
import com.example.personalootd.view.RecommendItem;
import com.example.personalootd.view.activity.MainActivity;
import com.example.personalootd.view.adapter.RecommendAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

// Home에서 OOTD 누르면 정보 설명하는 프래그먼트
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

    //Firebase DB
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    // 코디 제품
    private RecyclerView recommendRecyclerView;
    private RecommendAdapter recommendAdapter;
    private List<RecommendItem> itemList;

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
        Log.d("aa", "Info onAttach");

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
        Glide.with(view)
                .load(mainActivity.imgPath)
                .into(imageView);

        springPercentage = view.findViewById(R.id.spring_percentage);
        summerPercentage = view.findViewById(R.id.summer_percentage);
        autumnPercentage = view.findViewById(R.id.autumn_percentage);
        winterPercentage = view.findViewById(R.id.winter_percentage);

        springProgressBar = view.findViewById(R.id.spring_progressbar);
        summerProgressBar = view.findViewById(R.id.summer_progressbar);
        autumnProgressBar = view.findViewById(R.id.autumn_progressbar);
        winterProgressBar = view.findViewById(R.id.winter_progressbar);

        recommendRecyclerView = view.findViewById(R.id.ootd_cloths_recyclerview);

        // 여기 !!!! 이거 !!!!
        // numList에 불러올 ID 배열 연결하면 됨!!!!
        List<String> numList = new ArrayList<>();
        numList.add("19831");

        setItem(numList);

        return view;
    }

    private void setItem(List<String> numList) {
        itemList = new ArrayList<>();
        recommendRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("clothes/top");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    if(numList.size() == 0) break;
                    RecommendItem recommendItem = userSnapshot.getValue(RecommendItem.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    for (int i=0; i < numList.size(); i++){
                        String itemNum = numList.get(i);
                        if (itemNum.equals(recommendItem.getNum())){
                            itemList.add(recommendItem); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                        }
                    }
                }
                recommendAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });

        databaseReference = database.getReference("clothes/bottom");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    if(numList.size() == 0) break;
                    RecommendItem recommendItem = userSnapshot.getValue(RecommendItem.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    for (int i=0; i < numList.size(); i++){
                        String itemNum = numList.get(i);
                        if (itemNum.equals(recommendItem.getNum())){
                            itemList.add(recommendItem); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                            numList.remove(i);
                        }
                    }
                }
                recommendAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                throw error.toException();
            }
        });

        setPercentage();

        recommendAdapter = new RecommendAdapter(itemList, R.layout.item_recommend);
        recommendRecyclerView.setAdapter(recommendAdapter);
        recommendRecyclerView.setItemAnimator(new DefaultItemAnimator());
        recommendAdapter.notifyDataSetChanged() ;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPercentage();
    }

    private void setPercentage() {

        // spring, summer autumn, winter 순서
        int [] percent = new int [4];
        String[] text = new String [4];

        // 이 아래 코드에 옷 사진 퍼스널컬러 분석 결과 넣으면 됨
        percent[0] = 10;
        percent[1] = 20;
        percent[2] = 30;
        percent[3] = 90;

        text[0] = percent[0]+"%";
        text[1] = percent[1]+"%";
        text[2] = percent[2]+"%";
        text[3] = percent[3]+"%";

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
            case R.id.back_btn2:
            {
                // go to Picture Fragment
                mainActivity.goHomeFr();
                break;
            }

        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity.bottomNavigationView.setVisibility(View.VISIBLE);
        mainActivity =null;
        Log.d("aa", "Info onDetach");

    }
}