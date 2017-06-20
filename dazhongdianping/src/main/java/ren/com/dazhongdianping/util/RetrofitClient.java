package ren.com.dazhongdianping.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import ren.com.dazhongdianping.constant.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tarena on 2017/6/19.
 */

public class RetrofitClient {

    private static RetrofitClient INSTANCE;
    public synchronized static RetrofitClient getINSTANCE(){
        if (INSTANCE==null){
            synchronized (RetrofitClient.class){
                if (INSTANCE==null){
                    INSTANCE=new RetrofitClient();
                }
            }
        }


        return INSTANCE;
    }

    private Retrofit retrofit;
    private NetService netService;

    private RetrofitClient(){
        //创建Retrofit对象
        retrofit=new Retrofit.Builder().baseUrl(Constant.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create()).build();
        netService=retrofit.create(NetService.class);
    }
    public void test(){
        //创建接口的实体类对象
        Map<String, String> params=new HashMap<>();
        params.put("city","北京");
        params.put("category","美食");
        String sign=HttpUtil.getSign(HttpUtil.APPKEY,HttpUtil.APPSECRET,params);
        //获得请求对象
        Call<String> call = netService.test(HttpUtil.APPKEY, sign, params);
        //将请求对象放到请求队列中
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String string = response.body();
                Log.i("TAG","retrofit"+string);
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {

            }
        });

    }
}
