package com.example.skinlesions.Services;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkinLesionRetrofit {
    public static Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl("https://skin-lesion-api.onrender.com/")
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                    .build();

    public static SkinLesionAPI skinLesionService = retrofit.create(SkinLesionAPI.class);
}
