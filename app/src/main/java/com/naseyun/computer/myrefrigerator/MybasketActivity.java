package com.naseyun.computer.myrefrigerator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MybasketActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private MybasketAdapter  expiration_adapter;
    private BottomNavigationView bot_navi_menu;
    private Intent mybasket_intent;
    private TabLayout tablayout_basket;
    private ViewPager2 viewPager_basket;
    private TabPagerAdapter tabPagerAdapter;
    private FreshBasketFragment freshBasketFragment;
    private ExpirationBasketFragment expirationBasketFragment;

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

        createFragment();
        createViewpager();
        settingTabLayout();

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
                    startActivity(recipe_intent);
                    Toast.makeText(getApplicationContext(), recipe_intent.toString(), Toast.LENGTH_SHORT).show();
                }
                else if (menuItem.getItemId() == R.id.bookmarkFragment) {
                    menuItem.setChecked(true);
                    Intent bookmark_intent = new Intent(getApplicationContext(), BookmarkActivity.class);
                    startActivity(bookmark_intent);
                    Toast.makeText(getApplicationContext(), bookmark_intent.toString(), Toast.LENGTH_SHORT).show();
                }
                else if (menuItem.getItemId() == R.id.martFragment) {
                    menuItem.setChecked(true);
                    Intent bookmark_intent = new Intent(getApplicationContext(), MainActivity.class);  //마트화면이 없어 임시로 메인으로 이동되도록 해놓음!
                    bookmark_intent.putExtra("message", "마트 액티비티로 이동!");
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
        menuInflater.inflate(R.menu.menu_item_add, menu);
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
            case R.id.menu_plus:
                Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //fragment생성
    private void createFragment() {
        freshBasketFragment = new FreshBasketFragment();
        expirationBasketFragment = new ExpirationBasketFragment();
    }

    //viewpager 및 adapter생성
    private void createViewpager() {
        viewPager_basket = (ViewPager2)findViewById(R.id.viewpager_basket);
        viewPager_basket.setUserInputEnabled(false); //viewpager 스와이프 안 되도록 막기
        tabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), getLifecycle());
        tabPagerAdapter.addFragment(freshBasketFragment);
        tabPagerAdapter.addFragment(expirationBasketFragment);
        viewPager_basket.setAdapter(tabPagerAdapter);
        viewPager_basket.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
    }

    //탭 레이아웃 설정
    private void settingTabLayout() {
        tablayout_basket = findViewById(R.id.tablayout_basket);
        tablayout_basket.addTab(tablayout_basket.newTab().setText("신선 재료"), 0);
        tablayout_basket.addTab(tablayout_basket.newTab().setText("유통기한 만료"), 1);
        tablayout_basket.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) { //tab상태가 선택 상태로 변경
                int pos = tab.getPosition();
                switch (pos) {
                    case 0: //첫번째 탭인 경우
                        viewPager_basket.setCurrentItem(pos, false);
                        break;
                    case 1: //두번째 탭인 경우
                        viewPager_basket.setCurrentItem(pos, false);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { //tab 상태가 선택되지 않음으로 변경

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { //이미 선택된 tab이 다시 선택

            }
        });
    }
}
