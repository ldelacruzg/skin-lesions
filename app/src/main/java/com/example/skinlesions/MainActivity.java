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

import com.example.skinlesions.Views.LesionDetailActivity;
import com.example.skinlesions.Views.LesionsListActivity;
import com.example.skinlesions.Views.PhotoAnalysisActivity;

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
        //LinearLayout recommendOption = findViewById(R.id.recommendOption);
        LinearLayout takePhotoOption = findViewById(R.id.takePhotoOption);
        LinearLayout analizePhoneOption = findViewById(R.id.analizePhoneOption);
        LinearLayout uploadPhotoOption = findViewById(R.id.uploadPhotoOption);

        lesionsOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LesionsListActivity.class);
                startActivity(intent);
            }
        });

        takePhotoOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Take photo Click", Toast.LENGTH_SHORT).show();
            }
        });


        analizePhoneOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", 3); // TODO: Colocar el indice de la lesión

                Intent intent = new Intent(getApplicationContext(), PhotoAnalysisActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
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