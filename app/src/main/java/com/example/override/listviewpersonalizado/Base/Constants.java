package com.example.override.listviewpersonalizado.Base;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Josue on 21/11/2017.
 */

public class constants {
    public static final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://172.24.45.149/").addConverterFactory(GsonConverterFactory.create()).build();
}
