package com.example.personalootd.view.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalootd.R;
import com.example.personalootd.view.RecommendItem;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    private Activity mActivity;

    private int itemLayout;
    private List<RecommendItem> mItemList;

    public RecommendAdapter(List<RecommendItem> itemList, int itemLayout){

        this.mItemList = itemList;
        this.itemLayout = itemLayout;
    }

    public List<RecommendItem> getmPhotoList() {
        return mItemList;
    }

    @NonNull
    @Override
    public RecommendViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(itemLayout, viewGroup, false);
        return new RecommendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {

        final RecommendItem recommendItem = mItemList.get(position);


        /*  Gallery Adapter Code 복붙.. 가져오는 코드 새로 만들어야 함
        Glide.with(mActivity)
                .load(viewHolder.getImgPath())
                .centerCrop()
                .transition(withCrossFade())
                .into(viewHolder.itemImg);

         */
        viewHolder.itemImg.setImageResource(recommendItem.getImg());
        viewHolder.itemName.setText(recommendItem.getImgName());

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    public static class RecommendViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemImg;
        public TextView itemName;

        public RecommendViewHolder(View itemView) {
            super(itemView);

            itemImg = (ImageView) itemView.findViewById(R.id.recommend_item_img);
            itemName = (TextView) itemView.findViewById(R.id.recommend_item_name);

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    final int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        //
                    }
                }
            });

        }

    }
}
