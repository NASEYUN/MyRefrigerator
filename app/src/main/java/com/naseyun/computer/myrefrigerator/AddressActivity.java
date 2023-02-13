package com.naseyun.computer.myrefrigerator;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddressActivity extends AppCompatActivity {

    ActionBar actionBar_address;
    private WebView webView;
    private TextView txt_address;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_search);

        Toolbar tool_bar = findViewById(R.id.address_toolbar);
        tool_bar.setTitle(R.string.myAppName);

        setSupportActionBar(tool_bar);
        actionBar_address = getSupportActionBar();
        actionBar_address.setDisplayShowTitleEnabled(false);

        //webview 초기화
        init_webView();

        //handler를 통한 javascript 이벤트 반응
        handler = new Handler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item_close, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
        switch (id)
        {
            case R.id.menu_close: {
                //뒤로 가기
                onBackPressed();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void init_webView() {
        webView = (WebView) findViewById(R.id.webView_address);

        //javascript허용
        webView.getSettings().setJavaScriptEnabled(true);
        //javascript의 window.open허용
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //javascript이벤트에 대응할 함수를 정의 한 클래스를 붙여줌
        webView.addJavascriptInterface(new AndroidBridge(), "TestApp");
        //web client를 chrome으로 설정
        webView.setWebChromeClient(new WebChromeClient());
        //webview url load. php 파일 주소
        webView.loadUrl("http://192.168.25.60:80/daum_address.php");
    }

    private class AndroidBridge {
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    txt_address.setText(String.format("(%s) %s %s", arg1, arg2, arg3));
                    init_webView(); //webview를 초기화하지 않으면 재사용할 수 없음
                }
            });
        }
    }
}
