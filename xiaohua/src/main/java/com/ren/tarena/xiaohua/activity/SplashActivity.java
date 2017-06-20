package com.ren.tarena.xiaohua.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.util.SharePreferenceUtil;

public class SplashActivity extends AppCompatActivity {


    private ImageView imageView;
    private Animation animation_splash;
    SharePreferenceUtil sharePreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharePreferenceUtil = new SharePreferenceUtil(this);
        //初始化控件
        imageView = (ImageView) findViewById(R.id.imageID);
        //加载动画
        animation_splash = AnimationUtils.loadAnimation(this, R.anim.alpha_scale);
        //将动画添加到image上
        imageView.setAnimation(animation_splash);
        animation_splash.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent;
                //跳转页面
                if (sharePreferenceUtil.isFirst()) {
                    intent = new Intent(SplashActivity.this, GuideActivity.class);
                    overridePendingTransition(R.anim.enter_anim, R.anim.out_anim);
                    sharePreferenceUtil.setFirst(false);

                } else {
                    //实现跳转是的一个切换页面
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                }
                startActivity(intent);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}

