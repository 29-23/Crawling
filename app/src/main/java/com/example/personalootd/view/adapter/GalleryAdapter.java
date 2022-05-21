package com.example.personalootd.view.adapter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.personalootd.OnGalleryClickListener;
import com.example.personalootd.R;
import com.example.personalootd.view.GalleryItem;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.PhotoViewHolder> {


    private Activity mActivity;

    private int itemLayout;
    private List<GalleryItem> mPhotoList;

    private OnGalleryClickListener onGalleryClickListener;


    /**
     * PhotoList 반환
     * @return
     */
    public List<GalleryItem> getmPhotoList() {
        return mPhotoList;
    }


    /**
     * 선택된 PhotoList 반환
     * @return
     */
    public List<GalleryItem> getSelectedPhotoList(){

        List<GalleryItem> mSelectPhotoList = new ArrayList<>();

        for (int i = 0; i < mPhotoList.size(); i++) {

            GalleryItem galleryItem = mPhotoList.get(i);
            if(galleryItem.isSelected()){
                mSelectPhotoList.add(galleryItem);
            }
        }

        return mSelectPhotoList;
    }

    /**
     * 아이템 선택시 호출되는 리스너
     * @param onGalleryClickListener
     */
    public void setOnItemClickListener(OnGalleryClickListener onGalleryClickListener) {
        this.onGalleryClickListener = onGalleryClickListener;
    }


    /**
     * 생성자
     * @param photoList
     * @param itemLayout
     */
    public GalleryAdapter(Activity activity, List<GalleryItem> photoList, int itemLayout) {

        mActivity = activity;

        this.mPhotoList = photoList;
        this.itemLayout = itemLayout;

    }




    /**
     * 레이아웃을 만들어서 Holer에 저장
     *
     * @param viewGroup
     * @param viewType
     * @return
     */
    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new PhotoViewHolder(view);
    }


    /**
     * listView getView 를 대체
     * 넘겨 받은 데이터를 화면에 출력하는 역할
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(final PhotoViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        final GalleryItem galleryItem = mPhotoList.get(position);

        Glide.with(mActivity)
                .load(galleryItem.getImgPath())
                .centerCrop()
                .transition(withCrossFade())
                .into(viewHolder.imgPhoto);

        //선택
        if(galleryItem.isSelected()){
            viewHolder.layoutSelect.setVisibility(View.VISIBLE);
        }else{
            viewHolder.layoutSelect.setVisibility(View.INVISIBLE);
        }


        // 이거 PhotoViewHolder로 옮겨야 함
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            int preposition = -1;

            @Override
            public void onClick(View v) {

                if (onGalleryClickListener != null) {
                    onGalleryClickListener.OnItemClick(viewHolder, position, preposition);
                    preposition = viewHolder.getAbsoluteAdapterPosition();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mPhotoList.size();
    }


    /**
     * 뷰 재활용을 위한 viewHolder
     */
    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgPhoto;
        public RelativeLayout layoutSelect;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            imgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);
            layoutSelect = (RelativeLayout) itemView.findViewById(R.id.layoutSelect);
        }

    }
}