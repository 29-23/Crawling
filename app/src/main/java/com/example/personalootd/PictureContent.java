package com.example.personalootd;

import android.net.Uri;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class PictureContent {
    static final ArrayList<GalleryRecyclerItem> ITEMS = new ArrayList<>();

    public static void loadSavedImages(File dir) {
        Log.d("aa", dir+"!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        ITEMS.clear();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                String absolutePath = file.getAbsolutePath();
                String extension = absolutePath.substring(absolutePath.lastIndexOf("."));
                if (extension.equals(".jpg")||extension.equals(".jpeg")||extension.equals(".png")) {
                    loadImage(file);
                }
            }
        }
    }

    public static void loadImage(File file) {
        // 아래의 파라미터 나중에 빼야함
        GalleryRecyclerItem newItem = new GalleryRecyclerItem(1);
        newItem.uri = Uri.fromFile(file);
        addItem(newItem);
    }

    private static void addItem(GalleryRecyclerItem item) {
        ITEMS.add(0, item);
    }
}
