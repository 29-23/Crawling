package com.example.personalootd.view;

public class RecommendItem {

    private String brand;
    private String image;
    private String link;
    private String name;
    private String num;

    public RecommendItem(){}

    public RecommendItem(String brand, String image, String link, String name, String num) {
        this.brand = brand;
        this.image = image;
        this.link = link;
        this.name = name;
        this.num = num;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getBrand(){
        return brand;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setLink(String link){
        this.link = link;
    }

    public String getLink(){
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNum (String num) { this.num = num; }

    public String getNum () { return num; }

}
