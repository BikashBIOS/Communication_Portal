package com.example.forms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewerHolder> {

    private Context mContext;
    private List<Upload> mUpload;

    public ImageAdapter(Context context,List<Upload> uploads)
    {
        mContext=context;
        mUpload=uploads;
    }
    @NonNull
    @Override
    public ImageViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        return new ImageViewerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewerHolder holder, int position) {

        Upload uploadCurrent=mUpload.get(position);
        holder.textViewName.setText(uploadCurrent.getmName());
        Picasso.get().load(uploadCurrent.getmImageUri()).fit().centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImageViewerHolder extends RecyclerView.ViewHolder
    {
        public TextView textViewName;
        public ImageView imageView;
        public ImageViewerHolder(@NonNull View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.text_view_name);
            imageView=itemView.findViewById(R.id.image_view_upload);
        }
    }
}
