package com.example.personalootd;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class GalleryRecyclerViewHolder extends RecyclerView.ViewHolder {


    /*ImageView img;

    public GalleryRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        img = itemView.findViewById(R.id.image_item);

    }*/

    public final View mView;
    public final ImageView mImageView;
    public GalleryRecyclerItem mItem;

    public GalleryRecyclerViewHolder(View view) {
        super(view);
        mView = view;
        mImageView = view.findViewById(R.id.galleryRecyclerView);
    }
}
