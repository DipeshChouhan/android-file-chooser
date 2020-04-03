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

    private String toolbarTitleText;
    private String toolbarSubTitleText;
    private static SelectorCallbacks selectorCallbacks;
    private ArrayList<SimpleFile> simpleFiles;
    private RecyclerViewAdapter adapter;
    private Toolbar toolbar;
    private boolean stateSaved = false;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("activity created");
        if (savedInstanceState != null) {
            toolbarTitleText = savedInstanceState.getString("titleText");
            toolbarSubTitleText = savedInstanceState.getString("subTitleText");
            int size = savedInstanceState.getInt("listSize");
            simpleFiles = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                simpleFiles.add((SimpleFile) savedInstanceState.getParcelable(String.valueOf(i)));
            }
            System.out.println(simpleFiles.size());
            stateSaved = true;
        }else{
            System.out.println("null");
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("titleText", toolbarTitleText);
        outState.putString("subTitleText", toolbarSubTitleText);

            outState.putInt("listSize", simpleFiles.size());

            for(int i =0; i<simpleFiles.size(); i++){
                SimpleFile simpleFile = simpleFiles.get(i);
                outState.putParcelable(String.valueOf(i), new SimpleFile(
                        simpleFile.path, simpleFile.name, simpleFile.indicatorText, simpleFile.isDirectory
                ));
            }


        System.out.println("savestate ");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        toolbar = view.findViewById(R.id.toolbar_id);
        System.out.println("view");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClicked();
            }
        });
        adapter = new RecyclerViewAdapter(selectorCallbacks, simpleFiles);
        recyclerView.setAdapter(adapter);
        toolbar.setTitle(toolbarTitleText);
        if(toolbarSubTitleText != null) toolbar.setSubtitle(toolbarSubTitleText);
        return view;
    }

    private void onBackButtonClicked(){
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();


    }


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

    public void config(SelectorCallbacks callbacks, ArrayList<SimpleFile> listOfFiles){
        selectorCallbacks = callbacks;
        simpleFiles = listOfFiles;
    }

    @Override
    public void onResume() {

        super.onResume();

        if(stateSaved){
            adapter.setSimpleFiles(simpleFiles);
            toolbar.setTitle(toolbarTitleText);
            if(toolbarSubTitleText != null){
                toolbar.setSubtitle(toolbarSubTitleText);
            }
        }
    }

    @Override
    public String toString() {
        return toolbarTitleText;
    }
}
