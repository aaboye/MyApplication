package ren.com.dazhongdianping.util;

import android.text.TextUtils;
import android.util.Log;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import ren.com.dazhongdianping.constant.Constant;
import ren.com.dazhongdianping.entity.BusinessList;
import ren.com.dazhongdianping.entity.IdList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tarena on 2017/6/19.
 */

public class RetrofitClient {

    private static RetrofitClient INSTANCE;

    public synchronized static RetrofitClient getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (RetrofitClient.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitClient();
                }
            }
        }
        return INSTANCE;
    }

    private OkHttpClient okhttpClient;
    private Retrofit retrofit;
    private NetService netService;

    private RetrofitClient() {
        okhttpClient = new OkHttpClient.Builder().addInterceptor(new MyOkHttpInterceptor()).build();
        //创建Retrofit对象
       /* retrofit=new Retrofit.Builder().baseUrl(Constant.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();*/
        retrofit = new Retrofit.Builder().client(okhttpClient).baseUrl(Constant.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
        netService = retrofit.create(NetService.class);
    }

    public void test() {
        //创建接口的实体类对象
        Map<String, String> params = new HashMap<>();
        params.put("city", "北京");
        params.put("category", "美食");
        String sign = HttpUtil.getSign(HttpUtil.APPKEY, HttpUtil.APPSECRET, params);
        //获得请求对象
        Call<String> call = netService.test(HttpUtil.APPKEY, sign, params);
        //将请求对象放到请求队列中
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String string = response.body();
                Log.i("TAG", "retrofit" + string);
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

            }
        });

    }

    public void getDailyDeals(String city, final Callback<String> callback) {
        //先得到idlist，把idlist集合分成40一组的集合，已请求数据
        final Map<String, String> params = new HashMap<>();
        params.put("city", city);
        params.put("date", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
        String sign = HttpUtil.getSign(HttpUtil.APPKEY, HttpUtil.APPSECRET, params);
        Call<String> ids = netService.getDailyIds(HttpUtil.APPKEY, sign, params);
        ids.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    //Json解析idlist集合
                    JSONObject jsonObject = new JSONObject(response.body());
                    JSONArray array = jsonObject.getJSONArray("id_list");
                    //将获得的集合分组40一组
                    int size = array.length();
                    if (size > 40) {
                        size = 40;
                    }
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < size; i++) {
                        String id = array.getString(i);
                        sb.append(id).append(",");
                    }
                    if (sb.length() > 0) {
                        String idlist = sb.substring(0, sb.length() - 1);
                        Map<String, String> params1 = new HashMap<String, String>();
                        params1.put("deal_ids", idlist);
                        String sign1 = HttpUtil.getSign(HttpUtil.APPKEY, HttpUtil.APPSECRET, params1);
                        Call<String> call1 = netService.getDailyDeals(HttpUtil.APPKEY, sign1, params1);
                        call1.enqueue(callback);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

            }
        });

    }

    public void getDailyDeals2(String city, final Callback<IdList> callback2) {
        final Map<String, String> params = new HashMap<>();
        params.put("city", city);
        params.put("date", new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis()));
        final String sign = HttpUtil.getSign(HttpUtil.APPKEY, HttpUtil.APPSECRET, params);
        Call<String> ids = netService.getDailyIds(HttpUtil.APPKEY, sign, params);
        ids.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    JSONArray jsonArray = jsonObject.getJSONArray("id_list");

                    int size = jsonArray.length();
                    if (size > 40) {
                        size = 40;
                    }

                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < size; i++) {
                        String id = jsonArray.getString(i);
                        sb.append(id).append(",");
                    }

                    if (sb.length() > 0) {

                        String idlist = sb.substring(0, sb.length() - 1);
                        Map<String, String> params1 = new HashMap<String, String>();
                        params1.put("deal_ids", idlist);
                        String sign1 = HttpUtil.getSign(HttpUtil.APPKEY, HttpUtil.APPSECRET, params1);
                        Call<IdList> call1 = netService.getDailyDeals2(HttpUtil.APPKEY, sign1, params1);
                        call1.enqueue(callback2);
                    } else {
                        callback2.onResponse(null, null);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

            }
        });
    }

    /**
     * OKHTTP的拦截器
     */
    public class MyOkHttpInterceptor implements Interceptor {

        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            //获得请求对象
            Request request = chain.request();
            //请求路径
            //比如：http://baseurl/deal/get_daily_new_id_list?city=xxx&date=xxx
            HttpUrl url = request.url();
            //取出原有请求路径中的参数
            HashMap<String, String> params = new HashMap<>();
            //原有请求路径中，请求参数的名称
            //例如{city，date}
            Set<String> set = url.queryParameterNames();
            for (String key : set) {
                params.put(key, url.queryParameter(key));
            }
            String sign = HttpUtil.getSign(HttpUtil.APPKEY, HttpUtil.APPSECRET, params);
            //字符串形式的http://baseurl/deal/get_daily_new_id_list?city=xxx&date=xxx
            String urlString = url.toString();
            Log.i("TAG", "原始请求路径--->" + urlString);

            StringBuilder sb = new StringBuilder(urlString);
            if (set.size() == 0) {
                //意味着原有请求路径中没有参数
                sb.append("?");
            } else {
                sb.append("&");
            }

            sb.append("appkey=").append(HttpUtil.APPKEY);
            sb.append("&").append("sign=").append(sign);
            //http://baseurl/deal/get_daily_new_id_list?city=xxx&date=xxx&appkey=xxx&sign=xxx
            Log.d("TAG", "新的请求路径------>: " + sb.toString());
            Request newRequest = new Request.Builder().url(sb.toString()).build();


            return chain.proceed(newRequest);
        }
    }

    public void getDailyDeals3(String city, final Callback<IdList> callback2) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("city", city);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis());
        params.put("date", date);
        Call<String> idcall = netService.getDailyIds3(params);
        idcall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    JSONArray jsonArray = jsonObject.getJSONArray("id_list");

                    int size = jsonArray.length();
                    if (size > 40) {
                        size = 40;
                    }

                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < size; i++) {
                        String id = jsonArray.getString(i);
                        sb.append(id).append(",");
                    }

                    if (sb.length() > 0) {
                        String idlist = sb.substring(0, sb.length() - 1);

                        Map<String, String> params2 = new HashMap<String, String>();
                        params2.put("deal_ids", idlist);
                        Call<IdList> dealCall = netService.getDailyDeals3(params2);
                        dealCall.enqueue(callback2);
                    } else {
                        callback2.onResponse(null, null);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

            }
        });
    }


    public void getBusinessRetrofit1(String city, String region, final Callback<BusinessList> callback5) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("city", city);
        params.put("category", "美食");
        if (!TextUtils.isEmpty(region)) {
            params.put("region", region);
        }
        Call<BusinessList> idcall = netService.getBusiness(params);
        idcall.enqueue(callback5);
    }

}
