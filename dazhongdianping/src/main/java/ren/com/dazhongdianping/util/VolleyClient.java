package ren.com.dazhongdianping.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.app.MyApp;
import ren.com.dazhongdianping.entity.IdList;

/**
 * Created by tarena on 2017/6/19.
 */

public class VolleyClient {
    /**
     * 单例模式(方便其他类调用）
     * 1声明一个私有的静态属性
     * 2声明一个共有的静态的获取1属性的方法
     */
    private static VolleyClient INSTANCE;

    public synchronized static VolleyClient getINSTANCE() {
        //上锁只让一个线程创建INSTANCE，并使用后来的直接可以使用
        if (INSTANCE == null) {
            synchronized (VolleyClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new VolleyClient();
                }
            }
        }
        return INSTANCE;
    }

    RequestQueue queue;
    ImageLoader imageLoader;

    private VolleyClient() {
        queue = Volley.newRequestQueue(MyApp.CONTEXT);
        imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            //least recently use
            LruCache<String,Bitmap> cache = new LruCache<String,Bitmap>((int) (Runtime.getRuntime().maxMemory()/8)){
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getHeight()*value.getRowBytes();
                }
            };

            @Override
            public Bitmap getBitmap(String s) {
                return cache.get(s);
            }

            @Override
            public void putBitmap(String s, Bitmap bitmap) {
                cache.put(s,bitmap);
            }
        });

    }

    public void text() {
        Map<String, String> params = new HashMap<>();
        params.put("city", "北京");
        params.put("category", "美食");
        String url = HttpUtil.getURL("http://api.dianping.com/v1/business/find_businesses", params);
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.i("TAG", s.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        queue.add(request);
    }

    /**
     * 利用volly获取城市近日新增团购信息
     *
     */
    public void getDailyAddList(String city, final Response.Listener<String> listener){
        final Map<String, String> params=new HashMap<>();
        params.put("city",city);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date= format.format(System.currentTimeMillis());
        params.put("date",date);
        final String url=HttpUtil.getURL("http://api.dianping.com/v1/deal/get_daily_new_id_list",params);
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //利用jsonObject提取id信息
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("id_list");

                    int size=jsonArray.length();
                    if (size > 40) {
                        size=40;
                    }
                    StringBuilder sb=new StringBuilder();
                    for (int i=0;i<size;i++){
                        String id=jsonArray.getString(i);
                        sb.append(id).append(",");
                    }
                    if (sb.length()>0){
                    String idlist=sb.substring(0,sb.length()-1);

                    //获取团购详情
                    Map<String, String> params2=new HashMap<>();
                    params2.put("deal_ids",idlist);
                    String url=HttpUtil.getURL("http://api.dianping.com/v1/deal/get_batch_deals_by_id",params2);

                    StringRequest req=new StringRequest(url,listener,null);
                    queue.add(req);
                    }else {
                        //该城市今日无新增团购
                        listener.onResponse(null);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },null);
        queue.add(request);
    }

    /**
     * 显示网络中的一幅图片
     * @param url 图片在网络中的地址
     * @param iv  显示图片的控件
     */
    public void loadImage(String url,ImageView iv){

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(iv, R.drawable.bucket_no_picture,R.drawable.bucket_no_picture);
        imageLoader.get(url,listener);
    }

    public void getDailyAddList2(String city, final Response.Listener<IdList> listener){
        final Map<String, String> params=new HashMap<>();
        params.put("city",city);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String date= format.format(System.currentTimeMillis());
        params.put("date",date);
        final String url=HttpUtil.getURL("http://api.dianping.com/v1/deal/get_daily_new_id_list",params);
        StringRequest request=new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //利用jsonObject提取id信息
                try {
                    JSONObject jsonObject=new JSONObject(s);
                    JSONArray jsonArray=jsonObject.getJSONArray("id_list");

                    int size=jsonArray.length();
                    if (size > 40) {
                        size=40;
                    }
                    StringBuilder sb=new StringBuilder();
                    for (int i=0;i<size;i++){
                        String id=jsonArray.getString(i);
                        sb.append(id).append(",");
                    }
                    if (sb.length()>0){
                        String idlist=sb.substring(0,sb.length()-1);

                        //获取团购详情
                        Map<String, String> params3=new HashMap<>();
                        params3.put("deal_ids",idlist);
                        String url=HttpUtil.getURL("http://api.dianping.com/v1/deal/get_batch_deals_by_id",params3);

                        TuanBeanRequest req=new TuanBeanRequest(url,listener);
                        queue.add(req);
                    }else {
                        //该城市今日无新增团购
                        listener.onResponse(null);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },null);
        queue.add(request);
    }

    /**
     * 自定义请求对象
     */
    public class TuanBeanRequest extends Request<IdList>{
        Response.Listener<IdList> listener;
        public TuanBeanRequest(String url,Response.Listener<IdList> listener) {
            super(Method.GET, url, null);
            this.listener=listener;
        }

        @Override
        protected Response<IdList> parseNetworkResponse(NetworkResponse networkResponse) {
            String resp=new String(networkResponse.data);
            Gson gson=new Gson();
            IdList idlist=gson.fromJson(resp,IdList.class);
            //自己组装一个volley的Response对象作为方法的返回值
            Response<IdList> result=Response.success(idlist, HttpHeaderParser.parseCacheHeaders(networkResponse));

            return result;
        }

        @Override
        protected void deliverResponse(IdList idList) {
            listener.onResponse(idList);
        }
    }

    public void getCityList(final Response.Listener<String> listener){


        Map<String, String> params=new HashMap<>();
        String url=HttpUtil.getURL("http://api.dianping.com/v1/metadata/get_cities_with_businesses",params);
        StringRequest request=new StringRequest(url,listener,null);
        queue.add(request);
    }


    public void getBusinessVolley(String city,Response.Listener<String> listener){
        Map<String, String> params=new HashMap<>();
        params.put("city",city);
        String url=HttpUtil.getURL("http://api.dianping.com/v1/business/find_businesses",params);
        StringRequest request=new StringRequest(url,listener,null);
        queue.add(request);
    }

}
