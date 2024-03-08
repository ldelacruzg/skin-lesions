package com.example.skinlesions;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.skinlesions.Utils.ImageClassifier;
import com.example.skinlesions.Views.LesionDetailActivity;
import com.example.skinlesions.Views.LesionsListActivity;
import com.example.skinlesions.Views.PhotoAnalysisActivity;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    ImageView picture;
    LinearLayout analizePhoneOption;
    ImageClassifier classifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        classifier = new ImageClassifier(this);

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
        analizePhoneOption = findViewById(R.id.analizePhoneOption);
        LinearLayout uploadPhotoOption = findViewById(R.id.uploadPhotoOption);

        picture = findViewById(R.id.image_capture);

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
                if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 3);
                } else {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 100);
                }
            }
        });

        uploadPhotoOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        //TODO: Delete this line
        //setAnalyzeButtonOnClickEvent(40);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 3) {
                // Código para procesar la imagen tomada con la cámara
                Bitmap image = (Bitmap) data.getExtras().get("data");
                int dimension = Math.min(image.getWidth(), image.getHeight());
                image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                picture.setImageBitmap(image);
                picture.setImageTintMode(null);
                int imageCode = classifier.classifyImage(image);
                setAnalyzeButtonOnClickEvent(imageCode);
            } else if (requestCode == 1) {
                // Código para procesar la imagen seleccionada desde la galería
                if (data != null && data.getData() != null) {
                    Uri dat = data.getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(dat);
                        Bitmap image = BitmapFactory.decodeStream(inputStream);

                        if (image != null) {
                            int dimension = Math.min(image.getWidth(), image.getHeight());
                            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                            picture.setImageBitmap(thumbnail);
                            picture.setImageTintMode(null);
                            int imageCode = classifier.classifyImage(image);
                            setAnalyzeButtonOnClickEvent(imageCode);
                        } else {
                            Log.e("H_Alta", "Error al obtener la imagen desde la galería.");
                        }
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("H_Alta", "Error al obtener la imagen desde la galería.");
                    }
                }
            }
        }
    }

    private void setAnalyzeButtonOnClickEvent(int i) {
        analizePhoneOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", i); // TODO: Colocar el indice de la lesión

                Intent intent = new Intent(getApplicationContext(), PhotoAnalysisActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }
}