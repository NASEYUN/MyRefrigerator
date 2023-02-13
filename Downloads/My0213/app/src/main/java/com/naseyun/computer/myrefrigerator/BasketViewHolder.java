package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BasketViewHolder extends RecyclerView.ViewHolder {
    public ImageView ingredient_image;
    public TextView ingredient_title;
    public TextView ingredient_count;
    public TextView ingredient_unit;
    public TextView ingredient_category;
    public TextView expiration;
    public TextView deadline;
    public MybasketAdapter mybasketAdapter;

    public BasketViewHolder(Context context, @NonNull final View itemView) {
        super(itemView);
        ingredient_image = itemView.findViewById(R.id.ingredient_image);
        ingredient_title = itemView.findViewById(R.id.ingredient_title);
        ingredient_count = itemView.findViewById(R.id.ingredient_count);
        ingredient_unit = itemView.findViewById(R.id.ingredient_unit);
        ingredient_category = itemView.findViewById(R.id.ingredient_category);
        expiration = itemView.findViewById(R.id.expiration_date);
        deadline = itemView.findViewById(R.id.deadline_date);
    }
}
