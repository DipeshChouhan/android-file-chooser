package com.miniblocks.androidfileselector.file_selector;

import java.io.File;

public class SimpleFile {
    private File mFile;
    private String fileName;
    public SimpleFile(File file){
        mFile = file;
    }

    public SimpleFile(File file, String fileName){
        this(file);
        this.fileName = fileName;
    }

    public String path(){
        return mFile.getAbsolutePath();
    }
    public String name(){
        return fileName == null ? mFile.getName() : fileName;
    }

    public boolean isDirectory(){
        return mFile.isDirectory();
    }
}
