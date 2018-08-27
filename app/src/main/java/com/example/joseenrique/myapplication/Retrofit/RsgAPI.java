package com.example.joseenrique.myapplication.Retrofit;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RsgAPI {

    @FormUrlEncoded
    @POST("products")
    Call<String> getDataPost(@Field("device") String device, @Field("user") String user, @Field("request") String request);

    @GET("validateProducts")
    Call<Data> getDataGet(@Path("device") String device, @Path("user") String user, @Path("request") String request);

    @FormUrlEncoded
    @POST("prodImg")
    Call<ResponseBody> postImage(@Field("device") String device, @Field("user") String user, @Field("request") String request);

    @GET("prodImg")
    Call<ResponseBody> getImage(@Path("device") String device, @Path("user") String user, @Path("request") String request);
}
