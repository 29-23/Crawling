package com.example.personalootd;

import android.net.Uri;

public class GalleryRecyclerItem {

    public Uri uri;





    private int imageResId;

    public GalleryRecyclerItem(int a_resId) {
        imageResId = a_resId;
    }

    public void setImageResId(int a_imageResId) {
        imageResId = a_imageResId;
    }

    public int getImageResId() {
        return imageResId;
    }
}