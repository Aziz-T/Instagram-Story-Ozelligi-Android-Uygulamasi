package com.t.instagramstory;

import android.content.res.Configuration;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;

public class ShowCamera extends SurfaceView implements SurfaceHolder.Callback {

    private Camera camera;
            SurfaceHolder holder;

    public ShowCamera(CameraActivity context, Camera camera) {
            super(context);
            this.camera = camera;
            holder=getHolder();
            holder.addCallback(this);
            }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
            Camera.Parameters params = camera.getParameters();

            List<Camera.Size> sizes = params.getSupportedPictureSizes();
            Camera.Size size = null;
            for(Camera.Size size1 : sizes){
            size= size1;
            }

            if(this.getResources().getConfiguration().orientation!= Configuration.ORIENTATION_LANDSCAPE){
            params.set("orientation","portrait");
            camera.setDisplayOrientation(90);
            params.setRotation(90);
            }else {
            params.set("orientation","landscape");
            camera.setDisplayOrientation(0);
            params.setRotation(0);
            }

            params.setPictureSize(size.width,size.height);

            camera.setParameters(params);
            try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            } catch (IOException e) {
            e.printStackTrace();
            }


            }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

            }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
            camera.stopPreview();
            camera.release();

            }
            }
