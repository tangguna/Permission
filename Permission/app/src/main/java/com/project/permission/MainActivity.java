package com.project.permission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;

import com.tangguna.thpermission.RxPermission;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RxPermission(MainActivity.this,getApplication(),Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE){
            @Override
            protected void onPermissionSuccess(@NonNull ArrayList<String> lowerPermissions) {
                super.onPermissionSuccess(lowerPermissions);
            }
        };
    }
}