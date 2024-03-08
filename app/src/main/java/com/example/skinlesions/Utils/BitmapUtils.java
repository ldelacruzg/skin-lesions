package com.example.skinlesions.Utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public class BitmapUtils {
    public static Bitmap resizeBitmap(Bitmap bitmap, int targetWidth, int targetHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        float widthScale = ((float) targetWidth) / width;
        float heightScale = ((float) targetHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(widthScale, heightScale);

        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
    }
}
