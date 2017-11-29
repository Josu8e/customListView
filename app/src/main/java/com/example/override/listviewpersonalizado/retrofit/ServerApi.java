package com.example.override.listviewpersonalizado.retrofit;

import com.example.override.listviewpersonalizado.Base.ListData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Josue on 21/11/2017.
 */

public interface ServerApi {
    @GET("getAllProducts")
    Call<ArrayList<ListData>> getAllProducts();

    @GET("getProduct/{id}")
    Call<ListData> getProduct(@Path("id") String id);

    @PUT("update/{id}")
    Call<ListData> update(@Path("id") String id,@Body ListData product);

    @DELETE("delete/{id}")
    Call<ListData> delete(@Path("id") String id);

    @POST("create")
    Call<ListData> create(@Body ListData product);
}