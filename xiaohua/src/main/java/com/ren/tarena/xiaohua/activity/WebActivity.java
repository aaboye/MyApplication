package com.ren.tarena.xiaohua.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.ren.tarena.xiaohua.R;

public class WebActivity extends AppCompatActivity {
    String web;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);


        //激活WebView为活跃状态，能正常执行网页的响应
        onResume();


        Intent intent=getIntent();
        web=intent.getStringExtra("web");

        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(web);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //数据缓存及屏幕适配
        WebSettings webSettings=webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        LinearLayout.LayoutParams webviewLP=new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
//        webView.setLayoutParams(webviewLP);
//        webView.setInitialScale(25);

        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);
    }
}
