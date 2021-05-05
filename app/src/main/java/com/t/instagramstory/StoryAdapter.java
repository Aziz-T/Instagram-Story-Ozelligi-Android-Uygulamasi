package com.t.instagramstory;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    List<StoryModel> storyModelList;

    public StoryAdapter(List<StoryModel> storyModelList) {
        this.storyModelList = storyModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(storyModelList.size()<2){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_add_item,parent,false);
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item,parent,false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(storyModelList.get(position).getBaslik(),position);
    }

    @Override
    public int getItemCount() {
        return storyModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView profilImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.baslik);
            profilImage=itemView.findViewById(R.id.circle);

        }

        public void setData(String baslik, int position) {

            textView.setText(baslik);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // if(position==1){
                    Intent intent = new Intent(itemView.getContext(), CameraActivity.class);
                    itemView.getContext().startActivity(intent);
                    // }
                }
            });

        }
    }
}
