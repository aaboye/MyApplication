package com.ren.tarena.xiaohua.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.ren.tarena.xiaohua.activity.MainActivity;
import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.adapter.FunnyAdapter;
import com.ren.tarena.xiaohua.adapter.GifAdapter;
import com.ren.tarena.xiaohua.entity.Funny;
import com.ren.tarena.xiaohua.entity.Gif;
import com.ren.tarena.xiaohua.entity.Tuijian;
import com.ren.tarena.xiaohua.manager.HttpManager;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GifFragment extends BaseFragment {

    private RecyclerView recycler_gif;
    private LinearLayoutManager layoutManager;
    SwipeRefreshLayout swipeRefreshLayout_gif;
    private GifAdapter adapter;

    public GifFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView= inflater.inflate(R.layout.fragment_gif, container, false);
        initialUI();
        //实现推荐界面侧滑功能
        imageGifCehua();
        return contentView;
    }


    //实现侧滑功能
    private void imageGifCehua() {
        ImageView imageView_left = (ImageView) contentView.findViewById(R.id.ab_left_imageID);
        imageView_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用mainactivity中的show方法
                MainActivity mainActivity = (MainActivity ) getActivity();
                mainActivity.show();
            }
        });
    }

    @Override
    public void initialUI() {

        actionbar= (LinearLayout) contentView.findViewById(R.id.actionbar_more);
        initialActionbar(R.mipmap.gerenzhongxin,"GIF图",R.mipmap.shuaxin);

        swipeRefreshLayout_gif= (SwipeRefreshLayout) contentView.findViewById(R.id.swipeRefresh_gif);
        swipeRefreshLayout_gif.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                swipeRefreshLayout_gif.setRefreshing(false);
            }
        });

        recycler_gif = (RecyclerView) contentView.findViewById(R.id.recyclerview_gif);
        // mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        //为了性能一般设置此选项
        recycler_gif.setHasFixedSize(true);
        //构建一个LayoutManager对象
        layoutManager = new LinearLayoutManager(getContext());

        //构建适配器，关联适配器
        recycler_gif.setLayoutManager(layoutManager);

        adapter = new GifAdapter(getContext());
        recycler_gif.setAdapter(adapter);

        refresh();
    }

    private void refresh() {
        HttpManager.loadGif(getContext(), new HttpManager.GifLoadListener() {
                    @Override
                    public void onGifLoadEnd(Gif gif) {
                        List<Gif.ShowapiResBodyBean.ContentlistBean> gifs = gif.getShowapi_res_body().getContentlist();
                        adapter.addGifs(gifs,true);
                    }
                }

        );
    }
}
