package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
    private ArrayList<Recipe> recipe_arrayList;

    public RecipeAdapter() {
        recipe_arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.recipe_item_list, parent, false);
        RecipeViewHolder viewholder = new RecipeViewHolder(context, view);
        return viewholder;
    }

    //뷰홀더와 recyclerview 연결
    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe item = recipe_arrayList.get(position);
        Uri imageUri = item.getFood_image();
        holder.recipe_image.setImageURI(item.getFood_image());
        holder.recipe_title.setText(item.getFood_title());
        holder.recipe_serving.setText(item.getFood_serving());
        holder.recipe_time.setText(item.getFood_time());
    }

    @Override
    public int getItemCount() {
        return recipe_arrayList.size();
    }

    //Recipe 데이터 추가
    public void addItem(Recipe recipe){
        recipe_arrayList.add(recipe);
    }
}
