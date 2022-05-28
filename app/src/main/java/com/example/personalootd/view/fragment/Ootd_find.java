package com.example.personalootd.view.fragment;

import com.google.gson.annotations.SerializedName;

public class Ootd_find {
    @SerializedName("num")
    private String num[];

    @SerializedName("spring")
    private String spring;

    @SerializedName("summer")
    private String summer;

    @SerializedName("autumn")
    private String autumn;

    @SerializedName("winter")
    private String winter;

    public String[] getNum() {
        return num;
    }

    public void setNum(String[] num) {
        this.num = num;
    }

    public String getSpring() {
        return spring;
    }

    public void setSpring(String spring) {
        this.spring = spring;
    }

    public String getSummer() {
        return summer;
    }

    public void setSummer(String summer) {
        this.summer = summer;
    }

    public String getAutumn() {
        return autumn;
    }

    public void setAutumn(String autumn) {
        this.autumn = autumn;
    }

    public String getWinter() {
        return winter;
    }

    public void setWinter(String winter) {
        this.winter = winter;
    }
}
