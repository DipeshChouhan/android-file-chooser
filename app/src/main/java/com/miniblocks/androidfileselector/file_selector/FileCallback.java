package com.miniblocks.androidfileselector.file_selector;

public interface FileCallback {
    void selectedFiles(String errorMessage, String ...paths);
}
