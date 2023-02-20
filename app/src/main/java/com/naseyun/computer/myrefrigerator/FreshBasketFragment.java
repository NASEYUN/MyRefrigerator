package com.naseyun.computer.myrefrigerator;

import android.graphics.Canvas;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import java.util.ArrayList;

public class FreshBasketFragment extends Fragment implements ItemTouchHelperListener, OnDialogListener{
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
        recyclerView_fresh_basket.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                helper.onDraw(c, parent, state);
            }
        });
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
        //fresh_ingredient_adapter.notifyItemRemoved(position);
        fresh_ingredient_adapter.notifyItemRemoved(position);
        Log.v("checkk", "onItemswipe");
    }

    @Override
    public void onLeftClick(int position, RecyclerView.ViewHolder viewHolder) {
        fresh_ingredient_adapter.notifyDataSetChanged();
        //AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.AlertDialogTheme);
        BasketDialog basketDialog = new BasketDialog(getContext(), position, fresh_baskets.get(position));

        //화면 사이즈 구하기
        DisplayMetrics dm = getContext().getApplicationContext().getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height = dm.heightPixels;

        //다이얼로그 사이즈 설정
        WindowManager.LayoutParams params = basketDialog.getWindow().getAttributes();
        params.copyFrom(basketDialog.getWindow().getAttributes());
        params.width = (int)(width * 0.7);
        params.height = height/2;

        //다이얼로그 listener 설정
        basketDialog.setDialogListener(this);
        //다이얼로그 띄우기
        basketDialog.show();

    }

    @Override
    public void onRightClick(int position, RecyclerView.ViewHolder viewHolder) {
        fresh_baskets.remove(position);
        fresh_ingredient_adapter.notifyItemRemoved(position);
        Log.v("checkk", "onRightClick");
    }

    @Override
    public void onFinish(int position, Mybasket mybasket) {
        fresh_baskets.set(position, mybasket);
        fresh_ingredient_adapter.notifyItemChanged(position);
    }
}
