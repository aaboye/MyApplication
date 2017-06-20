package ren.com.dazhongdianping.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import com.viewpagerindicator.CirclePageIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.adapter.GuidePagerAdapter;

public class GuideActivity extends FragmentActivity {

    private GuidePagerAdapter adapter;


    @BindView(R.id.viewPage_guide)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initViewPager();
    }

    private void initViewPager() {
        adapter = new GuidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        indicator.setViewPager(viewPager);
        /**
         * 当前运行程序所使用的设备的屏幕密度
         * 低密度 ldpi  120x/1inch(2.54)
         * 中密度 mdpi  160pxx/1inch
         * 高密度 hdpi  240px/1inch
         * 很高密度 xhdpi 320px/1inch
         * 非常高密度 xxhdpi  480px/1inch
         *
         * dp 絶対単位 160dp=1inch
         * 1dp 在低密度屏幕上 0.75px
         * 1dp 在中密度屏幕上 1px
         * 1dp 在高密度屏幕上 1.5px
         * 1dp 在很高密度屏幕上 2px
         * 1dp 在非常高密度屏幕上 3px
         */
        //另外一种获得5dp在当前设备屏幕密度的像素值得方式
        //float px= TypedValue.applyDimension()

        final float density = getResources().getDisplayMetrics().density;
        //indicator.setBackgroundColor(0xFFCCCCCC);
        //5dp在当前设备上所对应的像素值（px）
        indicator.setRadius(5 * density);
        indicator.setPageColor(0xFFFFFFFF);
        indicator.setFillColor(0xFFEC4132);
        indicator.setStrokeColor(0xFFEC4132);
        indicator.setStrokeWidth(1 * density);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==3){
                    indicator.setVisibility(View.INVISIBLE);
                }else {
                    indicator.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
