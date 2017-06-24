package ren.com.dazhongdianping.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnTextChanged;
import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.app.MyApp;
import ren.com.dazhongdianping.entity.CitynameBean;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.listView_search)
    ListView listView;
    List<String> cities;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        initListView();
    }

    private void initListView() {
        cities=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,cities);
        listView.setAdapter(adapter);
    }

    @OnTextChanged(R.id.et_search)
    public void search(Editable editable){
        if(editable.length()==0){
            cities.clear();
            adapter.notifyDataSetChanged();
        }else{
            searchCities(editable.toString().toUpperCase());
        }
    }

    /**
     * 根据输入的内容
     * 筛选符合的城市名称
     * @param s
     */
    private void searchCities(String s) {

        List<String> temps = new ArrayList<String>();
        //中文 char 16bit 0-65535
        if(s.matches("[\u4e00-\u9fff]+")){

            for(CitynameBean bean:MyApp.citynameBeanList){
                if(bean.getCityName().contains(s)){
                    temps.add(bean.getCityName());
                }
            }
        }else{//拼音
            for(CitynameBean c: MyApp.citynameBeanList){
                if(c.getPyName().contains(s)){
                    temps.add(c.getCityName());
                }
            }
        }
        if(temps.size() > 0){
            cities.clear();
            cities.addAll(temps);
            adapter.notifyDataSetChanged();
        }
    }
    @OnItemClick(R.id.listView_search)
    public void selectCity(AdapterView<?> adapterView, View view, int i, long l){

        Intent data = new Intent();
        String city = adapter.getItem(i);
        data.putExtra("city",city);
        setResult(RESULT_OK,data);
        finish();

    }


}
