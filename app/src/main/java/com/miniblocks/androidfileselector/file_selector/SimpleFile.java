package com.miniblocks.androidfileselector.file_selector;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A simple wrapper class for files
 */
class SimpleFile implements Parcelable {

    public String path;
    public String name;
    public String indicatorText = "";
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

    public SimpleFile(){

    }

    public SimpleFile(Parcel in){
        String[] data = new String[3];
        boolean[] bool = new boolean[1];
        in.readStringArray(data);
        path = data[0];
        name = data[1];
        indicatorText = data[2];
        in.readBooleanArray(bool);
        isDirectory = bool[0];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{path, name, indicatorText});
        dest.writeBooleanArray(new boolean[]{isDirectory});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Object createFromParcel(Parcel source) {
            return new SimpleFile(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new SimpleFile[size];
        }
    };
}
