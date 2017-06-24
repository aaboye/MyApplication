package ren.com.dazhongdianping.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.adapter.CityAdapter;
import ren.com.dazhongdianping.app.MyApp;
import ren.com.dazhongdianping.entity.CityBean;
import ren.com.dazhongdianping.entity.CitynameBean;
import ren.com.dazhongdianping.util.DBUtil;
import ren.com.dazhongdianping.util.HttpUtil;
import ren.com.dazhongdianping.util.PinYinUtil;
import ren.com.dazhongdianping.view.MyLetterView;

public class CityActivity extends AppCompatActivity {

    @BindView(R.id.recycler_city)
    RecyclerView recyclerView;
    CityAdapter adapter;
    List<CitynameBean> dates;
    private DBUtil dbUtil;
    @BindView(R.id.myLetter_View)
    MyLetterView myLetterView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        try {
            dbUtil = new DBUtil(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ButterKnife.bind(this);
        initRecyclerView();

        myLetterView.setOnTouchTetterListener(new MyLetterView.OnTouchLetterListener() {
            @Override
            public void onTouchLetter(MyLetterView view, String letter) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if ("热门".equals(letter)) {
                    manager.scrollToPosition(0);
                } else {
                    int position = adapter.getPositionForSection(letter.charAt(0));
                    if(adapter.getHeaderView()!=null){
                        position += 1;
                    }
                    //RecyclerView移动到第position个视图位置
                    //且该视图位于当前RecyclerView最顶端
                    //当移动完毕后，如何设置offset值(非0)，则偏移offset个像素
                    //如果大于0就往下偏移，如果小于0就往上偏移
                    manager.scrollToPositionWithOffset(position,0);

                }
            }
        });

    }

    private void initRecyclerView() {
        //初始化数据源，适配器
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        dates = new ArrayList<>();
        adapter = new CityAdapter(this, dates);

        recyclerView.setAdapter(adapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.inflate_city_header_list, recyclerView, false);
        adapter.addHeaderView(headerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position) {
                CitynameBean citynameBean = dates.get(position);
                Toast.makeText(CityActivity.this, citynameBean.getCityName(), Toast.LENGTH_SHORT).show();
                String city = citynameBean.getCityName();
               /* Intent intent=new Intent(CityActivity.this,MainActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);*/
                Intent data = new Intent();
                data.putExtra("city", city);
                setResult(RESULT_OK, data);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {
        //优先从内存缓存中读取数据
        if (MyApp.citynameBeanList != null && MyApp.citynameBeanList.size() > 0) {
            adapter.addAll(MyApp.citynameBeanList, true);
            return;
        }


        HttpUtil.getCityVolley(new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Gson gson = new Gson();
                CityBean cityBean = gson.fromJson(s, CityBean.class);
                List<String> dates = cityBean.getCities();
                //根据List<String>创建一个List<CitynameBean>
                //将List<CitynameBean>放到RecyclerView中显示
                final List<CitynameBean> citynameBeanList = new ArrayList<CitynameBean>();
                for (String name : dates) {
                    if (!name.equals("全国") && !name.equals("其它城市") && !name.equals("点评实验室")) {
                        CitynameBean citynameBean = new CitynameBean();
                        citynameBean.setCityName(name);
                        citynameBean.setPyName(PinYinUtil.getPinYin(name));
                        citynameBean.setLetter(PinYinUtil.getLetter(name));
                        //Log.d("TAG", "创建的CitynameBean内容： " + citynameBean);

                        citynameBeanList.add(citynameBean);
                    }
                }
                Collections.sort(citynameBeanList, new Comparator<CitynameBean>() {
                    @Override
                    public int compare(CitynameBean t1, CitynameBean t2) {
                        return t1.getPyName().compareTo(t2.getPyName());
                    }
                });
                adapter.addAll(citynameBeanList, true);
                //将数据缓存起来
                MyApp.citynameBeanList = citynameBeanList;
                //向数据库中写入城市数据
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        long start = System.currentTimeMillis();
                        dbUtil.insertBatch(citynameBeanList);
                        Log.d("TAG", "写入数据库完毕，耗时：" + (System.currentTimeMillis() - start));

                    }
                }.start();

            }

        });
    }

    @OnClick(R.id.search_actionbar)
    public void jumpTo(View view) {

        Intent intent = new Intent(CityActivity.this, SearchActivity.class);
        startActivityForResult(intent, 101);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 101) {
            //data中取出搜索后点击的城市名称
//            Intent data2 = new Intent();
//            String city = data.getStringExtra("city");
//            data2.putExtra("city",city);
            setResult(RESULT_OK, data);
            finish();
        }
    }


}
