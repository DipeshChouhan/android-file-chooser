package com.miniblocks.androidfileselector.file_selector;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.miniblocks.androidfileselector.file_selector.util.FileUtil;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**@author Dipesh Chouhan
 * Implements Builder pattern for developer convenience.
 */
public class FileSelector extends SelectorCallbacks {
    private FragmentManager mFragmentManager;
    private int mContainerId;
    private FileCallback mFileCallback;
    private String mFileTypeName;
    private String[] mFileExtensions;
    private String mFolderToOpenName;
    private String mInitialTitle;
    private boolean isShowFolderIndicators;
    private String mIndicatorSubFoldersText;
    private String mIndicatorFilesText;
    private String mIndicatorEmptyFolderText;
    private String mDefaultInternalStorageName;
    public static final String NO_ERROR = "";
    /**
     * Initializing all our variables with values provided by FileBuilder object
     * @param fileBuilder - FileBuilder object
     */
    private FileSelector(FileBuilder fileBuilder){
        mFragmentManager = fileBuilder.fragmentManager;
        mContainerId = fileBuilder.containerId;
        mFileCallback = fileBuilder.fileCallback;
        mFileTypeName = fileBuilder.fileTypeName;
        mFileExtensions = fileBuilder.fileExtensions;
        mFolderToOpenName = fileBuilder.folderToOpenName;
        mInitialTitle = fileBuilder.initialTitle;
        isShowFolderIndicators = fileBuilder.isShowFolderIndicators;
        mIndicatorSubFoldersText = fileBuilder.indicatorSubFoldersText;
        mIndicatorFilesText = fileBuilder.indicatorFilesText;
        mIndicatorEmptyFolderText = fileBuilder.indicatorEmptyFolderText;
        mDefaultInternalStorageName = fileBuilder.defaultInternalStorageName;
    }




    /**
     * A simple builder class to build
     */
    public static class FileBuilder{
        private FragmentManager fragmentManager;
        private int containerId;
        private FileCallback fileCallback;
        private String fileTypeName;
        private String[] fileExtensions;
        private String folderToOpenName;
        private String initialTitle = "Directories";
        private boolean isShowFolderIndicators = true;
        private String indicatorSubFoldersText = "subfolders";
        private String indicatorFilesText = "files";
        private String indicatorEmptyFolderText = "Directory Empty";
        private String defaultInternalStorageName = "Internal Storage";
        /**
         *
         * @param fragmentManager - FragmentManager object which provided by developer via parent
         *                       class with method.
         * @param containerId - fragment id with which to replace the FileView fragment.
         */
        private FileBuilder(FragmentManager fragmentManager, int containerId){
            this.fragmentManager = fragmentManager;
            this.containerId = containerId;

        }

        /**
         *
         * @param callback - it called when files get selected.
         */
        public void addCallback(FileCallback callback){
            fileCallback = callback;
        }

        /**
         *
         * @param fileTypeName - name of your files type ex:- "media" files, "image" files etc.
         * @param fileExtensions - to filter files ex:- ".png", ".jpg", ".mp3", ".mp4", ".txt" etc.
         */
        public void setFileType(String fileTypeName, String ...fileExtensions){
            this.fileTypeName = fileTypeName;
            this.fileExtensions = fileExtensions;
        }

        /**
         * Folder indicators shows below folder name and show folder properties. ex:-
         * "3 {subFolderText}, 2 {fileTypeName} {filesText}",
         * (if folder is empty then- "{emptyFolderText}"
         * @param filesText - {fileType} post text. Default is "files"
         * @param subFolderText - indicating number of sub folders present in folder. Default is
         *                      "n(number of sub folders) subfolders.
         * @param emptyFolderText - indicating the folder is empty. Default is "Directory is empty".
         */
        public void setFolderIndicatorText(String filesText, String subFolderText,
                                           String emptyFolderText){
            indicatorSubFoldersText = subFolderText;
            indicatorEmptyFolderText = emptyFolderText;
            indicatorFilesText = filesText;
        }
        /**
         *
         * @param title - is displayed as toolbar title when first open with default settings.
         */
        public void setInitialTitle(String title){
            initialTitle = title;
        }

        /**
         * Sets the internal storage name when first open ex:- Internal Storage
         * @param name - of internal storage you want to display when first open.
         */
        public void setInternalStorageName(String name){
            defaultInternalStorageName = name;
        }

        /**
         *
         * @param folderName - name of the folder to open. ex:- 'Downloads' etc.
         */
        public void folderToOpen(String folderName){
            folderToOpenName = folderName;
        }

        /**
         *
         * @param showOrNot - True for show indicators or False for not show indicators. Default
         *                  is true.
         */
        public void showFolderIndicators(boolean showOrNot){
            isShowFolderIndicators = showOrNot;
        }
        /**
         *
         * @return {FileSelector} - A FileSelector object argument "this" passed.
         */
        public FileSelector build(){
            return new FileSelector(this);
        }
    }

    /**
     *
     * @param fragmentManager - FragmentManager passed by developer ex:-
     *                        getSupportFragmentManager().
     * @param containerId - fragment or container id with which to replace FileView fragment ex:-
     *                   R.id.containerID.
     * @return {FileBuilder} - object from which "this" object is build
     */
    public static FileBuilder with(FragmentManager fragmentManager, int containerId){
        return new FileBuilder(fragmentManager, containerId);
    }

    /**
     *In this method a default container id is passed as argument "android.R.id.content".
     * @param fragmentManager - FragmentManager passed by developer ex:-
     *                        getSupportFragmentManager().
     * @return {FileBuilder} - object from which "this" object is build
     */

    public static FileBuilder with(FragmentManager fragmentManager){
        return new FileBuilder(fragmentManager, android.R.id.content);
    }


    /**
     * Opens the fragment with list of folders and files with given settings.
     */
    public void open(){
        if(mFolderToOpenName == null){
            defaultStart();
        }
    }

    /**
     * Open the fragment and add it to backStack also.
     * @param fragment -  to open.
     */
    private void openFragment(Fragment fragment){
        mFragmentManager.beginTransaction().addToBackStack(fragment.getTag()).add(mContainerId,
                fragment).commit();
    }

    /**
     * A default start when folder to open is not given.
     */
    private void defaultStart(){
        ArrayList<SimpleFile> files = new ArrayList<>();
        FileView fileView = new FileView(this);
        fileView.setToolbarTitleText(mInitialTitle);
        String internalStoragePath = FileUtil.getInternalDirectoryPath();
        String sdCardStoragePath = FileUtil.getSDcardDirectoryPath();
        files.add(new SimpleFile(internalStoragePath, mDefaultInternalStorageName,
                "", true));
        if(sdCardStoragePath != null){
            File sdCardStorageFile = new File(sdCardStoragePath);
            files.add(new SimpleFile(sdCardStoragePath, sdCardStorageFile.getName(),
                    "", true));
        }
        fileView.getAdapter().setSimpleFiles(files);
        openFragment(fileView);
    }

    /**
     *
     * @param path -of selected folder
     */
    @Override
    protected void selectedFolderPath(String path) {

    }

    /**
     *
     * @param paths - of selected files.
     */
    @Override
    protected void selectedFilesPath(String... paths) {

    }

    /**
     * It gives list of all filtered files with given settings.
     * @param path - of folder which is clicked
     * @return {ArrayList<SimpleFile} - which shows to user in next fragment.
     */
    private ArrayList<SimpleFile> getSimpleFilesList(String path){
        ArrayList<SimpleFile> filesList = new ArrayList<>();    // array list to return.
        File[] files = new File(path).listFiles(fileFilter);    // a filtered files array.
        // check for files. If null return a null list.
        if(files == null){
            return null;
        }
        // traversing each file in files array.
        for(File file: files){
            SimpleFile simpleFile = new SimpleFile();
            boolean isDirectory = file.isDirectory();
            // if file is directory then only we do stuff for folder indicators
            if(isDirectory){
                if(isShowFolderIndicators){
                    String folderIndicatorText = mIndicatorEmptyFolderText;
                    int subFolderCount = 0;
                    int filesCount = 0;
                    // now traversing each file in this directory.
                    for(File inFile: file.listFiles(fileFilter)){
                        // if inFile is directory then we increment subFolderCount.
                        if(inFile.isDirectory()) subFolderCount++;
                        // else we increment filesCount.
                        else{
                            filesCount++;
                        }
                    }

                    /*
                     * Here all just constructing folder indicator text.
                     */
                    boolean areSubFolders = false;

                    if(subFolderCount > 0) {
                        folderIndicatorText = subFolderCount + " " + mIndicatorSubFoldersText;
                        areSubFolders = true;
                    }
                    if(filesCount > 0){
                        String filesIndicatorText = filesCount+" "+mIndicatorFilesText;
                        folderIndicatorText = areSubFolders ?
                                folderIndicatorText + ", " + filesIndicatorText: filesIndicatorText;
                    }
                    simpleFile.indicatorText = folderIndicatorText;

                }
            }
            // initializing simpleFile.
            simpleFile.isDirectory = isDirectory;
            simpleFile.path = file.getAbsolutePath();
            simpleFile.name = file.getName();
            // adding simpleFile to our filesList
            filesList.add(simpleFile);
        }

        return filesList;
    }

    /**
     * A simple file filter which filters all files ending with given fileExtensions but exclude
     * a directory. If developer have not provide extensions then it return true.
     */
    private FileFilter fileFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            if(pathname.isDirectory() || mFileExtensions.length == 0){
                return true;
            }else{
                for(String extension: mFileExtensions){
                    if(pathname.getName().endsWith(extension)) return true;
                }
            }
            return false;
        }
    };

}
