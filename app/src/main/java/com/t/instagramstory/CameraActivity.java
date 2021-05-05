package com.t.instagramstory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CameraActivity extends AppCompatActivity {
    private Camera camera;
    private FrameLayout frameLayout;
    ShowCamera showCamera;
    private ImageView imageView;

    String cameraPermission[];
    String storagePermission[];

    private Button button;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
    String currentDateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        frameLayout=findViewById(R.id.frame);
        button = findViewById(R.id.button);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkStoragePermission()){
                    requestStoragePermission();

                }else {
                    getImage();
                }
            }
        });

        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE };
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE };

        // String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        //  String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        if(!checkCameraPermission()){
            requestCameraPermission();

        }else {
            camera = Camera.open();
            showCamera = new ShowCamera(this, camera);
            frameLayout.addView(showCamera);
        }
    }
    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(this,cameraPermission,1001);
    }
    private boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1=  ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }


    Camera.PictureCallback mPictureCallBack = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            File pFile = getOutputMediaFile();
            Bitmap picture = BitmapFactory.decodeByteArray(data,0,data.length);
            imageView = new ImageView(getApplicationContext());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            imageView.setLayoutParams(layoutParams);
            imageView.setImageBitmap(picture);
            frameLayout.addView(imageView);
            if(pFile==null){
                return;
            }else{
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(pFile);
                    fileOutputStream.write(data);
                    fileOutputStream.close();

                   // camera.startPreview();
                    //camera.stopPreview();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }
    };

    private File getOutputMediaFile() {
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)){
            return null;
        }else {
            File folder = new File(Environment.getExternalStorageDirectory()+File.separator+"GUI");
            if(!folder.exists()){
                folder.mkdirs();
                Toast.makeText(getApplicationContext(), "mkdirs", Toast.LENGTH_SHORT).show();
            }
            File outputFile=new File(folder,currentDateandTime+"orn.jpg");
            return outputFile;
        }
    }



    public void getImage(){
        if(camera!=null){

            camera.takePicture(null,null,mPictureCallBack);
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestStoragePermission() {
        ActivityCompat.requestPermissions(this,storagePermission,1000);
    }
    private boolean checkStoragePermission() {
        boolean result=  ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }
}