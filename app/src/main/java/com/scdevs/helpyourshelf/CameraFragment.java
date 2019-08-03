package com.scdevs.helpyourshelf;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.scdevs.helpyourshelf.Classification.BoxDetector;
import com.scdevs.helpyourshelf.Classification.TextRecognitionClient;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link CameraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CameraFragment extends Fragment implements CameraBridgeViewBase.CvCameraViewListener2 {

    CameraBridgeViewBase cameraBridgeViewBase;
    BaseLoaderCallback baseLoaderCallback;
    boolean saveImg = false;
    FloatingActionButton fab;
    ImageView testImgView;
    ArrayList<String> allNames = new ArrayList<>();

    public static CameraFragment newInstance(){
        CameraFragment fragment = new CameraFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_camera , container, false);

        fab = view.findViewById(R.id.camerabutton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImg = true;
            }
        });
        testImgView = (ImageView) view.findViewById(R.id.TestImgView);
        return view;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        cameraBridgeViewBase = (CameraBridgeViewBase) getView().findViewById(R.id.cameraView);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCvCameraViewListener(this);

        baseLoaderCallback = new BaseLoaderCallback(getActivity()) {
            @Override
            public void onManagerConnected(int status) {
                super.onManagerConnected(status);
                switch (status)
                {
                    case BaseLoaderCallback.SUCCESS:
                        cameraBridgeViewBase.enableView();
                        break;
                    default:
                        super.onManagerConnected(status);
                        break;
                }
            }

            @Override
            public void onPackageInstall(int operation, InstallCallbackInterface callback) {
                super.onPackageInstall(operation, callback);
            }
        };




    }
    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat frame = inputFrame.rgba();
        if (saveImg)
        {
            saveImg = false;
            TextRecognitionClient trc = new TextRecognitionClient(getContext());
            String name = trc.getTextFromBitmap(trc.getBitmapFromMat(frame));
//            TextRecognitionClient trc = new TextRecognitionClient(getContext());
//            trc.getTextFromBitmap(map);
            allNames.add(name);
        }


        return frame;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!OpenCVLoader.initDebug());
        else
            baseLoaderCallback.onManagerConnected(baseLoaderCallback.SUCCESS);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (cameraBridgeViewBase!=null)
            cameraBridgeViewBase.disableView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraBridgeViewBase!=null)
            cameraBridgeViewBase.disableView();
    }
}
