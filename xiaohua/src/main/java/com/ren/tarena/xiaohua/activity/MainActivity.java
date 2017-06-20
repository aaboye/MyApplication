package com.ren.tarena.xiaohua.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.adapter.MyFragmentPagerAdapter;
import com.ren.tarena.xiaohua.fragment.ClassifyFragment;
import com.ren.tarena.xiaohua.fragment.FunnyFragment;
import com.ren.tarena.xiaohua.fragment.TuijianFragment;
import com.ren.tarena.xiaohua.fragment.GifFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private TuijianFragment tuijian_fragment;
    private ClassifyFragment classify_fragment;
    private FunnyFragment funny_fragment;
    private GifFragment gif_fragment;
    private MyFragmentPagerAdapter adapter;
    private DrawerLayout drawerLayout_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         *  初始化控件（viewpager，radiogroup）
         *  创建适配器对象
         * 创建fragment对象
         * 将fragment添加到适配器的集合中
         * 将viewpager和适配器关联
         */
        //实现viewpage与fragmnet绑定
        initialFragments();
        //实现点击底部按钮切换的监听
        setListener();
        //侧滑菜单跳转activity
        cehuacaidantiaozhuan();





    }

    //侧滑菜单跳转activity
    private void cehuacaidantiaozhuan() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.my_navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.cehua_gerenzhongxin) {
                    startActivity(new Intent(MainActivity.this, OtherActivity.class));
                } else if (id == R.id.cehua_wodeshoucang) {
                    startActivity(new Intent(MainActivity.this, OtherActivity.class));
                } else if (id == R.id.cehua_xiaoxi) {
                    startActivity(new Intent(MainActivity.this, OtherActivity.class));
                } else if (id == R.id.cehua_haoyou) {
                    startActivity(new Intent(MainActivity.this, OtherActivity.class));
                } else if (id == R.id.cehua_shezhi) {
                    startActivity(new Intent(MainActivity.this, OtherActivity.class));
                } else if (id == R.id.cehua_tuichu) {
                    //startActivity(new Intent(MainActivity.this,OtherActivity.class));
                    //关闭侧滑菜单
                   // drawerLayout_main.closeDrawer(GravityCompat.START);
                    System.exit(0);
                }


                return true;
            }
        });
        //点击头像按钮跳转登录界面
        View drawview=navigationView.inflateHeaderView(R.layout.start_drawer_header);
        ImageView imageView_denglu= (ImageView) drawview.findViewById(R.id.imageView_cehua);
        imageView_denglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }

    //实现点击底部按钮切换的监听
    private void setListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.radio0);
                        break;
                    case 1:
                        radioGroup.check(R.id.radio1);
                        break;
                    case 2:
                        radioGroup.check(R.id.radio2);
                        break;
                    case 3:
                        radioGroup.check(R.id.radio3);
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio0:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.radio1:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.radio2:
                        viewPager.setCurrentItem(2, false);
                        break;
                    case R.id.radio3:
                        viewPager.setCurrentItem(3, false);
                        break;
                    default:
                        break;

                }
            }
        });
    }

    //实现viewpage与fragmnet绑定
    private void initialFragments() {
        //初始化控件（viewpager，radiogroup）
        viewPager = (ViewPager) findViewById(R.id.viewpagerID);
        radioGroup = (RadioGroup) findViewById(R.id.rg_tab_menu);
        //创建fragment对象
        FragmentManager fragmentManager =
                getSupportFragmentManager();
        adapter = new MyFragmentPagerAdapter(fragmentManager);
        tuijian_fragment = new TuijianFragment();
        funny_fragment = new FunnyFragment();
        gif_fragment = new GifFragment();
        classify_fragment = new ClassifyFragment();
        //将viewpager和适配器关联
        viewPager.setAdapter(adapter);
        //将fragment添加到适配器的集合中
        adapter.addFragment(tuijian_fragment);
        adapter.addFragment(funny_fragment);
        adapter.addFragment(gif_fragment);
        adapter.addFragment(classify_fragment);
        viewPager.setCurrentItem(0, false);

    }

    //实现侧滑的方法
    public void show() {
        drawerLayout_main = (DrawerLayout) findViewById(R.id.drawerlayout_main);
        drawerLayout_main.openDrawer(GravityCompat.START);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}