package com.miniblocks.androidfileselector.file_selector;

/**
 * A class for communicating between FileSelector and RecyclerViewAdapter.
 */
abstract class SelectorCallbacks {
    abstract protected void selectedFolderPath(String path);
    abstract protected void selectedFilesPath(String...paths);
}
