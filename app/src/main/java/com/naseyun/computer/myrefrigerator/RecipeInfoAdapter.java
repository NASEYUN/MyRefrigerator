package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeInfoAdapter extends RecyclerView.Adapter<RecipeInfoViewHolder> {
    private ArrayList<Recipe> recipe_info_arrayList;

    public RecipeInfoAdapter() {
        recipe_info_arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecipeInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recipe_info_item_list, parent, false);
        RecipeInfoViewHolder recipeinfoviewholder = new RecipeInfoViewHolder(context, view);
        return recipeinfoviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeInfoViewHolder holder, int position) {
        Recipe item = recipe_info_arrayList.get(position);
        holder.recipe_info_imageview.setImageResource(R.drawable.kimchi);
        holder.recipe_info_title.setText(item.getFood_title());
        holder.recipe_info_serving.setText(item.getFood_serving());
        holder.recipe_info_time.setText(item.getFood_time());
    }

    @Override
    public int getItemCount() {
        return recipe_info_arrayList.size();
    }

    //Mybasket 데이터 추가
    public void addItem(Recipe recipe){
        recipe_info_arrayList.add(recipe);
    }
}
