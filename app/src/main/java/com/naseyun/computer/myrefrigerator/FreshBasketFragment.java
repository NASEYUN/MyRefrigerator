package com.naseyun.computer.myrefrigerator;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public class FreshBasketFragment extends Fragment implements ItemTouchHelperListener{
    // TODO: Rename parameter arguments, choose names that match
    private RecyclerView recyclerView_fresh_basket;
    private RecyclerView.LayoutManager recyclerview_manager;
    private MybasketAdapter fresh_ingredient_adapter;
    private ArrayList<Mybasket> fresh_baskets;
    private ItemTouchHelper helper;

    public FreshBasketFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FreshBasketFragment newInstance() {
        FreshBasketFragment fragment = new FreshBasketFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fresh_basket, container, false);
        recyclerView_fresh_basket = view.findViewById(R.id.recyclerview_fresh_basket);
        recyclerView_fresh_basket.setHasFixedSize(true);
        recyclerview_manager = new LinearLayoutManager(view.getContext());
        recyclerView_fresh_basket.setLayoutManager(recyclerview_manager);
        //recyclerView_fresh_basket.scrollToPosition(0);
        fresh_baskets = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            fresh_baskets.add(new Mybasket(1, String.valueOf(i) + "당근", i, "개", "뿌리채소", "2022-12-23", "D-" + String.valueOf(i)));
        }
        fresh_ingredient_adapter = new MybasketAdapter(fresh_baskets);
        //recyclerview에 어댑터 연결
        recyclerView_fresh_basket.setAdapter(fresh_ingredient_adapter);
        helper = new ItemTouchHelper(new ItemTouchHelperCallback(this));
        helper.attachToRecyclerView(recyclerView_fresh_basket);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public boolean onItemMove(int from_position, int to_position) { //drag & drop으로 순서 변경
        Mybasket item = fresh_baskets.get(from_position);
        fresh_baskets.remove(from_position);
        fresh_baskets.add(to_position, item);
        fresh_ingredient_adapter.notifyItemMoved(from_position, to_position);
        return false;
    }

    @Override
    public void onItemSwipe(int position) {  //swipe 시, 삭제
        fresh_baskets.remove(position);
        fresh_ingredient_adapter.notifyDataSetChanged();
    }
}
