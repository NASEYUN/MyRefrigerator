package com.naseyun.computer.myrefrigerator;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private BottomNavigationView bot_navi_menu;
    private EditText username, id, pw, pw_check, address1, address2, address3;
    private String edit_username, edit_id, edit_pw, edit_gender;
    private Button edit_profile;
    private RadioButton gender;
    private Intent profile_intent;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profile_intent = getIntent();  //인텐트 받기

        username = findViewById(R.id.username_textview);
        id = findViewById(R.id.id_textview);
        gender = findViewById(R.id.genderMan);
        gender = findViewById(R.id.genderWoman);
        pw = findViewById(R.id.pw_textview);
        pw_check = findViewById(R.id.pwcheck_textview);
        address1 = findViewById(R.id.address1_textview);
        address2 = findViewById(R.id.address2_textview);
        address3 = findViewById(R.id.address3_textview);
        //edit_profile = findViewById(R.id.edit_profile);

        username.setEnabled(false);
        id.setEnabled(false);
        pw.setEnabled(false);
        pw_check.setEnabled(false);
        gender.setEnabled(false);
        address1.setEnabled(false);
        address2.setEnabled(false);
        address3.setEnabled(false);

        Toolbar tool_bar = findViewById(R.id.profile_toolbar);

        setSupportActionBar(tool_bar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        // ↓툴바에 홈버튼을 활성화
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        username.setText("seyuun");
//        id.setText("nsy");
//        gender.setText("여자");
//        pw.setText("1234");
//        pw.setPaintFlags(pw.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

//        edit_profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //edittext 활성화
//                username.setEnabled(true);
//                id.setEnabled(true);
//                pw.setEnabled(true);
//                pw_check.setEnabled(true);
//                gender.setEnabled(true);
//                address1.setEnabled(true);
//                address2.setEnabled(true);
//                address3.setEnabled(true);
//
//                edit_profile.setText("수정 완료");
//
//                //수정한 데이터로 변경되도록
//                edit_username = username.getText().toString();
//                edit_id = id.getText().toString();
//                edit_pw = pw.getText().toString();
////                edit_gender = gender.getText().toString();
//
//                username.setText(edit_username);
//                id.setText(edit_id);
//                pw.setText(edit_pw);
////                gender.setText(edit_gender);
//            }
//        });

        //하단 네비게이션 바
        bot_navi_menu = findViewById(R.id.profile_bottom_navi_menu);
        bot_navi_menu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.myhomeFragment) {
                    menuItem.setChecked(true);
                    Intent home_intent = new Intent(getApplicationContext(), ItemActivity.class);
                    home_intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(home_intent);
                    Toast.makeText(getApplicationContext(), home_intent.toString(), Toast.LENGTH_SHORT).show();
                } else if (menuItem.getItemId() == R.id.recipeFragment) {
                    menuItem.setChecked(true);
                    Intent recipe_intent = new Intent(getApplicationContext(), RecipeActivity.class);
                    recipe_intent.putExtra("message", "레시피 액티비티로 이동!");
                    startActivity(recipe_intent);
                    Toast.makeText(getApplicationContext(), recipe_intent.toString(), Toast.LENGTH_SHORT).show();
                } else if (menuItem.getItemId() == R.id.bookmarkFragment) {
                    menuItem.setChecked(true);
                    Intent bookmark_intent = new Intent(getApplicationContext(), BookmarkActivity.class);
                    bookmark_intent.putExtra("message", "북마크 액티비티로 이동!");
                    startActivity(bookmark_intent);
                    Toast.makeText(getApplicationContext(), bookmark_intent.toString(), Toast.LENGTH_SHORT).show();
                } else if (menuItem.getItemId() == R.id.martFragment) {
                    menuItem.setChecked(true);
                    Intent bookmark_intent = new Intent(getApplicationContext(), MapsActivity.class);
                    bookmark_intent.putExtra("message", "구글맵 액티비티로 이동!");
                    startActivity(bookmark_intent);
                    Toast.makeText(getApplicationContext(), bookmark_intent.toString(), Toast.LENGTH_SHORT).show();
                } else if (menuItem.getItemId() == R.id.myPageFragment) {
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
        menuInflater.inflate(R.menu.edit_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: {
                //현재 화면에서 뒤로가기
                onBackPressed();
                return true;
            }
            case R.id.menu_edit: {
                Intent intent = new Intent(getApplicationContext(), Edit_Profile_Activity.class);
                intent.putExtra("message", "편집 액티비티로 이동!");
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
