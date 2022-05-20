package com.example.personalootd.view;

public class RecommendItem {

    private int img;
    private String imgName;

    public RecommendItem(int img, String imgName) {
        this.img = img;
        this.imgName = imgName;

    }

    public void setImg(int img) {
        this.img = img;
    }

    //public String getImg() {
      //  return img;
    //}

    public void setImgName(String imgName) {this.imgName = imgName; }

    public int getImg () { return img; }

    public String getImgName() { return imgName; }

}
