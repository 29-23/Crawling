package com.example.personalootd;

import com.example.personalootd.view.adapter.GalleryAdapter;

public interface OnGalleryClickListener {

    void OnItemClick(GalleryAdapter.PhotoViewHolder photoViewHolder , int position, int preposition);

}