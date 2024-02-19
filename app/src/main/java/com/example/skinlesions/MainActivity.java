package com.example.skinlesions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout lesionsOption = findViewById(R.id.lesionsOption);
        LinearLayout recommendOption = findViewById(R.id.recommendOption);
        LinearLayout takePhotoOption = findViewById(R.id.takePhotoOption);
        LinearLayout uploadPhotoOption = findViewById(R.id.uploadPhotoOption);

        lesionsOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Lesions Click", Toast.LENGTH_SHORT).show();
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