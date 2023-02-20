package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookmarkViewHolder extends RecyclerView.ViewHolder {
    public ImageView bookmark_recipe_imageview;
    public TextView bookmark_recipe_title;
    public ImageButton bookmark_recipe_button;
    public TextView bookmark_recipe_serving;
    public TextView bookmark_recipe_time;

    public BookmarkViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        bookmark_recipe_imageview = itemView.findViewById(R.id.bookmark_recipe_imageview);
        bookmark_recipe_title = itemView.findViewById(R.id.bookmark_recipe_title);
        bookmark_recipe_button = itemView.findViewById(R.id.bookmark_recipe_button);
        bookmark_recipe_serving = itemView.findViewById(R.id.bookmark_recipe_serving);
        bookmark_recipe_time = itemView.findViewById(R.id.bookmark_recipe_time);
    }
}
