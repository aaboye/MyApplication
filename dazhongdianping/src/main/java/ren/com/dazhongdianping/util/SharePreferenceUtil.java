package ren.com.dazhongdianping.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ren.com.dazhongdianping.constant.Constant;

/**
 * 对偏好设置文件的操作
 *  1）Context的getSharedPreferences(文件名, 模式)
 *  2）Activity的getPreferences（模式）
 *  3）PreferenceManager的getdefaultSharePreferences，获取preference_包名   偏好设置文件
 *     模式  Context_MODE_PRIVATE
 * Created by tarena on 2017/6/15.
 */

public class SharePreferenceUtil {
    SharedPreferences sp;

    //通过构造器重载，以不同的方式获得偏好设置文件
    public  SharePreferenceUtil(Context context,String name){
       sp= context.getSharedPreferences(name,Context.MODE_PRIVATE);
    }

    public  SharePreferenceUtil(Context context){
        sp= PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isFirst(){
        return sp.getBoolean(Constant.FIRST,true);
    }

    public void setFirst(boolean flag){
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(Constant.FIRST,false);
        editor.commit();
    }

}
