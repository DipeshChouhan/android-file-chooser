package com.miniblocks.androidfileselector;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.miniblocks.androidfileselector.file_selector.FileSelector;

import java.nio.file.FileSystemException;


public class MainActivity extends AppCompatActivity {
FileSelector selector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openFileButtonClick(View view){
        // open File Button Clicked
        selector = FileSelector.with(getSupportFragmentManager())
                .setFileType("image", ".png", ".jpg", ".jpeg").build();
        selector.open();

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("onResume activity");
    }


}









































//
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
