package com.miniblocks.androidfileselector;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<File> files = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View recyclerView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,
                parent, false);

        return new ViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File file  = files.get(position);
        if(file.isDirectory()){
            System.out.println(file.getName());
            holder.fileImage.setImageResource(R.drawable.ic_folder_black_24dp);
        }else{
            holder.fileImage.setImageResource(R.drawable.ic_insert_drive_file_black_24dp);
        }
        holder.fileName.setText(file.getName());
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

    public void setItems(ArrayList<File> files){
        this.files = files;
        notifyDataSetChanged();
    }
}
