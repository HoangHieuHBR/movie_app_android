package com.example.movie_app_ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_app_ui.R;
import com.example.movie_app_ui.models.CategoryItem;

import java.util.ArrayList;
import java.util.List;

public class DetailMovieCategoryAdapter extends RecyclerView.Adapter<DetailMovieCategoryAdapter.CategoryListHolder> {
    List<String> items;
    Context context;

    public DetailMovieCategoryAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public DetailMovieCategoryAdapter.CategoryListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_viewholder, parent, false);
        return new CategoryListHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailMovieCategoryAdapter.CategoryListHolder holder, int position) {
        holder.titleTxt.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class CategoryListHolder extends RecyclerView.ViewHolder {
        TextView titleTxt;

        public CategoryListHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleText);
        }
    }
}
