package com.example.skinlesions.Services;

import com.example.skinlesions.Models.Lesion;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkinLesionAPI {
    @GET("lesions")
    Call<List<Lesion>> getLesions();
}
