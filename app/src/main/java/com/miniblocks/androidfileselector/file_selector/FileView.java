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
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miniblocks.androidfileselector.R;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Dipesh Chouhan
 * Fragment class. It shows list of folders and files.
 */
public class FileView extends Fragment {
    // Toolbar
    private Toolbar toolbar;
    // RecyclerView
    private RecyclerViewAdapter adapter;
    private ArrayList<SimpleFile> files = new ArrayList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FileUtil util = new FileUtil();
        File file = new File(util.getInternalDirectoryPath());
        files.add(new SimpleFile(file, "Internal Storage"));

        String SDcardPath = util.getSDcardDirectoryPath();
        if(SDcardPath != null){
            files.add(new SimpleFile(new File(SDcardPath)));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view, container, false);
        init(view);
        return view;
    }


    /**
     * @param view - our inflated layout for this fragment.
     */
    private void init(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_id);
        toolbar = view.findViewById(R.id.toolbar_id);


        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity != null){
            activity.setSupportActionBar(toolbar);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(true);

            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
        adapter = new RecyclerViewAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setItems(files);
    }


    /**
     * removes itself
     */
    private void onBackPressed(){
        getFragmentManager().beginTransaction().remove(this).commit();
    }

    public RecyclerViewAdapter getAdapter(){
        return adapter;
    }



}
