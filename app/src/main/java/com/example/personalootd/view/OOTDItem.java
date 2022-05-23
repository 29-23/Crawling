package com.example.personalootd.view;

public class OOTDItem {

    private String category;
    private String image;
    private String num;

    public OOTDItem(){}

    public OOTDItem(String category, String image, String num) {

        this.category = category;
        this.image = image;
        this.num = num;

    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getCategory (){
        return category;
    }

    public void setImage(String image){
        this.image = image;
    }

    public String getImage (){
        return image;
    }

    public void setNum(String num){
        this.num = num;
    }

    public String getNum (){
        return num;
    }

}
