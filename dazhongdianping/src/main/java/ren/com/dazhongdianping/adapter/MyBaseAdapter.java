package ren.com.dazhongdianping.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by tarena on 2017/6/20.
 */

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<T> datas;

    public MyBaseAdapter(Context context,List<T> datas){
        this.context=context;
        this.datas=datas;
        this.inflater=LayoutInflater.from(context);
    }
    //追加新数据
    public void add(T t){
        datas.add(t);
        notifyDataSetChanged();
    }
    public void addAll(List<T> list,boolean isclear){
        if (isclear){
            datas.clear();
        }
        datas.addAll(list);
        notifyDataSetChanged();
    }
    public void removeAll(){
        datas.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public T getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


}
