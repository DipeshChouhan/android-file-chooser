package com.miniblocks.androidfileselector.file_selector;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miniblocks.androidfileselector.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private SelectorCallbacks mCallbacks;
    private ArrayList<SimpleFile> simpleFiles = new ArrayList<>();
    public RecyclerViewAdapter(SelectorCallbacks callbacks){
        mCallbacks = callbacks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom, parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SimpleFile simpleFile = simpleFiles.get(position);
        holder.name.setText(simpleFile.name);
        final boolean isDirectory = simpleFile.isDirectory;
        if(isDirectory){
            holder.image.setImageResource(R.drawable.ic_folder_black_24dp);
        }else{
            //Todo - load image with glide.
            Glide.with(holder.itemView).load(simpleFile.path).error(R.drawable.ic_insert_drive_file_black_24dp).into(holder.image);
        }
        if(simpleFile.indicatorText.isEmpty()){
            holder.indicator.setVisibility(View.GONE);
        }else{
            holder.indicator.setVisibility(View.VISIBLE);
            holder.indicator.setText(simpleFile.indicatorText);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDirectory) mCallbacks.selectedFolderPath(simpleFile.path, simpleFile.name);
                else{
                    mCallbacks.selectedFilesPath(simpleFile.path);
                }
            }
        });
    }



    @Override
    public int getItemCount() {
        return simpleFiles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView name;
        private TextView indicator;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            indicator = itemView.findViewById(R.id.folderIndicator);

        }
    }

    public void setSimpleFiles(ArrayList<SimpleFile> files){
        simpleFiles = files;
        notifyDataSetChanged();
    }

}
