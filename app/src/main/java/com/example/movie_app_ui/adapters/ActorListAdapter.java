package com.example.movie_app_ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie_app_ui.R;

import java.util.List;

public class ActorListAdapter extends RecyclerView.Adapter<ActorListAdapter.ActorListViewHolder> {
    List<String> images;
    Context context;

    public ActorListAdapter(List<String> images) {
        this.images = images;
    }
    @NonNull
    @Override
    public ActorListAdapter.ActorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_viewholder, parent, false);
        return new ActorListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ActorListAdapter.ActorListViewHolder holder, int position) {
        Glide.with(context)
                .load(images.get(position))
                .into(holder.actorImg);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ActorListViewHolder extends RecyclerView.ViewHolder {
        ImageView actorImg;
        public ActorListViewHolder(@NonNull View itemView) {
            super(itemView);
            actorImg = itemView.findViewById(R.id.itemActorImg);
        }
    }
}
