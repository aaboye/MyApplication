package ren.com.dazhongdianping.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.constant.Constant;
import ren.com.dazhongdianping.util.SharePreferenceUtil;

public class SplashActivity extends AppCompatActivity {
    SharePreferenceUtil sharePreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharePreferenceUtil = new SharePreferenceUtil(this);
        //界面停留几秒钟
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //根据是否是第一次使用进行相应的界面跳转
                Intent intent;
                if (sharePreferenceUtil.isFirst()) {
                    //想新手指导页跳转
                    intent = new Intent(SplashActivity.this, GuideActivity.class);
                    sharePreferenceUtil.setFirst(false);
                } else {
                    //向主页面跳转
                    intent = new Intent(SplashActivity.this, MainActivity.class);

                }
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
