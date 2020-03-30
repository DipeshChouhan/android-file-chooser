package com.miniblocks.androidfileselector;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

    }

    public void requestButton(View view){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN){
            AskPermission permissions = new AskPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            permissions.request();
        }

    }


}
