package com.miniblocks.androidfileselector.file_selector;

/**
 * A simple wrapper class for files
 */
class SimpleFile {

    public String path;
    public String name;
    public String indicatorText;
    public boolean isDirectory;

    /**
     * Initializing all instance variables.
     * @param path - file path
     * @param name - name of the file
     * @param indicatorText - folder indicator text ex:- "3 subfolders, 2 image files"
     * @param isDirectory - true if file is directory or folder and false if file is not directory.
     */
    public SimpleFile(String path, String name, String indicatorText, boolean isDirectory){
        this.path = path;
        this.name = name;
        this.indicatorText = indicatorText;
        this.isDirectory = isDirectory;
    }
}
