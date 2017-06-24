package ren.com.dazhongdianping.util;

import java.util.Map;

import ren.com.dazhongdianping.entity.BusinessList;
import ren.com.dazhongdianping.entity.IdList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by tarena on 2017/6/19.
 */

public interface NetService {
    @GET("business/find_businesses")
    public Call<String> test(@Query("appkey") String appkey, @Query("sign") String sign, @QueryMap Map<String, String> params);

    @GET("business/find_businesses")
    public Call<BusinessList> getBusiness(@QueryMap Map<String, String> params);

    @GET("deal/get_daily_new_id_list")
    public Call<String> getDailyIds3(@QueryMap Map<String,String> params);

    @GET("deal/get_batch_deals_by_id")
    public Call<IdList> getDailyDeals3(@QueryMap Map<String,String> params);



    @GET("deal/get_daily_new_id_list")
    public Call<String> getDailyIds(@Query("appkey") String appkey, @Query("sign") String sign, @QueryMap Map<String, String> params);

    @GET("deal/get_batch_deals_by_id")
    public Call<String> getDailyDeals(@Query("appkey") String appkey, @Query("sign") String sign, @QueryMap Map<String, String> params);

    @GET("deal/get_batch_deals_by_id")
    public Call<IdList> getDailyDeals2(@Query("appkey") String appkey, @Query("sign") String sign, @QueryMap Map<String, String> params);

}
