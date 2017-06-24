package ren.com.dazhongdianping.app;

import android.app.Application;

import java.util.List;

import ren.com.dazhongdianping.entity.CitynameBean;

/**
 * Created by tarena on 2017/6/19.
 */

public class MyApp extends Application {
    public static MyApp CONTEXT;
    public static List<CitynameBean> citynameBeanList;

    @Override
    public void onCreate() {
        super.onCreate();
        CONTEXT=this;
    }
}
