package com.miniblocks.androidfileselector.file_selector;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miniblocks.androidfileselector.R;

import java.util.ArrayList;

/**
 * @author Dipesh Chouhan
 * Fragment class. It shows list of folders and files.
 */
//Todo - fix configuration change bugs.
public class FileView extends Fragment {
    private static SelectorCallbacks selectorCallbacks;
    private static RecyclerViewAdapter recyclerViewAdapter;
    private static toolbarTitleText;
    private static String toolbarSubTitleText;

    public FileView(SelectorCallbacks callbacks){

        selectorCallbacks = callbacks;
        recyclerViewAdapter = new RecyclerViewAdapter(callbacks);
    }
    public FileView(){

    }
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
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);
        Toolbar toolbar = view.findViewById(R.id.toolbar_id);
        toolbar.setTitle(toolbarTitleText);
        if(toolbarSubTitleText != null){
            toolbar.setSubtitle(toolbarSubTitleText);
        }
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClicked();
            }
        });

        return view;
    }

    private void onBackButtonClicked(){
        getFragmentManager().beginTransaction().remove(this).commit();
    }

//    /**
//     *
//     * @return {RecyclerViewAdapter}
//     */
//    public RecyclerViewAdapter getAdapter(){
//        return recyclerViewAdapter;
//    }

    /**
     *
     * @param titleText - for toolbar title.
     */
    public void setToolbarTitleText(String titleText){
        toolbarTitleText = titleText;
    }

    /**
     *
     * @param subTitleText - for toolbar subtitle.
     */
    public void setToolbarSubTitleText(String subTitleText){
        toolbarSubTitleText = subTitleText;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    public RecyclerViewAdapter getAdapter(){
        return recyclerViewAdapter;
    }
}
