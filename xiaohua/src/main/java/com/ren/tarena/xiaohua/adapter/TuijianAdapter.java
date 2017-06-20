package com.ren.tarena.xiaohua.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.entity.Funny;
import com.ren.tarena.xiaohua.entity.Tuijian;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tarena on 2017/5/25.
 */

public class TuijianAdapter extends RecyclerView.Adapter<TuijianAdapter.ViewHolder> {
    private List<Tuijian.ShowapiResBodyBean.ContentlistBean> tuijians = new ArrayList<Tuijian.ShowapiResBodyBean.ContentlistBean > ();
    Context context;

    public TuijianAdapter(Context context) {

        this.context = context;
    }

    public void addTuijianes(List<Tuijian.ShowapiResBodyBean.ContentlistBean> list, boolean isClear) {
        //如果要清空原始数据
        if (isClear) {
            this.tuijians.clear();
            this.tuijians.addAll(list);
        } else {
            //不清空原始数据
            this.tuijians.addAll(0, list);
        }
        //刷新UI
        notifyDataSetChanged();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_content;
        TextView tv_date;

        public ViewHolder(View v) {
            super(v);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title);
            tv_content= (TextView) itemView.findViewById(R.id.tv_content);
            tv_date= (TextView) itemView.findViewById(R.id.tv_date);

        }
    }


    @Override
    public TuijianAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_tuijian_list, parent, false);

        return new TuijianAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TuijianAdapter.ViewHolder holder, int position) {

        holder.tv_title.setText(tuijians.get(position).getTitle());
        holder.tv_content.setText(tuijians.get(position).getText());
        String str=tuijians.get(position).getCt();
        str=str.substring(0,str.lastIndexOf(":"));
        holder.tv_date.setText(str);
    }

    @Override
    public int getItemCount() {
        return tuijians.size();
    }
}
