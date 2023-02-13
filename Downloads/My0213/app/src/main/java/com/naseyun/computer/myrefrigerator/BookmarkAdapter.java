package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkViewHolder> {
    private ArrayList<Recipe> bookmark_arrayList;

    public BookmarkAdapter() {
        bookmark_arrayList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.bookmark_item_list, parent, false);
        BookmarkViewHolder bookmarkviewholder = new BookmarkViewHolder(context, view);
        return bookmarkviewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkViewHolder holder, int position) {
        Recipe item = bookmark_arrayList.get(position);
        holder.recipe_imageview.setImageURI(item.getFood_image());
        holder.recipe_title.setText(item.getFood_title());
        holder.recipe_serving.setText(item.getFood_serving());
        holder.recipe_time.setText(item.getFood_time());
    }

    @Override
    public int getItemCount() {
        return bookmark_arrayList.size();
    }

    //Mybasket 데이터 추가
    public void addItem(Recipe recipe){
        bookmark_arrayList.add(recipe);
    }
}
