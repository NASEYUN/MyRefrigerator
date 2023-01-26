package com.naseyun.computer.myrefrigerator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SignUpActivity extends AppCompatActivity {
    ActionBar actionBar;
    Button signup_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_btn = findViewById(R.id.registerButton);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //회원정보 DB 저장 코드 작성해야 함!

                //회원가입 시, 로그인하는 메인화면으로 이동
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("message", "Sign 액티비티로 이동!");
                startActivity(intent);
            }
        });

        Toolbar tool_bar = findViewById(R.id.signup_toolbar);
        tool_bar.setTitle(R.string.myAppName);

        setSupportActionBar(tool_bar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        // ↓툴바에 홈버튼을 활성화
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        Log.d("tag", intent.getStringExtra("message"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
        switch (id)
        {
            case android.R.id.home: {
                // 해당 버튼을 눌렀을 때 적절한 액션을 넣는다.
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("message", "메인 액티비티로 이동!");
                startActivity(intent);
                return true;
            }
            case R.id.menu_basket: {
                Intent intent = new Intent(getApplicationContext(), MybasketActivity.class);
                intent.putExtra("message", "장바구니 액티비티로 이동!");
                startActivity(intent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
