package com.miniblocks.androidfileselector.file_selector.util;

import android.os.Environment;

public class FileUtil {
    /**
     * @return {String} Returns the path to internal storage ex:- /storage/emulated/0
     */
    public  static String getInternalDirectoryPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * @return {String OR null} Returns the SD card storage path for samsung ex:-
     * /storage/extSdCard OR null if SD card is not present
     */
    public static String getSDcardDirectoryPath() {
        return System.getenv("SECONDARY_STORAGE");
    }

}
