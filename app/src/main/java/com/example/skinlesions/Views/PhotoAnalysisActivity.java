package com.example.skinlesions.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.skinlesions.Adapters.ListCareAdapter;
import com.example.skinlesions.Adapters.ListTreatmentAdapter;
import com.example.skinlesions.Models.Lesion;
import com.example.skinlesions.Models.Treatment;
import com.example.skinlesions.R;
import com.example.skinlesions.Services.SkinLesionAPI;
import com.example.skinlesions.Services.SkinLesionRetrofit;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoAnalysisActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView textViewLesionName, textViewLesionDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_analysis);

        // Toolbar
        toolbar = findViewById(R.id.myAppBar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Análisis");
        setSupportActionBar(toolbar);

        // AppBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Bundle
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");

        textViewLesionName = findViewById(R.id.textViewLesionName);
        textViewLesionDescription = findViewById(R.id.textViewLesionDescription);

        getLesionById(id);
    }

    private void setCareList(List<String> cares) {
        RecyclerView recyclerViewCareList = findViewById(R.id.recyclerViewCareList);
        RecyclerView.LayoutManager layoutManagerCareList = new LinearLayoutManager(this);
        RecyclerView.Adapter adapterCareList = new ListCareAdapter(cares, R.layout.list_item_symptoms);
        recyclerViewCareList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewCareList.setLayoutManager(layoutManagerCareList);
        recyclerViewCareList.setAdapter(adapterCareList);
    }

    private void setTreatmentList(List<Treatment> treatments) {
        RecyclerView recyclerViewTreatmentList = findViewById(R.id.recyclerViewTreatmentList);
        RecyclerView.LayoutManager layoutManagerTreatmentList = new LinearLayoutManager(this);
        RecyclerView.Adapter adapterTreatmentList = new ListTreatmentAdapter(treatments, R.layout.list_item_faq);
        recyclerViewTreatmentList.setItemAnimator(new DefaultItemAnimator());
        recyclerViewTreatmentList.setLayoutManager(layoutManagerTreatmentList);
        recyclerViewTreatmentList.setAdapter(adapterTreatmentList);
    }

    private void setGlobalData(Lesion lesion) {
        textViewLesionName.setText(lesion.getName());
        textViewLesionDescription.setText(lesion.getDescription());
    }

    private void getLesionById(int id) {
        SkinLesionAPI service = SkinLesionRetrofit.skinLesionService;
        Call<Lesion> call = service.getLesionById(id);

        call.enqueue(new Callback<Lesion>() {
            @Override
            public void onResponse(Call<Lesion> call, Response<Lesion> response) {
                Lesion lesion = response.body();
                setGlobalData(lesion);
                setTreatmentList(lesion.getTreatments());
                setCareList(lesion.getCare());
            }

            @Override
            public void onFailure(Call<Lesion> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}