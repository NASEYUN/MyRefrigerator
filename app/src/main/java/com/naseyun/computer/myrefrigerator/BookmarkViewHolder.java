package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookmarkViewHolder extends RecyclerView.ViewHolder {
    public ImageView bookmark_imageview;
    public TextView bookmark_title;
    public ImageButton bookmark_bookmark;
    public TextView bookmark_serving;
    public TextView bookmark_time;

    public BookmarkViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        bookmark_imageview = itemView.findViewById(R.id.bookmark_recipe_imageview);
        bookmark_title = itemView.findViewById(R.id.bookmark_recipe_title);
        bookmark_bookmark = itemView.findViewById(R.id.bookmark_button);
        bookmark_serving = itemView.findViewById(R.id.bookmark_recipe_serving);
        bookmark_time = itemView.findViewById(R.id.bookmark_recipe_time);
    }
}
