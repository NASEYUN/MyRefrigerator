package com.naseyun.computer.myrefrigerator;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MybasketAdapter extends RecyclerView.Adapter<BasketViewHolder> {
    private ArrayList<Mybasket> mybasket_data = new ArrayList<>();
    private HashSet<Integer> position_set = new HashSet<Integer>();
    private onListItemSelectedInterface mListener;

    public MybasketAdapter(ArrayList<Mybasket> mybasket_data, onListItemSelectedInterface listener) {
        this.mybasket_data = mybasket_data;
        this.mListener = listener;
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

        if (item.isSelected()) {
            holder.itemView.setBackgroundColor(Color.parseColor("#F7F8E0"));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mybasket_data.get(position).isSelected()) { //아이템뷰가 선택되어있는 경우, 선택 해제되도록
                    holder.itemView.setSelected(false);
                    mybasket_data.get(position).setSelected(false);
                    mListener.removeItemSelected(holder.itemView, position);
                } else { //선택되어있지 않은 경우, 선택되도록
                    holder.itemView.setSelected(true);
                    mybasket_data.get(position).setSelected(true);
                    mListener.addItemSelected(holder.itemView, position);
                }
                notifyDataSetChanged();
            }
        });
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
