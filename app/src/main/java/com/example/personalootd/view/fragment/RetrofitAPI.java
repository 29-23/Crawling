package com.test.retrofit_test2;

import java.util.HashMap;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST("/posts")
    Call<Post> postData(@FieldMap HashMap<String, Object> param);

    @Multipart
    @POST("/get_data")
    Call<Ootd_find> request(@Part MultipartBody.Part file);
}
