package com.example.skinlesions.Services;

import com.example.skinlesions.Models.Lesion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SkinLesionAPI {
    @GET("lesions")
    Call<List<Lesion>> getLesions();

    @GET("lesions/{id}")
    Call<Lesion> getLesionById(@Path("id") int id);
}
