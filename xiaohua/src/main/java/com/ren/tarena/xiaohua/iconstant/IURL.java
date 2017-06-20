package com.ren.tarena.xiaohua.iconstant;

/**
 * Created by tarena on 2017/6/3.
 */

public interface IURL {
    String ROOT="http://192.168.70.1:8080/EmployeeServer/";
    String REGIST_URL=ROOT+"regist.do";
    String CODE_URL=ROOT+"getCode.do";
    String LOGIN_URL=ROOT+"login.do";

    //
    String KEY="showapi_sign=f24d77d883404e0196fab273d8a2c7ce&showapi_appid=28026&time=2015-07-10";
    //文字笑话路径
    String TEXTPATH_ROOT="http://route.showapi.com/341-1?";
    //图片笑话路径
   String PICPATH_ROOT="http://route.showapi.com/341-2?";
    //动图笑话路径
    String GIFPATH_ROOT="http://route.showapi.com/341-3?maxResult=10&";
}
