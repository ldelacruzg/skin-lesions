package com.example.skinlesions.Services;

import com.example.skinlesions.Models.Lesion;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkinLesionService {
    private Retrofit retrofit;

    public SkinLesionService() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/ldelacruzg/skin-lesion-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
