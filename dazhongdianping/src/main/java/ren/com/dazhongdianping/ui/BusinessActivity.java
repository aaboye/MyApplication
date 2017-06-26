package ren.com.dazhongdianping.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.adapter.BusinessAdapter;
import ren.com.dazhongdianping.entity.BusinessList;
import ren.com.dazhongdianping.entity.IdList;
import ren.com.dazhongdianping.util.HttpUtil;
import ren.com.dazhongdianping.util.SharePreferenceUtil;
import ren.com.dazhongdianping.view.MyBanner;
import retrofit2.Call;
import retrofit2.Callback;

public class BusinessActivity extends AppCompatActivity {
    String city;
    @BindView(R.id.listView_business)
    ListView listView;
    BusinessAdapter adapter;
    List<BusinessList.BusinessesBean> datas;
    SharePreferenceUtil sharePreferenceUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        ButterKnife.bind(this);
        city=getIntent().getStringExtra("city");
        sharePreferenceUtil=new SharePreferenceUtil(this);
        initListView();

    }

    private void initListView() {
        datas=new ArrayList<>();
        adapter=new BusinessAdapter(this,datas);
        listView.setAdapter(adapter);
        if (!sharePreferenceUtil.isCloseBanner()) {
            final MyBanner myBanner = new MyBanner(this, null);
            myBanner.OnCloseBannerListener(new MyBanner.OnCloseBannerListener() {
                @Override
                public void onClose() {
                    sharePreferenceUtil.setCloseBanner(true);
                    listView.removeHeaderView(myBanner);
                }
            });
            listView.addHeaderView(myBanner);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        HttpUtil.getBusinessByRetrofit1(city,null, new Callback<BusinessList>() {
            @Override
            public void onResponse(Call<BusinessList> call, retrofit2.Response<BusinessList> response) {
                BusinessList businessList=response.body();
                List<BusinessList.BusinessesBean> businessesBeen=businessList.getBusinesses();
                adapter.addAll(businessesBeen,true);
            }

            @Override
            public void onFailure(Call<BusinessList> call, Throwable throwable) {

            }
        });
    }
}
