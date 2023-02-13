package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MybasketAdapter extends RecyclerView.Adapter<BasketViewHolder> {
    private ArrayList<Mybasket> mybasket_data = new ArrayList<>();
    private int selectedposition = -1;
    private int oldposition = -1;

    public MybasketAdapter(ArrayList<Mybasket> mybasket_data) {
        this.mybasket_data = mybasket_data;
    }

    //뷰홀더를 생성(레이아웃 생성)
    @NonNull
    @Override
    public BasketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.basket_item_list, parent, false);
        final BasketViewHolder viewholder = new BasketViewHolder(context, view);

        return viewholder;
    }

    //뷰홀더가 재활용될 때 실행되는 함수
    @Override
    public void onBindViewHolder(@NonNull BasketViewHolder holder, final int position) {
        Mybasket item = mybasket_data.get(position);
        holder.ingredient_image.setImageResource(R.drawable.carrot);
        holder.ingredient_title.setText(item.getIngredient());
        holder.ingredient_count.setText(String.valueOf(item.getIngredient_count()));
        holder.ingredient_unit.setText(item.getIngredient_unit());
        holder.ingredient_category.setText(item.getIngredient_category());
        holder.expiration.setText(item.getExpiration_date());
        holder.deadline.setText(String.valueOf(item.getDeadline_date()));
        holder.itemView.setSelected(true);

        if (item.isSelected()) {
            holder.itemView.setBackgroundColor(Color.parseColor("#F7F8E0"));
        }
        else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("checkk", String.valueOf(position));
                setMultipleSelection(position);
            }
        });
    }

    private void setMultipleSelection(int adapterPosition) {
        if (mybasket_data.get(adapterPosition).isSelected()) {
            mybasket_data.get(adapterPosition).setSelected(false);
        } else {
            mybasket_data.get(adapterPosition).setSelected(true);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mybasket_data.size();
    }

    //Mybasket 데이터 추가
    public void addItem(Mybasket mybasket){
        mybasket_data.add(mybasket);
    }

    //Mybasket 데이터 삭제
    public void removeItem(int position) {
        mybasket_data.remove(position);
    }

}
