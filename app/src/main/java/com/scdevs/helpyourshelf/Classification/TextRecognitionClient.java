package com.scdevs.helpyourshelf.Classification;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.scdevs.helpyourshelf.DBModels.Image;
import com.scdevs.helpyourshelf.R;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class TextRecognitionClient {

    public Context context;

    public TextRecognitionClient(Context c){
        context = c;
    }

    public void getAllBooks(Mat frame)
    {
        Imgproc.HoughLines(frame, frame, 1, Math.PI / 180, 100);

//        ArrayList<String> names = new ArrayList<>();
//        Rect r = new Rect(0, 0, 7, frame.rows());
//        while (r.x + r.width < frame.cols())
//        {
//            Mat newMat = new Mat(frame, r);
//            Core.rotate(newMat,newMat ,Core.ROTATE_90_COUNTERCLOCKWISE);
//            String name = getTextFromBitmap(getBitmapFromMat(newMat));
//            if (name.length() >= 7)
//            {
//                names.add(name);
//                r = new Rect(r.x + r.width, 0, 7, frame.rows());
//            }
//            else
//                r = new Rect(r.x, 0, r.width + 7, frame.rows());
//        }
//        return names;
    }

    public String getTextFromBitmap(Bitmap b)
    {
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
        if (imageText.trim().length() != 0)
            System.out.println("WORDS: " +imageText);
        return imageText;
    }
    public Bitmap getBitmapFromMat(Mat mat){
        Bitmap bmp = Bitmap.createBitmap(mat.cols(),mat.rows(), Bitmap.Config.ARGB_8888);
        try {
            //Imgproc.cvtColor(seedsImage, tmp, Imgproc.COLOR_RGB2BGRA);
            Utils.matToBitmap(mat, bmp);}
        catch (CvException e){
            Log.d("Exception",e.getMessage());}

        return bmp;
    }
}
