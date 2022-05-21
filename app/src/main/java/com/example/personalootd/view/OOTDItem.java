package com.example.personalootd.view;

public class OOTDItem {

    private static int img;
    public OOTDItem(int img) {
        this.img = img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    //public String getImg() {
    //  return img;
    //}

    public static int getImg() { return img; }

}
