package com.example.personalootd.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalootd.OnItemClickListener;
import com.example.personalootd.R;
import com.example.personalootd.view.GalleryManager;
import com.example.personalootd.view.GridDividerDecoration;
import com.example.personalootd.view.PhotoVO;
import com.example.personalootd.view.activity.MainActivity;
import com.example.personalootd.view.adapter.GalleryAdapter;

import java.util.List;

// 상의/하의 사진 촬영 또는 갤러리에서 이미지 선택
public class PictureFragment extends Fragment implements View.OnClickListener{

    private static final int REQUEST_IMAGE_CAPTURE = 100;
    MainActivity mContext = (MainActivity) getActivity() ;
    private GalleryManager mGalleryManager;
    private RecyclerView recyclerGallery;
    private GalleryAdapter galleryAdapter;

    private ImageView goBtn;
    private ImageView camBtn;

    private static final int REQUEST_CAMERA = 100;

    public PictureFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_picture, container, false);
        //goBtn = view.findViewById(R.id.go_btn);
        camBtn = view.findViewById(R.id.cameraIcon);

        camBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerGallery = (RecyclerView) view.findViewById(R.id.recyclerGallery);
        initRecyclerGallery();

    }

    @Override
    public void onResume() {
        super.onResume();
        /*
        getActivity().runOnUiThread(new Runnable() {


            @Override
            public void run() {

            }
        });
        */
    }


    /**
     * 갤러리 이미지 데이터 초기화
     */
    private List<PhotoVO> initGalleryPathList() {

        mGalleryManager = new GalleryManager( getActivity().getApplicationContext());
        return mGalleryManager.getAllPhotoPathList();
    }


    /**
     * 확인 버튼 선택 시
     */
    private void selectDone() {

        List<PhotoVO> selectedPhotoList = galleryAdapter.getSelectedPhotoList();
        for (int i = 0; i < selectedPhotoList.size(); i++) {
            Log.i("", ">>> selectedPhotoList   :  " + selectedPhotoList.get(i).getImgPath());
        }
    }


    /**
     * 갤러리 리사이클러뷰 초기화
     */
    private void initRecyclerGallery() {

        galleryAdapter = new GalleryAdapter(getActivity(), initGalleryPathList(), R.layout.item_photo);
        galleryAdapter.setOnItemClickListener(mOnItemClickListener);
        recyclerGallery.setAdapter(galleryAdapter);
        recyclerGallery.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        recyclerGallery.setItemAnimator(new DefaultItemAnimator());
        recyclerGallery.addItemDecoration(new GridDividerDecoration(getResources(), R.drawable.divider_recycler_gallery));
    }


    /**
     * 리사이클러뷰 아이템 선택시 호출 되는 리스너
     */
    private OnItemClickListener mOnItemClickListener = new OnItemClickListener() {

        @Override
        public void OnItemClick(GalleryAdapter.PhotoViewHolder photoViewHolder, int position) {

            PhotoVO photoVO = galleryAdapter.getmPhotoList().get(position);

            if(photoVO.isSelected()){
                photoVO.setSelected(false);
            }else{
                photoVO.setSelected(true);
            }

            galleryAdapter.getmPhotoList().set(position,photoVO);
            galleryAdapter.notifyDataSetChanged();

        }
    };

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.cameraIcon:
            {
                // camera 켜고 사진 촬영
                callCamera();
                break;
            }
            case R.id.go_btn:
                // go to Pairing Fragment
                break;

        }
    }

    private void callCamera() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ((ImageView)getView().findViewById(R.id.imageView)).setImageBitmap(imageBitmap);
        }
    }
}