package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BasketViewHolder extends RecyclerView.ViewHolder {
    public TextView ingredient;
    public TextView expiration;
    public TextView deadline;

    public BasketViewHolder(Context context, @NonNull View itemView) {
        super(itemView);
        ingredient = itemView.findViewById(R.id.recipe_title);
        expiration = itemView.findViewById(R.id.expiration_date);
        deadline = itemView.findViewById(R.id.basket_underline);
    }
}
