package com.example.movie_app_ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.movie_app_ui.R;
import com.example.movie_app_ui.activities.DetailActivity;
import com.example.movie_app_ui.models.MovieList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListHolder> {
    MovieList items;
    Context context;

    public MovieListAdapter(MovieList items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MovieListAdapter.MovieListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_viewholder, parent, false);
        return new MovieListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieListHolder holder, int position) {
        holder.titleTxt.setText(items.getData().get(position).getTitle());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions = requestOptions.transform(new CenterCrop(), new RoundedCorners(30));

        Glide.with(context)
                .load(items.getData().get(position).getPoster())
                .apply(requestOptions)
                .into(holder.movieImg);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            intent.putExtra("id", items.getData().get(position).getId());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.getData().size();
    }

    public class MovieListHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;
        ImageView movieImg;

        public MovieListHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            movieImg = itemView.findViewById(R.id.movieImg);
        }
    }
}
