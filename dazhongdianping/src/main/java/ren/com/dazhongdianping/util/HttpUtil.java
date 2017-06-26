package ren.com.dazhongdianping.util;

import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response;
import com.squareup.picasso.Picasso;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.app.MyApp;
import ren.com.dazhongdianping.entity.BusinessList;
import ren.com.dazhongdianping.entity.IdList;
import retrofit2.Callback;

/**
 * 网络访问工具类
 * <p>
 * 网址
 * http://api.dianping.com/v1/business/find_businesses
 * App Key：49814079
 * App Secret：90e3438a41d646848033b6b9d461ed54
 * Created by tarena on 2017/6/19.
 */

public class HttpUtil {

    public static final String APPKEY = "49814079";
    public static final String APPSECRET = "90e3438a41d646848033b6b9d461ed54";

    /**
     * 获得请求路径
     *
     * @return
     */
    public static String getURL(String url, Map<String, String> params) {
        String result = "";

        String sign = getSign(APPKEY, APPSECRET, params);

        String query = getQuery(APPKEY, sign, params);
        result=url+"?"+query;
        return result;
    }


    public static String getSign(String appkey, String appsecret, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        // 对参数名进行字典排序
        String[] keyArray = params.keySet().toArray(new String[0]);
        Arrays.sort(keyArray);
        // 拼接有序的参数名-值串
        stringBuilder.append(appkey);
        for (String key : keyArray) {
            stringBuilder.append(key).append(params.get(key));
        }
        String codes = stringBuilder.append(appsecret).toString();
        //在纯java环境下，利用codec对自负串
        // String sign = org.apache.commons.codec.digest.DigestUtils.shaHex(codes).toUpperCase();
        String sign = new String(Hex.encodeHex(DigestUtils.sha(codes))).toUpperCase();

        return sign;
    }

    private static String getQuery(String appkey, String sign, Map<String, String> params) {
        try {
            // 添加签名
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("appkey=").append(appkey).append("&sign=").append(sign);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuilder.append('&').append(entry.getKey()).append('=').append(URLEncoder.encode( entry.getValue(),"utf-8"));
            }
            String query = stringBuilder.toString();

            return query;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException("使用了不正确的字符串");
        }
    }

    public static void HttpURLConnection(){
        //获取符合要求的请求地址
        Map<String,String> params=new HashMap<>();
        params.put("city","北京");
        params.put("category","美食");
        final String url=getURL("http://api.dianping.com/v1/business/find_businesses",params);
        Log.i("TAG","url地址"+url);

        new Thread(){
            @Override
            public void run() {
                try {
                    super.run();
                    URL u=new URL(url);
                    HttpURLConnection connection= (HttpURLConnection) u.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream is = connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(is));
                    StringBuilder builder=new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        builder.append(line);
                    }
                    reader.close();
                    String respose=builder.toString();
                    Log.i("TAG", "HttpURLConnection获得的服务器响应内容："+respose);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public static void Volley(){
        /**
         * 1请求队列
         * 2请求对象
         * 3对象放到队列
         */
       VolleyClient.getINSTANCE().text();
    }

    public static void testRetrofit(){
     RetrofitClient.getINSTANCE().test();
    }
    public static  void getDailyList(String city, Response.Listener<IdList> listener){
        VolleyClient.getINSTANCE().getDailyAddList2(city,listener);

    }
    public static void loadImage(String url,ImageView iv){
        VolleyClient.getINSTANCE().loadImage(url,iv);
    }
    public static void displayImage(String url,ImageView iv){
        Picasso.with(MyApp.CONTEXT).load(url).placeholder(R.drawable.bucket_no_picture).error(R.drawable.bucket_no_picture).into(iv);
    }

    public static void getDailyListByRetrofit(String city,Callback<IdList> callback){
        RetrofitClient.getINSTANCE().getDailyDeals3(city,callback);
    }

    public static void getCityVolley(Response.Listener<String> listener){
        VolleyClient.getINSTANCE().getCityList(listener);
    }

    public static void getBusinessByRetrofit1(String city,String region, Callback<BusinessList> callback5){
        RetrofitClient.getINSTANCE().getBusinessRetrofit1(city,region,callback5);
    }
    public static void getBusinessByVolley(String city,Response.Listener<String> listener){
        VolleyClient.getINSTANCE().getBusinessVolley(city,listener);
    }



}
