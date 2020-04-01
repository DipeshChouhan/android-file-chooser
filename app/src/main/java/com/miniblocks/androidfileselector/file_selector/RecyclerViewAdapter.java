package com.miniblocks.androidfileselector.file_selector;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private SelectorCallbacks mCallbacks;
    private ArrayList<SimpleFile> simpleFiles = new ArrayList<>();
    public RecyclerViewAdapter(SelectorCallbacks callbacks){

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return simpleFiles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }

    public void setSimpleFiles(ArrayList<SimpleFile> files){
        simpleFiles = files;
        notifyDataSetChanged();
    }

}
