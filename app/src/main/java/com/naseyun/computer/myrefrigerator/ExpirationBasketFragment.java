package com.naseyun.computer.myrefrigerator;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class ExpirationBasketFragment extends Fragment implements ItemTouchHelperListener, onListItemSelectedInterface{
    private RecyclerView recyclerView_expiration_basket;
    private RecyclerView.LayoutManager recyclerview_manager;
    private MybasketAdapter expiration_ingredient_adapter;
    private ArrayList<Mybasket> expiration_baskets;
    private ItemTouchHelper helper;

    public ExpirationBasketFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ExpirationBasketFragment newInstance(String param1, String param2) {
        ExpirationBasketFragment fragment = new ExpirationBasketFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fresh_basket, container, false);
        recyclerView_expiration_basket = view.findViewById(R.id.recyclerview_fresh_basket);
        recyclerView_expiration_basket.setHasFixedSize(true);
        recyclerview_manager = new LinearLayoutManager(view.getContext());
        recyclerView_expiration_basket.setLayoutManager(recyclerview_manager);
        recyclerView_expiration_basket.scrollToPosition(0);
        expiration_baskets = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            expiration_baskets.add(new Mybasket(2, String.valueOf(i)+"고등어", i, "마리","해산물","2023-02-10", "D+" + String.valueOf(i)));
        }
        expiration_ingredient_adapter = new MybasketAdapter(expiration_baskets, this);
        //recyclerview에 어댑터 연결
        recyclerView_expiration_basket.setAdapter(expiration_ingredient_adapter);
        helper = new ItemTouchHelper(new ItemTouchHelperCallback(this));
        helper.attachToRecyclerView(recyclerView_expiration_basket);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) {//drag & drop으로 순서 변경
        Mybasket item = expiration_baskets.get(from_position);
        expiration_baskets.remove(from_position);
        expiration_baskets.add(to_position, item);
        expiration_ingredient_adapter.notifyItemMoved(from_position, to_position);
        return false;
    }

    @Override
    public void onItemSwipe(int position) {  //swipe 시, 삭제
        expiration_baskets.remove(position);
        expiration_ingredient_adapter.notifyDataSetChanged();
    }

    @Override
    public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        expiration_baskets.remove(position);
        expiration_ingredient_adapter.notifyItemRemoved(position);
    }

    @Override
    public void addItemSelected(View v, int position) {

    }

    @Override
    public void removeItemSelected(View v, int position) {

    }
}
