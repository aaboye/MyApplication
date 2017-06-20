package ren.com.dazhongdianping.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.adapter.DealAdapter;
import ren.com.dazhongdianping.entity.IdList;
import ren.com.dazhongdianping.util.HttpUtil;

public class MainActivity extends AppCompatActivity {

    //头部
    @BindView(R.id.city_actionbar)
    LinearLayout cityContainer;
    @BindView(R.id.textView_city_actionbar)
    TextView tv_city;
    @BindView(R.id.imageAdd_action)
    ImageView ivAdd;
    @BindView(R.id.menu_layout)
    View menuLayout;


    //中段
    @BindView(R.id.ptrlv_main)
    PullToRefreshListView ptrListView;

    ListView listView;
    List<IdList.Deal> datas;
    DealAdapter adapter;

    //脚部

    RadioGroup rg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListView();

    }

    @OnClick(R.id.city_actionbar)
    public void jumpToCity(View view) {
        Intent intent = new Intent(this, CityActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.imageAdd_action)
    public void toggleMenu(View view) {
        if (menuLayout.getVisibility() == View.VISIBLE) {
            menuLayout.setVisibility(View.INVISIBLE);
        } else {
            menuLayout.setVisibility(View.VISIBLE);
        }

    }


    private void initListView() {
        listView = ptrListView.getRefreshableView();
        datas = new ArrayList<IdList.Deal>();
        adapter = new DealAdapter(this, datas);
        //为listview添加若干个头部
        LayoutInflater inflater = LayoutInflater.from(this);

        View listHearderIcons = inflater.inflate(R.layout.header_list_icons, listView, false);
        View listHeaderSquares = inflater.inflate(R.layout.header_list_square, listView, false);
        View listHeaderAds = inflater.inflate(R.layout.header_list_ads, listView, false);
        View listHeaderCategories = inflater.inflate(R.layout.header_list_categories, listView, false);
        View listHeaderRecommend = inflater.inflate(R.layout.main_jingcaituijian_layout, listView, false);

        listView.addHeaderView(listHearderIcons);
        listView.addHeaderView(listHeaderSquares);
        listView.addHeaderView(listHeaderAds);
        listView.addHeaderView(listHeaderCategories);
        listView.addHeaderView(listHeaderRecommend);


        listView.setAdapter(adapter);


        initListHeaderIcons(listHearderIcons);

        //添加下拉送售后刷新
        ptrListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                /*new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.add(0, "hfdgs");
                        adapter.notifyDataSetChanged();
                        ptrListView.onRefreshComplete();
                    }
                }, 1500);*/
                refresh();
            }
        });
        //设置标题的变化
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == 0) {
                    cityContainer.setVisibility(View.VISIBLE);
                    ivAdd.setVisibility(View.VISIBLE);
                } else {
                    cityContainer.setVisibility(View.GONE);
                    ivAdd.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initListHeaderIcons(View listHearderIcons) {
        final ViewPager viewPager = (ViewPager) listHearderIcons.findViewById(R.id.vp_header_list_icons);
        final PagerAdapter adapter = new PagerAdapter() {

            int[] resIDs = new int[]{
                    R.layout.inflate_maintopa_layout,
                    R.layout.inflate_maintopb_layout,
                    R.layout.inflate_maintopc_layout,

            };

            @Override
            public int getCount() {
                return 30000;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                int layoutId = resIDs[position % 3];
                View view = LayoutInflater.from(MainActivity.this).inflate(layoutId, viewPager, false);
                container.addView(view);
                return view;

            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(15000);

        final ImageView iv1 = (ImageView) listHearderIcons.findViewById(R.id.iv_header_list_icons_indicator1);
        final ImageView iv2 = (ImageView) listHearderIcons.findViewById(R.id.iv_header_list_icons_indicator2);
        final ImageView iv3 = (ImageView) listHearderIcons.findViewById(R.id.iv_header_list_icons_indicator3);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                iv1.setImageResource(R.drawable.banner_dot);
                iv2.setImageResource(R.drawable.banner_dot);
                iv3.setImageResource(R.drawable.banner_dot);

                switch (position % 3) {
                    case 0:
                        iv1.setImageResource(R.drawable.banner_dot_pressed);
                        break;
                    case 1:
                        iv2.setImageResource(R.drawable.banner_dot_pressed);
                        break;
                    case 2:
                        iv3.setImageResource(R.drawable.banner_dot_pressed);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }

    private void refresh() {

        adapter.notifyDataSetChanged();

        //HttpUtil.HttpURLConnection();
        // HttpUtil.Volley();
        //HttpUtil.testRetrofit();
        HttpUtil.getDailyList(tv_city.getText().toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (s != null) {
                    Gson gson = new Gson();
                    IdList list = gson.fromJson(s, IdList.class);
                    List<IdList.Deal> deals = list.getDeals();
                    adapter.addAll(deals, true);
                } else {
                    //今日无新增团购内容
                    Toast.makeText(MainActivity.this, "今日无新增团购内容", Toast.LENGTH_SHORT).show();

                }
                //刷新
                ptrListView.onRefreshComplete();
            }
        });
    }
}
