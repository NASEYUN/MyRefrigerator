package com.naseyun.computer.myrefrigerator;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String data) {

            Bundle extra = new Bundle();
            Intent intent = new Intent();
            extra.putString("data", data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);
            finish();

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_search);

        Toolbar tool_bar = findViewById(R.id.address_toolbar);
        tool_bar.setTitle(R.string.myAppName);

        setSupportActionBar(tool_bar);
        actionBar_address = getSupportActionBar();
        actionBar_address.setDisplayShowTitleEnabled(false);

        webView = (WebView) findViewById(R.id.webView_address);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        //handler를 통한 javascript 이벤트 반응
        handler = new Handler();
        //webview 초기화
        init_webView();
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
        webView.addJavascriptInterface(new AndroidBridge(), "MyRefrigeratorApp");
        //DOMStorage허용
        webView.getSettings().setDomStorageEnabled(true);
        //ssl 인증이 없는 경우, web client를 chrome으로 설정
        webView.setWebChromeClient(new WebChromeClient() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                request.grant(request.getResources());
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //ssl에러가 발생해도 계속 진행하도록 설정
                handler.proceed();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });
        //webview url load. html 파일 주소
        webView.loadUrl("http://ec2-3-39-212-222.ap-northeast-2.compute.amazonaws.com/address_search.html");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class AndroidBridge {
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
