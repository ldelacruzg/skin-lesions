package com.example.skinlesions;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.example.skinlesions.Views.LesionsListActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.myAppBar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        // ActionBar actionBar = getSupportActionBar();
        // actionBar.setDisplayHomeAsUpEnabled(true);

        // Options
        LinearLayout lesionsOption = findViewById(R.id.lesionsOption);
        LinearLayout recommendOption = findViewById(R.id.recommendOption);
        LinearLayout takePhotoOption = findViewById(R.id.takePhotoOption);
        LinearLayout uploadPhotoOption = findViewById(R.id.uploadPhotoOption);

        lesionsOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LesionsListActivity.class);
                startActivity(intent);
            }
        });

        recommendOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Recommend Click", Toast.LENGTH_SHORT).show();
            }
        });

        takePhotoOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Take photo Click", Toast.LENGTH_SHORT).show();
            }
        });

        uploadPhotoOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Upload photo Click", Toast.LENGTH_SHORT).show();
            }
        });
    }
}