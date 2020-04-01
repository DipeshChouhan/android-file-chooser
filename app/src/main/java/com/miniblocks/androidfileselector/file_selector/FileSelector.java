package com.miniblocks.androidfileselector.file_selector;

import androidx.fragment.app.FragmentManager;

/**@author Dipesh Chouhan
 * Implements Builder pattern for developer convenience.
 */
public class FileSelector extends SelectorCallbacks {
    private FragmentManager mFragmentManager;
    private int mContainerId;

    /**
     * Initializing all our variables with values provided by FileBuilder object
     * @param fileBuilder - FileBuilder object
     */
    private FileSelector(FileBuilder fileBuilder){
        mFragmentManager = fileBuilder.fragmentManager;
        mContainerId = fileBuilder.containerId;
    }


    /**
     * A simple builder class to build
     */
    public static class FileBuilder{
        private FragmentManager fragmentManager;
        private int containerId;

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
        // opens the fragment
    }


}
