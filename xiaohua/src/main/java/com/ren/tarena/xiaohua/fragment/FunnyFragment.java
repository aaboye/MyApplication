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
import com.ren.tarena.xiaohua.adapter.FunnyAdapter;
import com.ren.tarena.xiaohua.entity.Funny;
import com.ren.tarena.xiaohua.manager.HttpManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FunnyFragment extends BaseFragment {


    private FunnyAdapter funnyAdapter;
    private RecyclerView recyclerView_funny;


    private RecyclerView.LayoutManager layoutManager;
    private SwipeRefreshLayout swipeRefreshLayout_funny;

    public FunnyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_funny, container, false);
        initialUI();
        //实现推荐界面侧滑功能
        imageFunnyCehua();
        //下拉刷新
        setSwipeRefresh();


        return contentView;
    }


    private void setSwipeRefresh() {
        swipeRefreshLayout_funny = (SwipeRefreshLayout) contentView.findViewById(R.id.swipeRefresh_funny);
        swipeRefreshLayout_funny.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onRefresh() {
                refresh();
                swipeRefreshLayout_funny.setRefreshing(false);
            }

        });
    }

    //实现侧滑功能
    private void imageFunnyCehua() {
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


    @Override
    public void initialUI() {
        actionbar = (LinearLayout) contentView.findViewById(R.id.actionbar_funny);
        initialActionbar(R.mipmap.gerenzhongxin, "趣图", R.mipmap.shuaxin);

        recyclerView_funny = (RecyclerView) contentView.findViewById(R.id.recyclerview_funny);
        // mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        //为了性能一般设置此选项
        recyclerView_funny.setHasFixedSize(true);
        //构建一个LayoutManager对象
        layoutManager = new LinearLayoutManager(getContext());

        //构建适配器，关联适配器
        recyclerView_funny.setLayoutManager(layoutManager);

        funnyAdapter = new FunnyAdapter(getContext());
        recyclerView_funny.setAdapter(funnyAdapter);

        refresh();

    }

    private void refresh() {
        HttpManager.loadFunny(getContext(), new HttpManager.FunnyLoadListener() {
            @Override
            public void onFunnyLoadEnd(Funny funny) {
                List<Funny.ShowapiResBodyBean.ContentlistBean> funnys = funny.getShowapi_res_body().getContentlist();
                funnyAdapter.addFunnies(funnys, true);
            }
        });
    }

}
