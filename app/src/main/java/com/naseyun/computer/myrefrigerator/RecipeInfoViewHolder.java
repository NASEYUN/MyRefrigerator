package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecipeInfoViewHolder extends RecyclerView.ViewHolder {
    public ImageView recipe_info_imageview;
    public TextView recipe_info_title;
    public TextView recipe_info_serving;
    public TextView recipe_info_time;

    public RecipeInfoViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        recipe_info_imageview = itemView.findViewById(R.id.recipe_info_imageview);
        recipe_info_title = itemView.findViewById(R.id.recipe_info_title);
        recipe_info_serving = itemView.findViewById(R.id.recipe_info_serving);
        recipe_info_time = itemView.findViewById(R.id.recipe_info_time);
    }
}
