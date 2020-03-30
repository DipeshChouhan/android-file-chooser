package com.miniblocks.androidfileselector;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private FileUtil util;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       util = new FileUtil();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        getFiles();
    }

    public void getFiles(){
        File file = Environment.getExternalStorageDirectory();
        File[] files = file.listFiles(filter);

        if(files != null){
            ArrayList<File> fileList = new ArrayList<>(Arrays.asList(files));
            adapter.setItems(fileList);
        }

    }

//    public void getFiles(File file){
//
//        if(!file.isDirectory()){
//
//            System.out.println("___file___"+file.getName());
//        }else{
//            File[] files = file.listFiles(filter);
//            System.out.println("____dir___"+file.getName());
//            if(files != null){
//                for(File f: files){
//                    getFiles(f);
//                }
//            }
//
//        }
//    }

    FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.isDirectory() || util.isFileImage(pathname);
        }
    };


}
