package com.naseyun.computer.myrefrigerator;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MybasketActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private RecyclerView recyclerView;
    private MybasketAdapter adapter;
    private BottomNavigationView bot_navi_menu;
    private Intent mybasket_intent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybasket);

        mybasket_intent = getIntent(); // 인텐트 받기

        Toolbar tool_bar = findViewById(R.id.basket_toolbar);

        setSupportActionBar(tool_bar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        // ↓툴바에 홈버튼을 활성화
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_mybasket);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        //Mybasket 어댑터
        adapter = new MybasketAdapter();

        for(int i=0; i<100; i++) {
            adapter.addItem(new Mybasket(String.valueOf(i)+"당근", "2022-12-23", i));
        }
        //recyclerview에 어댑터 연결
        recyclerView.setAdapter(adapter);

        //하단 네비게이션 바
        bot_navi_menu = findViewById(R.id.basket_bottom_navi_menu);
        bot_navi_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.myhomeFragment) {
                    menuItem.setChecked(true);
                    Intent home_intent = new Intent(getApplicationContext(), ItemActivity.class);
                    home_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(home_intent);
                    Toast.makeText(getApplicationContext(), home_intent.toString(), Toast.LENGTH_SHORT).show();
                }
                else if (menuItem.getItemId() == R.id.recipeFragment) {
                    menuItem.setChecked(true);
                    Intent recipe_intent = new Intent(getApplicationContext(), RecipeActivity.class);
                    recipe_intent.putExtra("message", "레시피 액티비티로 이동!");
                    startActivity(recipe_intent);
                    Toast.makeText(getApplicationContext(), recipe_intent.toString(), Toast.LENGTH_SHORT).show();
                }
                else if (menuItem.getItemId() == R.id.bookmarkFragment) {
                    menuItem.setChecked(true);
                    Intent bookmark_intent = new Intent(getApplicationContext(), BookmarkActivity.class);
                    bookmark_intent.putExtra("message", "북마크 액티비티로 이동!");
                    startActivity(bookmark_intent);
                    Toast.makeText(getApplicationContext(), bookmark_intent.toString(), Toast.LENGTH_SHORT).show();
                }
                else if (menuItem.getItemId() == R.id.myPageFragment) {
                    menuItem.setChecked(true);
                    Intent mypage_intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    mypage_intent.putExtra("message", "회원계정 액티비티로 이동!");
                    startActivity(mypage_intent);
                    Toast.makeText(getApplicationContext(), mypage_intent.toString(), Toast.LENGTH_SHORT).show();
                }
            return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case android.R.id.home: {
                //현재 화면에서 뒤로가기
                onBackPressed();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
