package com.scdevs.helpyourshelf.Classification;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.scdevs.helpyourshelf.R;

import static android.content.ContentValues.TAG;

public class TextRecognitionClient {

    public Context context;

    public TextRecognitionClient(Context c){
        context = c;
    }

    public String getTextFromBitmap(Bitmap b){
        TextRecognizer textRecognizer = new TextRecognizer.Builder(context).build();

        Frame imageFrame = new Frame.Builder()
                .setBitmap(b)                 // your image bitmap
                .build();

        String imageText = "";


        SparseArray<TextBlock> textBlocks = textRecognizer.detect(imageFrame);

        for (int i = 0; i < textBlocks.size(); i++) {
            TextBlock textBlock = textBlocks.get(textBlocks.keyAt(i));
            imageText += textBlock.getValue();                   // return string
        }
        return imageText;
    }
}
