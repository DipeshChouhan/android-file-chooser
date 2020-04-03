package com.miniblocks.androidfileselector.file_selector;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.miniblocks.androidfileselector.file_selector.util.FileUtil;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

/**@author Dipesh Chouhan
 * Implements Builder pattern for developer convenience.
 * Note:- The FileSelector does not support configuration changes.
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

    private boolean isShowHiddenFiles;

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
        isShowHiddenFiles = fileBuilder.isShowHiddenFiles;
    }




    /**
     * A simple builder class to build
     */
    public static class FileBuilder{
        private FragmentManager fragmentManager;
        private int containerId;
        private FileCallback fileCallback;
        private String fileTypeName;
        private String[] fileExtensions = new String[0];
        private String folderToOpenName;
        private String initialTitle = "Directories";
        private boolean isShowFolderIndicators = true;
        private String indicatorSubFoldersText = "subfolders";
        private String indicatorFilesText = "files";
        private String indicatorEmptyFolderText = "Directory Empty";
        private String defaultInternalStorageName = "Internal Storage";
        private boolean isShowHiddenFiles = false;
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
        public FileBuilder addCallback(FileCallback callback){
            fileCallback = callback;
            return this;
        }

        /**
         *
         * @param fileTypeName - name of your files type ex:- "media" files, "image" files etc.
         * @param fileExtensions - to filter files ex:- ".png", ".jpg", ".mp3", ".mp4", ".txt" etc.
         */
        public FileBuilder setFileType(String fileTypeName, String ...fileExtensions){
            this.fileTypeName = fileTypeName;
            this.fileExtensions = fileExtensions;
            return this;
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
        public FileBuilder setFolderIndicatorText(String filesText, String subFolderText,
                                           String emptyFolderText){
            indicatorSubFoldersText = subFolderText;
            indicatorEmptyFolderText = emptyFolderText;
            indicatorFilesText = filesText;
            return this;
        }
        /**
         *
         * @param title - is displayed as toolbar title when first open with default settings.
         */
        public FileBuilder setInitialTitle(String title){
            initialTitle = title;
            return this;
        }

        /**
         * Sets the internal storage name when first open ex:- Internal Storage
         * @param name - of internal storage you want to display when first open.
         */
        public FileBuilder setInternalStorageName(String name){
            defaultInternalStorageName = name;
            return this;
        }

        /**
         *
         * @param folderName - name of the folder to open. ex:- 'Downloads' etc.
         */
        public FileBuilder folderToOpen(String folderName){
            folderToOpenName = folderName;
            return this;
        }

        /**
         *
         * @param showOrNot - True for show indicators or False for not show indicators. Default
         *                  is true.
         */
        public FileBuilder showFolderIndicators(boolean showOrNot){
            isShowFolderIndicators = showOrNot;
            return this;
        }

        /**
         *
         * call if you want to show hidden directories also.
         */
        public FileBuilder showHiddenFiles(){
            isShowHiddenFiles = true;
            return this;
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
     * Todo - remove both try-catch blocks
     * Opens the fragment with list of folders and files with given settings.
     */
    public void open(){

        if(mFolderToOpenName == null){
            try{
                defaultStart();
            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            try{
                String folderToOpenPath = getFolderPath(new File(FileUtil.getInternalDirectoryPath()));
                if(folderToOpenPath == null){
                    if(mFileCallback != null){
                        mFileCallback.selectedFiles("No such directory with given name found.", "null");
                    }


                }

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }

    /**
     * Open the fragment and add it to backStack also.
     * @param fragment -  to open.
     */
    private Fragment previousFragment;
    private void openFragment(Fragment fragment){
        try{
            mFragmentManager.beginTransaction().
                    add(mContainerId, fragment)
                    .commitAllowingStateLoss();
        }catch (Exception e){
            System.out.println("commit not allow");
        }


    }

    /**
     * A default start when folder to open is not given.
     */
    private void defaultStart(){
        ArrayList<SimpleFile> files = new ArrayList<>();
        FileView fileView = new FileView();
        String internalStoragePath = FileUtil.getInternalDirectoryPath();
        String sdCardStoragePath = FileUtil.getSDcardDirectoryPath();
        files.add(new SimpleFile(internalStoragePath, mDefaultInternalStorageName,
                "", true));
        if(sdCardStoragePath != null){
            File sdCardStorageFile = new File(sdCardStoragePath);
            files.add(new SimpleFile(sdCardStoragePath, sdCardStorageFile.getName(),
                    "", true));
        }
        fileView.config(this, files);
        fileView.setToolbarTitleText(mInitialTitle);
        openFragment(fileView);


    }

    /**
     * It simply open new fragment with new list of files and folders in given settings.
     * @param path -of selected folder
     */
    @Override
    protected void selectedFolderPath(String path, String name) {

        ArrayList<SimpleFile> files = getSimpleFilesList(path);
        FileView fileView = new FileView();

        fileView.config(this, files);
        fileView.setToolbarTitleText(name);
        fileView.setToolbarSubTitleText(path);

        openFragment(fileView);

    }

    /**
     *
     * @param paths - of selected files.
     */
    @Override
    protected void selectedFilesPath(String... paths) {
        if(mFileCallback != null){
            mFileCallback.selectedFiles("", paths);
        }

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
                        String filesIndicatorText =
                                mFileTypeName == null ? filesCount+" "+mIndicatorFilesText :
                                        filesCount+" "+mFileTypeName+" "+mIndicatorFilesText;
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
            boolean query = !isShowHiddenFiles ?
                    pathname.isDirectory() && !pathname.getName().startsWith("."):
                    pathname.isDirectory();
            if(query || mFileExtensions.length == 0){
                return true;
            }else{
                for(String extension: mFileExtensions){
                    if(pathname.getName().endsWith(extension)) return true;
                }
            }
            return false;
        }
    };

    /**
     * A simple file filter which filter only directory.
     */
    private FileFilter directoryFilter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {

            return !isShowHiddenFiles ?
                    pathname.isDirectory() && !pathname.getName().startsWith("."):
                    pathname.isDirectory();
        }
    };

    /**
     *
     * @param file - to start searching for folder path with.
     * @return - folderPath if it matches else null.
     */
    private String getFolderPath(File file){
        if(file.getName().equals(mFolderToOpenName)){
            return file.getAbsolutePath();
        }
        File[] files = file.listFiles(directoryFilter);
        if(files != null){
            for(File f: files){
                return getFolderPath(f);
            }
        }
        return null;
    }




}
