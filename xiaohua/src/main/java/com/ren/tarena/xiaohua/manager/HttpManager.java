package com.ren.tarena.xiaohua.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.ren.tarena.xiaohua.entity.Funny;
import com.ren.tarena.xiaohua.entity.Gif;
import com.ren.tarena.xiaohua.entity.Tuijian;
import com.ren.tarena.xiaohua.entity.User;
import com.ren.tarena.xiaohua.iconstant.IURL;
import com.ren.tarena.xiaohua.util.DateUtil;
import com.ren.tarena.xiaohua.util.ParamsUtil;
import com.ren.tarena.xiaohua.util.StreamUtil;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tarena on 2017/6/3.
 */

public class HttpManager {
    static String SESSIONID = "";

    /**
     * 注册界面网络请求
     *
     * @param user
     * @return
     */
    public static boolean registHttpPost(User user) {
        boolean flag = false;
        try {
            URL url = new URL(IURL.REGIST_URL);
            //获得连接对象
            HttpURLConnection connection =
                    (HttpURLConnection)
                            url.openConnection();
            //设置请求的参数和请求的属性
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //拼接提交数据的字符串
            Map<String, String> params = new HashMap<String, String>();
            params.put("loginname", user.getLoginname());
            params.put("password", user.getPassword());
            //根据拼接的字符串获得提交的数据
            byte[] datas = ParamsUtil.createParams(params).getBytes();
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", String.valueOf(datas.length));
            connection.connect();

            //获得指向服务器的数据输出流向服务器端提交数据
            OutputStream os = connection.getOutputStream();
            os.write(datas);
            os.flush();
            //获得服务端响应的状态码
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                //获得服务器端的响应结果
                InputStream is = connection.getInputStream();
                //将输入流转化成字符串
                String jsonStr = StreamUtil.createStr(is);
                JSONObject jsonObject = new JSONObject(jsonStr);
                String result = jsonObject.getString("result");
                if ("ok".equals(result)) {
                    flag = true;
                } else {
                    String msg = jsonObject.getString("msg");
                    Log.i("TAG:msg", msg);
                }
            } else {
                Log.i("TAG:statusCode", "" + statusCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return flag;
    }

    /**
     * 登录界面网络请求
     *
     * @param loginName
     * @param password
     * @param code
     * @return
     */
    public static boolean loginHttpPost(
            String loginName,
            String password,
            String code) {
        boolean flag = false;
        try {
            URL url = new URL(IURL.LOGIN_URL);
            HttpURLConnection connection = (HttpURLConnection)
                    url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            //设置请求头信息
            connection.setRequestProperty("Cookie", SESSIONID);


            //拼接提交的参数的字符串
            Map<String, String> params =
                    new HashMap<String, String>();
            params.put("loginname", loginName);
            params.put("password", password);
            params.put("code", code);
            byte[] datas = ParamsUtil.createParams(params).getBytes();
            connection.setRequestProperty(
                    "Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty(
                    "Content-Length",
                    String.valueOf(datas.length));
            connection.connect();

            //向服务器端提交数据
            OutputStream os = connection.getOutputStream();
            os.write(datas);
            os.flush();
            os.close();

            //获得响应的状态码
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                InputStream is = connection.getInputStream();
                String jsonStr = StreamUtil.createStr(is);
                JSONObject jsonObject = new JSONObject(jsonStr);
                String result = jsonObject.getString("result");
                if ("ok".equals(result)) {
                    flag = true;
                } else {
                    String msg = jsonObject.getString("msg");
                    Log.i("TAG:msg", msg);
                }
            } else {
                Log.i("TAG:statusCode", statusCode + "");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return flag;
    }

    //验证码请求
    public static Bitmap loadCodeHttpGet() {
        Bitmap codeBitmap = null;
        try {
            URL url = new URL(IURL.CODE_URL);
            HttpURLConnection connection =
                    (HttpURLConnection)
                            url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);

            connection.connect();
            int statusCode = connection.
                    getResponseCode();

            if (statusCode == 200) {
                SESSIONID = connection.getHeaderField("Set-Cookie").split(";")[0];
                InputStream is = connection.getInputStream();
                codeBitmap = BitmapFactory.decodeStream(is);
                Log.i("TAG:SESSIONID", SESSIONID);
            } else {
                Log.i("TAG:statusCode", "" + statusCode);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return codeBitmap;
    }

    //网络请求趣图页面
    public static void loadFunny(Context context, final FunnyLoadListener loadListener) {
        RequestQueue queue = null;

        String url =IURL.PICPATH_ROOT+IURL.KEY;
        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Funny funny = gson.fromJson(s, Funny.class);
                //Log.i("TAG:weather",weatherBean.toString());
                loadListener.onFunnyLoadEnd(funny);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        queue.add(request);

    }

    public interface FunnyLoadListener {
        public void onFunnyLoadEnd(Funny funny);
    }

    //网络请求推荐页面

    public static void loadTuijian(Context context, final TuijianLoadListener loadListener) {
        RequestQueue queue = null;
        String url = IURL.TEXTPATH_ROOT + IURL.KEY;

        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Tuijian tuijian = gson.fromJson(s, Tuijian.class);
                //Log.i("TAG:weather",tuijian.toString());
                loadListener.onTuijianLoadEnd(tuijian);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        queue.add(request);

    }

    public interface TuijianLoadListener {
        public void onTuijianLoadEnd(Tuijian tuijian);
    }

    //网络请求动图页面

    public static void loadGif(Context context, final GifLoadListener loadListener) {
        RequestQueue queue = null;
        String url = IURL.GIFPATH_ROOT + IURL.KEY;

        if (queue == null) {
            queue = Volley.newRequestQueue(context);
        }

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                Gif gif = gson.fromJson(s, Gif.class);
                //Log.i("TAG:weather",tuijian.toString());
                loadListener.onGifLoadEnd(gif);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        });
        queue.add(request);

    }

    public interface GifLoadListener {
        public void onGifLoadEnd(Gif gif);
    }


}
