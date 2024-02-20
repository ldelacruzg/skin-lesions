package com.example.skinlesions.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkinLesionRetrofit {
    public static Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/ldelacruzg/skin-lesion-api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    public static SkinLesionAPI skinLesionService = retrofit.create(SkinLesionAPI.class);
}
