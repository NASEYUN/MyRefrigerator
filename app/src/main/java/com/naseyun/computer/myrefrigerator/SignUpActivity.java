package com.naseyun.computer.myrefrigerator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SignUpActivity extends AppCompatActivity {
    // 2시 2분 수정

    ActionBar actionBar;
    Button address_search_btn;
    Button signup_btn;
    EditText addressText_first, addressText_second, addressText_third;
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;
    String[] address_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        address_search_btn = findViewById(R.id.address_search_Button);
        signup_btn = findViewById(R.id.registerButton);
        addressText_first = findViewById(R.id.addressText_first);
        addressText_second = findViewById(R.id.addressText_second);
        addressText_third = findViewById(R.id.addressText_third);

        //주소 3개의 edittext 터치 못하도록 막음
        addressText_first.setFocusable(false);
        addressText_second.setFocusable(false);
        addressText_third.setFocusable(false);

        address_search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent address_intent = new Intent(getApplicationContext(), AddressActivity.class);
                // 화면전환 애니메이션 없애기
                overridePendingTransition(0, 0);
                //startActivityForResult(address_intent, SEARCH_ADDRESS_ACTIVITY);
                setResult(SEARCH_ADDRESS_ACTIVITY, address_intent);
                //startActivity(address_intent);
                startActivityResult.launch(address_intent);
            }
        });

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

    //AddressActivity에서 성공적으로 돌아올 경우
    ActivityResultLauncher<Intent> startActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String data = intent.getExtras().getString("data");
                        if (data != null) {
                            Log.v("checkk", data);
                            address_data = data.split(", ");
                            //주소 3개의 edittext 터치 할 수 있도록 허용
                            addressText_first.setBackgroundColor(Color.WHITE);
                            addressText_second.setBackgroundColor(Color.WHITE);
                            addressText_first.setText(address_data[0]);
                            addressText_second.setText(address_data[1]);
                        }
                    }
                }
            }
    );
}
