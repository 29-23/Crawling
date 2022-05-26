package com.test.retrofit_test2;

import com.google.gson.annotations.SerializedName;

public class Ootd_find {
    @SerializedName("num")
    private String num[];

    public String[] getNum() {
        return num;
    }

    public void setNum(String[] num) {
        this.num = num;
    }
}
