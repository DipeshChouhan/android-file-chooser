package com.miniblocks.androidfileselector;

import java.io.File;

public class FileUtil {
    private String[] extentions = {".png", ".jpg", ".bmp", ".jpeg", ".tif",
            ".webp", ".gif", "heic", "heif"};
    private int extensionsSize = extentions.length;
    public boolean isFileImage(File file){
        for(int i = 0; i < extensionsSize; i++){
            if(file.getName().endsWith(extentions[i])){
                return true;
            }
        }
        return false;
    }
}
