package com.ren.tarena.xiaohua.fragment;


import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ren.tarena.xiaohua.activity.MainActivity;
import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.adapter.TuijianAdapter;
import com.ren.tarena.xiaohua.entity.Funny;
import com.ren.tarena.xiaohua.entity.Tuijian;
import com.ren.tarena.xiaohua.manager.HttpManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TuijianFragment extends BaseFragment {
    private TuijianAdapter tuijianAdapter;
    private RecyclerView recyclerView_tuijian;
    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout_tuijian;


    public TuijianFragment() {}


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_tuijian, container, false);


        //实现推荐界面侧滑功能
        imageTuijianCehua();
        //toolbar显示内容
        initialUI();
        //下拉刷新
        setSwipeRefresh();
        return contentView;
    }

    //下拉刷新
    private void setSwipeRefresh() {
        swipeRefreshLayout_tuijian = (SwipeRefreshLayout) contentView.findViewById(R.id.swipeRefresh_tuijian);
        swipeRefreshLayout_tuijian.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onRefresh() {
                refresh();
                swipeRefreshLayout_tuijian.setRefreshing(false);


            }

        });
    }


    //实现侧滑功能
    private void imageTuijianCehua() {
        ImageView imageView_left = (ImageView) contentView.findViewById(R.id.ab_left_imageID);

        imageView_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用mainactivity中的show方法
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void initialUI() {
        actionbar = (LinearLayout) contentView.findViewById(R.id.actionbar_joke);
        initialActionbar(R.mipmap.gerenzhongxin, "推荐", R.mipmap.shuaxin);

        recyclerView_tuijian = (RecyclerView) contentView.findViewById(R.id.recyclerview_tuijian);
        // mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        //为了性能一般设置此选项
        recyclerView_tuijian.setHasFixedSize(true);
        //构建一个LayoutManager对象
        layoutManager = new LinearLayoutManager(getContext());

        //构建适配器，关联适配器
        recyclerView_tuijian.setLayoutManager(layoutManager);

        tuijianAdapter = new TuijianAdapter(getContext());
        recyclerView_tuijian.setAdapter(tuijianAdapter);

        refresh();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void refresh() {
        HttpManager.loadTuijian(getContext(), new HttpManager.TuijianLoadListener() {
            @Override
            public void onTuijianLoadEnd(Tuijian tuijian) {
                List<Tuijian.ShowapiResBodyBean.ContentlistBean> tuijians=tuijian.getShowapi_res_body().getContentlist();
                tuijianAdapter.addTuijianes(tuijians,true);
            }
        });
    }
}
