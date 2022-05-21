package com.example.personalootd.view.fragment;

import android.content.Context;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener{

    MainActivity mainActivity;

    // 추천 의류 리사이클러뷰
    private RecyclerView ootdRecyclerView;
    private OOTDAdapter ootdAdapter;
    private List<OOTDItem> itemList;

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

        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ootdRecyclerView = (RecyclerView) view.findViewById(R.id.ootd_recyclerview);
        initRecyclerView();

    }

    private void initRecyclerView() {
        itemList = new ArrayList<>();
        ootdRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        // 이 아래에 itemList 초기화 코드 작성
        for (int i=0; i<10; i++) {
            itemList.add(new OOTDItem(R.drawable.spring));
        }

        ootdAdapter = new OOTDAdapter(itemList, R.layout.item_ootd);
        ootdRecyclerView.setAdapter(ootdAdapter);
        ootdRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ootdAdapter.notifyDataSetChanged() ;
    }

    private OnOOTDClickListener mOnOOTDClickListener = new OnOOTDClickListener(){

        @Override
        public void OnItemClick(OOTDAdapter.OOTDViewHolder ootdViewHolder, int position) {
            // firebase -> ootd 정보 -> Info Fragment
            OOTDItem ootdItem = ootdAdapter.getmItemList().get(position);
            mainActivity.tmpImg = OOTDItem.getImg();

        }
    };

    @Override
    public void onClick(View view) {

    }
}