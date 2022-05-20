package com.example.personalootd.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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

    MainActivity mainActivity;

    private static final int REQUEST_IMAGE_CAPTURE = 100;
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

        View view = inflater.inflate(R.layout.fragment_picture, container, false);

        camBtn = (ImageView) view.findViewById(R.id.cameraIcon);
        goBtn = (ImageView) view.findViewById(R.id.go_btn);

        // 버튼 클릭 리스너
        camBtn.setOnClickListener(this);
        goBtn.setOnClickListener(this);

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
            // Log.i("", ">>> selectedList   :  " + selectedPhotoList.get(i).getImgPath());
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
        public void OnItemClick(GalleryAdapter.PhotoViewHolder photoViewHolder, int position, int preposition) {

            PhotoVO photoVO = galleryAdapter.getmPhotoList().get(position);

            if(photoVO.isSelected()){
                photoVO.setSelected(false);
                ((ImageView)getView().findViewById(R.id.imageView)).setImageBitmap(null);

            }else{
                photoVO.setSelected(true);
                galleryAdapter.getmPhotoList().set(position,photoVO);

                Bitmap bm = BitmapFactory.decodeFile(photoVO.getImgPath());
                ((ImageView)getView().findViewById(R.id.imageView)).setImageBitmap(bm);
                //Log.d("aa", "bitmap in pic : "+ bm);

                mainActivity.bm = bm;

                // 원래 아이템 하나만 선택되는거고
                // 다른 아이템 선택하면 기존 선택 해제돼야하는데
                // 아직 구현 안됨... ㅎㅎㅎㅎㅎㅎ
                //Log.d("aa", "Preposition : " + preposition);
                if (preposition > 0 ){
                    photoVO = galleryAdapter.getmPhotoList().get(preposition);
                    photoVO.setSelected(false);
                }

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
            {
                Toast.makeText(getActivity(), "go button clicked", Toast.LENGTH_SHORT).show();
                mainActivity.goPairingFr();

            }

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