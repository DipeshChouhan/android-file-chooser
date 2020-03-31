package com.miniblocks.androidfileselector;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.miniblocks.androidfileselector.file_selector.FileSelector;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openFileButtonClick(View view){
        // open File Button Clicked
        FileSelector.with(getSupportFragmentManager()).open();
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
