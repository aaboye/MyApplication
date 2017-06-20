package com.ren.tarena.xiaohua.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ren.tarena.xiaohua.activity.MainActivity;
import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.activity.WebActivity;
import com.ren.tarena.xiaohua.adapter.ClassifyAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassifyFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private ClassifyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String[] web={"http://www.toutiao.com/a6421104262118228225/",
            "http://m.lengxiaohua.com/joke/227175/",
            "http://m.lengxiaohua.com/joke/227175/","https://www.baidu.com/",
            "https://www.baidu.com/","https://www.baidu.com/","https://www.baidu.com/"};

    String[] myDataset = {"幽默笑话", "内涵男女", "冷笑话", "爆笑糗事", "儿童笑话", "校园笑话", "职场笑话"};
    int[] images0 = {R.mipmap.integral, R.mipmap.integral, R.mipmap.integral, R.mipmap.integral,
            R.mipmap.integral, R.mipmap.integral, R.mipmap.integral,};
    int[] images1 = {R.mipmap.right, R.mipmap.right, R.mipmap.right, R.mipmap.right,
            R.mipmap.right, R.mipmap.right, R.mipmap.right,};


    public ClassifyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contentView = inflater.inflate(R.layout.fragment_classify, container, false);
        initialUI();

        //实现推荐界面侧滑功能
        imageClassfyCehua();

        mRecyclerView = (RecyclerView) contentView.findViewById(R.id.my_recycler_view);
        // mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        //为了性能一般设置此选项
        mRecyclerView.setHasFixedSize(true);
        //构建一个LayoutManager对象
        mLayoutManager = new LinearLayoutManager(getContext());

        //构建适配器，关联适配器
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ClassifyAdapter(myDataset, images0, images1);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ClassifyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Intent intent=new Intent(getActivity(), WebActivity.class);
                String a = web[position];
                intent.putExtra("web",a);
                startActivity(intent);

            }
        });




        return contentView;
    }

    //实现侧滑功能
    private void imageClassfyCehua() {
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
        actionbar = (LinearLayout) contentView.findViewById(R.id.actionbar_classify);
        initialActionbar(R.mipmap.gerenzhongxin, "分类", -1);

    }
}


