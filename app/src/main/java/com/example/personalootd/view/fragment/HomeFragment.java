package com.example.personalootd.view.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalootd.OnOOTDClickListener;
import com.example.personalootd.R;
import com.example.personalootd.view.OOTDItem;
import com.example.personalootd.view.activity.MainActivity;
import com.example.personalootd.view.adapter.OOTDAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener{

    MainActivity mainActivity;
    SharedPreferences preferences;

    // 추천 의류 리사이클러뷰
    private RecyclerView ootdRecyclerView;
    private OOTDAdapter ootdAdapter;
    private List<OOTDItem> itemList;

    //Firebase DB
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();

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
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        preferences = this.getActivity().getSharedPreferences("UserInfo", MODE_PRIVATE);
        String userColor = preferences.getString("userColor","");

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ootdRecyclerView = (RecyclerView) view.findViewById(R.id.ootd_recyclerview);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRecyclerView();
            }
        });

    }

    private void initRecyclerView() {
        itemList = new ArrayList<>();
        ootdRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        // 이 아래에 itemList 초기화 코드 작성
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("codi/autumn");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                itemList.clear();
                for (DataSnapshot snapshot : datasnapshot.getChildren()) { // 반복문으로 데이터 List를 추출해냄
                    OOTDItem ootdItem = snapshot.getValue(OOTDItem.class); // 만들어뒀던 User 객체에 데이터를 담는다.
                    itemList.add(ootdItem); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                ootdAdapter.notifyDataSetChanged() ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("aa", String.valueOf(error.toException()));
            }
        });

        ootdAdapter = new OOTDAdapter(itemList, R.layout.item_ootd, getContext());
        ootdAdapter.setOnItemClickListener(mOnOOTDClickListener);
        ootdRecyclerView.setAdapter(ootdAdapter);
        ootdRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ootdAdapter.notifyDataSetChanged() ;
    }

    private OnOOTDClickListener mOnOOTDClickListener = new OnOOTDClickListener(){

        @Override
        public void OnItemClick(OOTDAdapter.OOTDViewHolder ootdViewHolder, int position) {
            // firebase -> ootd 정보 -> Info Fragment


        }
    };

    @Override
    public void onClick(View view) {

    }


}