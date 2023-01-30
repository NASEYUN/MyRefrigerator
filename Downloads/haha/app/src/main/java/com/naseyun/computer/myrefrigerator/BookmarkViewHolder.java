package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookmarkViewHolder extends RecyclerView.ViewHolder {
    public ImageView recipe_imageview;
    public TextView recipe_title;
    public ImageButton recipe_bookmark;
    public TextView recipe_serving;
    public TextView recipe_time;

    public BookmarkViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        recipe_imageview = itemView.findViewById(R.id.bookmark_recipe_imageview);
        recipe_title = itemView.findViewById(R.id.bookmark_recipe_title);
        recipe_bookmark = itemView.findViewById(R.id.bookmark_recipe_button);
        recipe_serving = itemView.findViewById(R.id.bookmark_recipe_serving);
        recipe_time = itemView.findViewById(R.id.bookmark_recipe_time);
    }
}
