package com.example.override.listviewpersonalizado.Base;

import com.example.override.listviewpersonalizado.retrofit.ServerApi;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Josue on 21/11/2017.
 */

public class Constants {
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://13.58.144.176:3000/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public ServerApi serverApi = retrofit.create(ServerApi.class);

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
