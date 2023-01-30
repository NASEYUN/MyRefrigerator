package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MybasketAdapter extends RecyclerView.Adapter<BasketViewHolder> {
    private ArrayList<Mybasket> arrayList;

    public MybasketAdapter() {
        arrayList = new ArrayList<>();
    }

    //뷰홀더를 생성(레이아웃 생성)
    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.basket_item_list, parent, false);
        BasketViewHolder viewholder = new BasketViewHolder(context, view);
        return viewholder;
    }

    //뷰홀더가 재활용될 때 실행되는 함수
    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, int position) {
        Mybasket item = arrayList.get(position);
        holder.ingredient.setText(item.getIngredient());
        holder.expiration.setText(item.getExpiration_date());
        holder.deadline.setText(String.valueOf(item.getDeadline_date()));
        Log.v("deadline", String.valueOf(item.getDeadline_date()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    //Mybasket 데이터 추가
    public void addItem(Mybasket mybasket){
        arrayList.add(mybasket);
    }
}
