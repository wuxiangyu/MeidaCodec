package com.example.media3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    private SurfaceView mSurface = null;
    private SurfaceHolder mSurfaceHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        requestPermission("android.permission.WRITE_EXTERNAL_STORAGE", 1);
        requestPermission("android.permission.READ_EXTERNAL_STORAGE", 1);

        mSurface = (SurfaceView) findViewById(R.id.surfaceview);

        mSurfaceHolder = mSurface.getHolder();
        new MediaCodecUtil(mSurfaceHolder);

        SurfaceView surfaceView = findViewById(R.id.surfaceview2);
        new MediaCodecUtil(surfaceView.getHolder());

        SurfaceView surfaceView2 = findViewById(R.id.surfaceview3);
        new MediaCodecUtil(surfaceView2.getHolder());
        //        mSurfaceHolder.addCallback(new SurfaceHolder.Callback() {
//            MediaCodecUtil mmediaCodecUtil;
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//                mmediaCodecUtil = new MediaCodecUtil(holder);
//                mmediaCodecUtil.initDecodec();
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//                mmediaCodecUtil.stopDecodec();
//            }
//        });

    }

    private void requestPermission(String permission, int requestCode) {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this, permission ) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);

            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

}