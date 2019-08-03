package com.scdevs.helpyourshelf.Classification;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;

import com.scdevs.helpyourshelf.DBModels.Book;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvException;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BoxDetector {

    private Mat srcGray = new Mat();
    private Mat dst = new Mat();
    private Mat dstNorm = new Mat();
    private Mat dstNormScaled = new Mat();
    private static final int MAX_THRESHOLD = 255;
    private int threshold = 200;
    double area = 0;
    Rect holdR;

    public Bitmap runner(Mat mat,Context c) {
        try {
            System.loadLibrary("opencv_java3");

            Mat source = mat;
            Mat destination = new Mat(source.rows(), source.cols(), source.type());
            Mat tmp = destination;
            int threshold = 100;
            Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2GRAY);
            Imgproc.equalizeHist(destination, destination);
            Imgproc.Canny(destination, destination, threshold, threshold*3);

            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(destination, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
            MatOfPoint2f matOfPoint2f = new MatOfPoint2f();
            MatOfPoint2f approxCurve = new MatOfPoint2f();

            ArrayList<Rect> rects = new ArrayList<>();

            for (int idx = 0; idx >= 0; idx = (int) hierarchy.get(0, idx)[0]) {
                MatOfPoint contour = contours.get(idx);
                Rect rect = Imgproc.boundingRect(contour);
                Imgproc.rectangle(destination, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0, 255), 5);
                //double contourArea = Imgproc.contourArea(contour);
                matOfPoint2f.fromList(contour.toList());
                Imgproc.approxPolyDP(matOfPoint2f, approxCurve, Imgproc.arcLength(matOfPoint2f, true) * 0.02, true);
                long total = approxCurve.total();
                if (total >= 4 && total <= 6) {
                    List<Double> cos = new ArrayList<>();
                    Point[] points = approxCurve.toArray();
                    for (int j = 2; j < total + 1; j++) {
                        cos.add(angle(points[(int) (j % total)], points[j - 2], points[j - 1]));
                    }
                    Collections.sort(cos);
                    //Double minCos = cos.get(0);
                    //Double maxCos = cos.get(cos.size() - 1);
                    boolean isRect = total == 4 /*&& minCos >= -0.1 && maxCos <= 0.3*/;
                    if (isRect) {
                        rects.add(rect);
                        //double ratio = Math.abs(1 - (double) rect.width / rect.height);
                       // drawText(mat,rect.tl(),"Rectangle");
                    }
                }
            }
            getKeywordsFromBoxes(rects,destination,c);

            return getBitmapFromMat(new Mat(source, holdR));
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }

    private static double angle(Point pt1, Point pt2, Point pt0) {
        double dx1 = pt1.x - pt0.x;
        double dy1 = pt1.y - pt0.y;
        double dx2 = pt2.x - pt0.x;
        double dy2 = pt2.y - pt0.y;
        return (dx1*dx2 + dy1*dy2)/Math.sqrt((dx1*dx1 + dy1*dy1)*(dx2*dx2 + dy2*dy2) + 1e-10);
    }

    public List<String>getKeywordsFromBoxes (List<Rect>boxes, Mat original, Context c){
        System.out.println("Started getKeywords");
        Mat temp;
        Bitmap bmap;
        int i = 0;
        TextRecognitionClient trc = new TextRecognitionClient(c);
        List<String>words = new ArrayList<>();
        String s ="";
        for(Rect r: boxes){
            System.out.println("AREA: " + r.area());
            if (r.area() > area)
            {
                area = r.area();
                holdR = r;
            }
            temp = new Mat(original, r);
            while(i<4){
                bmap= getBitmapFromMat(temp);
                s = trc.getTextFromBitmap(bmap);
                Core.rotate(temp,temp,Core.ROTATE_90_CLOCKWISE);
                i++;
            }
            if(s.length()>1)
            words.add(s);
            i = 0;
        }
        System.out.println(words.toString());
        
        return words;
    }

    private static void drawText(Mat destination,Point ofs, String text) {
        Imgproc.putText(destination, text, ofs, Core.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(255,255,25));
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