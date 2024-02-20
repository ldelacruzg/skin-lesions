package com.example.skinlesions.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.skinlesions.Adapters.ListLesionAdapter;
import com.example.skinlesions.Models.Lesion;
import com.example.skinlesions.R;
import com.example.skinlesions.Services.SkinLesionAPI;
import com.example.skinlesions.Services.SkinLesionRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LesionsListActivity extends AppCompatActivity {
    List<Lesion> lesions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesions_list);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.myAppBar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Lesiones");
        setSupportActionBar(toolbar);

        // AppBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // List
        getLesionList();
    }

    private void setLesionList(List<Lesion> lesions) {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewLesions);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new ListLesionAdapter(
                lesions,
                R.layout.list_item_lesion,
                new ListLesionAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Lesion lesion, int position) {

                    }
                }
        );

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void getLesionList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://my-json-server.typicode.com/ldelacruzg/skin-lesion-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SkinLesionAPI service = retrofit.create(SkinLesionAPI.class);
        Call<List<Lesion>> lesions = service.getLesions();

        lesions.enqueue(new Callback<List<Lesion>>() {
            @Override
            public void onResponse(Call<List<Lesion>> call, Response<List<Lesion>> response) {
                List<Lesion> result = response.body();
                setLesionList(result);
            }

            @Override
            public void onFailure(Call<List<Lesion>> call, Throwable t) {
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