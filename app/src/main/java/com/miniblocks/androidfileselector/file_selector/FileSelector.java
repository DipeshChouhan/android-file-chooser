package com.miniblocks.androidfileselector.file_selector;

import androidx.fragment.app.FragmentManager;

import java.io.File;
import java.util.ArrayList;

/**@author Dipesh Chouhan
 * Implements Builder pattern for developer convenience.
 */
public class FileSelector {

    /**
     * A builder. To create instance of it you must call FileSelector overloaded with(...param)
     * methods.
     */
    public static class FileBuilder{

        private FragmentManager mFragmentManager;
        private int mContainer;
        private String[] filterExtensions;
        private String filterExtensionName;
        /**
         * @param fragmentManager - instance of FragmentManager.
         * @param container - id of fragment to replace ex:- R.id.fragmentContainer.
         */
        private FileBuilder(FragmentManager fragmentManager, int container){

            mFragmentManager = fragmentManager;
            mContainer = container;
        }


        public FileBuilder applyFilter(String fileTypeName, String ...extensionsToFilter){
            filterExtensions = extensionsToFilter;
            filterExtensionName = fileTypeName;
            return this;
        }


        /**
         * Opens the fragment containing list of files and folders.
         * Add the FileView fragment to BackStack also.
         */
        public void open(){
            mFragmentManager.beginTransaction().add(mContainer, new FileView()).addToBackStack(
                    "fileView").commit();
        }
    }

    /**
     *
     * @param fragmentManager - instance of FragmentManager class ex:- inside activity
     *                        (getSupportFragmentManager()).
     * @return {FileBuilder} instance of the inner static class.
     */
    public static FileBuilder with(FragmentManager fragmentManager){
        return new FileBuilder(fragmentManager, android.R.id.content);
    }

    /**
     *
     * @param fragmentManager - instance of FragmentManager class.
     * @param container - id of fragment or container which replaced with FileView fragment
     * @return {FileBuilder} instance of the inner Static class.
     */

    public static FileBuilder with(FragmentManager fragmentManager, int container){
        return new FileBuilder(fragmentManager, container);
    }

}
