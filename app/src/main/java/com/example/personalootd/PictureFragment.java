package com.example.personalootd;

import static com.example.personalootd.PictureContent.loadSavedImages;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class PictureFragment extends Fragment implements ItemFragment.OnListFragmentInteractionListener {

    MainActivity mContext = (MainActivity) getActivity() ;
    RecyclerView recyclerView;
    GalleryRecyclerViewAdapter adapter;

    private int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.READ_EXTERNAL_STORAGE" };

    public PictureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //  Permission
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_picture, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bindList();

    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadSavedImages(new File(Environment.getExternalStorageDirectory() + "/Pictures"));
                //loadSavedImages(getActivity().getExternalFilesDir(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures"));
                adapter.notifyDataSetChanged();
            }
        });

    }

    private void bindList() {
        // List item 생성
        ArrayList<GalleryRecyclerItem> itemList = new ArrayList<>();

        // List item image 연결
        // 갤러리에서 연결하는 걸로 바꿔야함
        for (int i=0; i<12; i++){
            //itemList.add(new GalleryRecyclerItem(R.drawable.camera));
        }

        // Recycler view
        recyclerView = getActivity().findViewById(R.id.galleryRecyclerView);

        // Adapter 추가
        recyclerView.setAdapter(new GalleryRecyclerViewAdapter(PictureContent.ITEMS));
        adapter = (GalleryRecyclerViewAdapter) recyclerView.getAdapter();

        // Layout manager 추가
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onListFragmentInteraction(GalleryRecyclerItem item) {

    }
}