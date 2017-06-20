package com.ren.tarena.xiaohua.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ren.tarena.xiaohua.R;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{
    private int images[]={R.mipmap.xiaohuangren,R.mipmap.xiaohai,R.mipmap.xiaohuanren1,};
    private RadioGroup radioGroup;
    private ViewPager viewPager;
    private Button btn_yonghu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        setViewPager();
        setRadioGroup();
        btn_yonghu = (Button) findViewById(R.id.btn_yonghutiyan);
        btn_yonghu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpagerID);
        PagerAdapter adapter=new ImageAdapter();
        viewPager.setAdapter(adapter);
        //viewPager.setOnPageChangeListener(this);
        viewPager.addOnPageChangeListener(this);

    }

    /**初始化radiogroup**/
    private void setRadioGroup(){
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //获得当前选中的radiobutton
                RadioButton btn= (RadioButton) group.findViewById(checkedId);
                //获得此radiobutton在radiogroup中的位置
                int position=group.indexOfChild(btn);
                //设置当前选中的viewpager
                viewPager.setCurrentItem(position);
            }
        });
        RadioButton rBtn= (RadioButton) radioGroup.findViewById(R.id.radiobutton1);
        rBtn.setChecked(true);
    }
    /**页面滚动时执行*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**当页面完全进入viewpager容器时执行此方法*/
    @Override
    public void onPageSelected(int position) {
        if (radioGroup!=null){
            RadioButton rBtn= (RadioButton) radioGroup.getChildAt(position);
            rBtn.setChecked(true);
        }
        if(position==2){
            btn_yonghu.setVisibility(View.VISIBLE);
        }else{
            btn_yonghu.setVisibility(View.GONE);
        }

    }
    /**滚动状态发生变化时执行*/
    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class ImageAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return images.length;
        }

        /***当此方法的返回值为true时才会以分页形式呈现数据
         * @param view 为添加到容器的view
         * @param object 为instantiateItem方法的返回值(key)
         *  */
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        /**类似ArrayAdapter中的getView方法，此方法用于
         * 初始化一个item并将item添加到viewpager容器*/
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //1.itemview
            ImageView imageView=new ImageView(container.getContext());
            //imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            //2.itemdata
            int imageID=images[position];
            //3.binddata
            imageView.setImageResource(imageID);
            //4.add data to container
            container.addView(imageView);  //container默认指向viewpager对象
            //ViewPager对象内部默认采用key/value的形式存储数据
            return imageView;  //返回的key，值为通过addview方法添加的view
            //此方法的返回值会影响isviewfromobiect的编写
        }
        /**当页面在滑动时有些view要从容器中移除，移除时会执行此方法
         * @param container  代表当前容器对象(ViewPager)
         * @param position  当前viewpager中item的位置
         * @param object  代表要移除的view的key
         * */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }
    }
}
