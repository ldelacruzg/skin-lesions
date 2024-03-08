package com.example.skinlesions.Utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.skinlesions.ml.SkinViewModel;;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ImageClassifier {
    int imageSize = 300;
    Context context;

    public ImageClassifier(Context ctx) {
        this.context = ctx;
    }

    public int classifyImage(Bitmap originalImage) {
        try {
            Bitmap image = BitmapUtils.resizeBitmap(originalImage, imageSize, imageSize);
            SkinViewModel model = SkinViewModel.newInstance(context.getApplicationContext());

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, imageSize, imageSize, 3}, DataType.FLOAT32);

            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());
            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++]; //RGB
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 1));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 1));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            SkinViewModel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            int maxResultDisease = 0;
            float maxConfidence = 0;
            float minConfidence = 0;
            for (int i = 0; i < confidences.length; i++) {
                System.out.println("Confidence: " + confidences[i]);
                if (confidences[i] > maxConfidence) {
                    maxConfidence = confidences[i];
                    maxResultDisease = i;
                }
                if (confidences[i] <= minConfidence) {
                    minConfidence = confidences[i];
                }
            }
            maxResultDisease += 1;

            if (maxResultDisease > 37) {
                maxResultDisease += 1;
            }

            //model.close();
            return maxResultDisease;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } catch (Exception e) {
            System.out.println("Classifier Error: " + e.getMessage());
            return -1;
        }
    }
}
