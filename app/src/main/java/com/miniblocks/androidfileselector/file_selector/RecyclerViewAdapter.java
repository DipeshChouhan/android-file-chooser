package com.miniblocks.androidfileselector.file_selector;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miniblocks.androidfileselector.R;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<SimpleFile> files = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,
                parent, false);

        return new ViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SimpleFile file  = files.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClicked(file);
            }
        });
        if(file.isDirectory()){
            holder.fileImage.setImageResource(R.drawable.ic_folder_black_24dp);
        }else{
            holder.fileImage.setImageResource(R.drawable.ic_insert_drive_file_black_24dp);
        }
        holder.fileName.setText(file.name());

    }

    private void itemClicked(SimpleFile file){
        if(file.isDirectory()){
            setItems(listAllFiles(file.path()));
        }
    }

    private ArrayList<SimpleFile> listAllFiles(String directoryPath){
        File[] files = new File(directoryPath).listFiles();
        ArrayList<SimpleFile> filesList = new ArrayList<>();
        for(File file : files){
            filesList.add(new SimpleFile(file));
        }

        return filesList;
    }

    @Override
    public int getItemCount() {
        return files.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView fileImage;
        public TextView fileName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fileImage = itemView.findViewById(R.id.image);
            fileName = itemView.findViewById(R.id.file_name);
        }
    }

    public void setItems(ArrayList<SimpleFile> files){
        this.files = files;
        notifyDataSetChanged();
    }
}
