package com.example.skinlesions.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.skinlesions.Adapters.ListFAQAdapter;
import com.example.skinlesions.Adapters.ListLesionAdapter;
import com.example.skinlesions.Adapters.ListSymptomsAdapter;
import com.example.skinlesions.Models.FAQ;
import com.example.skinlesions.Models.Lesion;
import com.example.skinlesions.Models.Symptom;
import com.example.skinlesions.R;
import com.example.skinlesions.Services.SkinLesionAPI;
import com.example.skinlesions.Services.SkinLesionRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LesionDetailActivity extends AppCompatActivity {

    TextView textViewLesionName, textViewLesionDescription;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesion_detail);

        // Toolbar
        toolbar = findViewById(R.id.myAppBar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        // AppBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Bundle
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");

        // Init values
        this.textViewLesionName = findViewById(R.id.textViewLesionName);
        this.textViewLesionDescription = findViewById(R.id.textViewLesionDescription);

        getLesionById(id);
    }

    private void setGlobalData(Lesion lesion) {
        this.toolbar.setTitle(lesion.getName());
        this.textViewLesionName.setText(lesion.getName());
        this.textViewLesionDescription.setText(lesion.getDescription());
    }

    private void setSymtomsList(List<Symptom> symtoms) {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewSymptomList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new ListSymptomsAdapter(symtoms,  R.layout.list_item_symptoms);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setFAQList(List<FAQ> faqList) {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFaqList);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ListFAQAdapter(faqList));
    }

    private void getLesionById(int id) {
        SkinLesionAPI service = SkinLesionRetrofit.skinLesionService;
        Call<Lesion> call = service.getLesionById(id);

        call.enqueue(new Callback<Lesion>() {
            @Override
            public void onResponse(Call<Lesion> call, Response<Lesion> response) {
                Lesion lesion = response.body();
                setGlobalData(lesion);
                setSymtomsList(lesion.getSymptoms());
                setFAQList(lesion.getFaq());
            }

            @Override
            public void onFailure(Call<Lesion> call, Throwable t) {
                //System.out.println(t.getMessage());
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