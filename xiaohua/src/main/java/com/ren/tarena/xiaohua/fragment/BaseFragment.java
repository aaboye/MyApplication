package com.ren.tarena.xiaohua.fragment;

import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ren.tarena.xiaohua.R;

/**
 * Created by tarena on 2017/5/17.
 */

public abstract class BaseFragment extends Fragment {
    //定义一个方法事项对标题三个空间的初始设置
    protected View contentView=null;
    protected LinearLayout actionbar;
    private ImageView imageView_left;

    //左边图片资源ID  中间文本  右边图片资源ID
    public void initialActionbar(int leftID, String title, int rightID) {
        if (actionbar==null){
            return;
        }
        //获得标题栏当中的各个控件
        imageView_left = (ImageView) actionbar.findViewById(R.id.ab_left_imageID);
        TextView textView= (TextView) actionbar.findViewById(R.id.ab_textviewID);
        ImageView imageView_right= (ImageView) actionbar.findViewById(R.id.ab_right_imageID);
        //如果第一个参数小于等于0说明左边图片不显示
        if (leftID<=0){
            imageView_left.setVisibility(View.INVISIBLE);
        }else {
            imageView_left.setVisibility(View.VISIBLE);
            imageView_left.setImageResource(leftID);
        }
        if (TextUtils.isEmpty(title)){
            textView.setVisibility(View.INVISIBLE);
        }else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(title);
        }
        if (rightID<=0){
            imageView_right.setVisibility(View.INVISIBLE);
        }else {
            imageView_right.setVisibility(View.VISIBLE);
            imageView_right.setImageResource(rightID);
        }
    }
    public abstract void initialUI();
}
