package com.example.personalootd.view.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.personalootd.R;
import com.example.personalootd.view.RecommendItem;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendViewHolder> {

    private int itemLayout;
    private List<RecommendItem> mItemList;

    public RecommendAdapter(List<RecommendItem> itemList, int itemLayout){

        this.mItemList = itemList;
        this.itemLayout = itemLayout;
    }

    public List<RecommendItem> getmItemList() {
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

        Glide.with(viewHolder.itemView)
                .load(recommendItem.getImage())
                .into(viewHolder.itemImg);

        viewHolder.itemBrand.setText(recommendItem.getBrand());
        viewHolder.itemName.setText(recommendItem.getName());

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    public static class RecommendViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemImg;
        public TextView  itemBrand;
        public TextView  itemName;

        public RecommendViewHolder(View itemView) {
            super(itemView);

            itemImg = (ImageView) itemView.findViewById(R.id.recommend_item_img);
            itemBrand = (TextView) itemView.findViewById(R.id.recommend_item_brand);
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
