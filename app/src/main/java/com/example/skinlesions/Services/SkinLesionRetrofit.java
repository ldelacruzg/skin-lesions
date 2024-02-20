package com.example.skinlesions.Services;

import retrofit2.Retrofit;

public class SkinLesionRetrofit {
    public static Retrofit retrofit =
            new Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/ldelacruzg/skin-lesion-api/")
                    .build();
}
