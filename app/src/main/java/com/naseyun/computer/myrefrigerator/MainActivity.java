package com.naseyun.computer.myrefrigerator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tool_bar = findViewById(R.id.main_toolbar);
        tool_bar.setTitle(R.string.myAppName);

        setSupportActionBar(tool_bar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
                intent.putExtra("message", "Sign 액티비티로 이동!");
                startActivity(intent);
            }
        });

        Button btn_Signup = (Button)findViewById(R.id.btn_signup);
        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                intent.putExtra("message", "Sign 액티비티로 이동!");
                startActivity(intent);
            }
        });
    }


}
