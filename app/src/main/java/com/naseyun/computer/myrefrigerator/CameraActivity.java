package com.naseyun.computer.myrefrigerator;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CameraActivity extends AppCompatActivity {
    public static android.widget.ImageView ImageView;
    Bitmap bitmap;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intent = getIntent();
        Bitmap imagebit = (Bitmap)intent.getParcelableExtra("image");
        imageView.setImageBitmap(imagebit);

        Button picBtn = findViewById(R.id.pic_btn);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults[0] == 0) {
                Toast.makeText(this, "카메라 권한이 승인됨", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "카메라 권한이 거절 되었습니다.카메라를 이용하려면 권한을 승낙하여야 합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}