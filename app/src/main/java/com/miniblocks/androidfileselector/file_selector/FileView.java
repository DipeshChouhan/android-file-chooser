package com.miniblocks.androidfileselector.file_selector;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.miniblocks.androidfileselector.R;

/**
 * @author Dipesh Chouhan
 * Fragment class. It shows list of folders and files.
 */
class FileView extends Fragment {
    Toolbar toolbar;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        toolbar = view.findViewById(R.id.toolbar_id);


        return view;
    }

    /**
     *
     * @return {RecyclerViewAdapter}
     */
    public RecyclerViewAdapter getAdapter(){
        return null;
    }





}
