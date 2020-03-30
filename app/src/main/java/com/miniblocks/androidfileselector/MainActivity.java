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

    private LinkedList<String> permissions = new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }



        setContentView(R.layout.activity_main);

    }

    public void requestButton(View view){
        for(String permission : permissions){
            if(ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED){
                permissions.remove(permission);
            }
        }
        requestPermissions();
    }

    public void requestPermissions(){
        String[] array = new String[permissions.size()];
        for(int i = 0; i < permissions.size(); i++){
            array[i] = permissions.get(i);
        }
        if(permissions.size() != 0){
            ActivityCompat.requestPermissions(this, array, 2);
        }

    }
}
