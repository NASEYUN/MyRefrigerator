package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeViewHolder extends RecyclerView.ViewHolder {
    public ImageView recipe_image;
    public TextView recipe_title;
    public ImageButton recipe_bookmark;
    public TextView recipe_serving;
    public TextView recipe_time;

    public RecipeViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        recipe_image = itemView.findViewById(R.id.recipe_imageview);
        recipe_title = itemView.findViewById(R.id.recipe_title);
        recipe_bookmark = itemView.findViewById(R.id.bookmark_button);
        recipe_serving = itemView.findViewById(R.id.recipe_serving);
        recipe_time = itemView.findViewById(R.id.recipe_time);
    }
}
