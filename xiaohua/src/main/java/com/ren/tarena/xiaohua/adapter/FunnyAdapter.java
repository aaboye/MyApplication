package com.ren.tarena.xiaohua.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.entity.Funny;
import com.ren.tarena.xiaohua.manager.MyImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarena on 2017/5/20.
 */

public class FunnyAdapter extends RecyclerView.Adapter<FunnyAdapter.ViewHolder> {

    private List<Funny.ShowapiResBodyBean.ContentlistBean> funnys=new ArrayList<Funny.ShowapiResBodyBean.ContentlistBean>();
    Context context;
    public FunnyAdapter( Context context) {

        this.context=context;
    }
    public void addFunnies(List<Funny.ShowapiResBodyBean.ContentlistBean> list,boolean isClear){
        //如果要清空原始数据
        if(isClear){
            this.funnys.clear();
            this.funnys.addAll(list);
        }else{
            //不清空原始数据
            this.funnys.addAll(0,list);
        }
        notifyDataSetChanged();

    }


    @Override
    public FunnyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_funny_list, parent, false);
        return new FunnyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_title.setText(funnys.get(position).getTitle());
        String imageUrl=funnys.get(position).getImg();
        MyImageLoader.setBitmapFromCache(context,holder.imageView,imageUrl);
        String str=funnys.get(position).getCt();
        str=str.substring(0,str.lastIndexOf(":"));
        holder.tv_date.setText(str);
    }

    @Override
    public int getItemCount() {
        return this.funnys.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView imageView;
        TextView tv_date;


        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title_inflate_fun);
            imageView = (ImageView) itemView.findViewById(R.id.image_inflate_fun);
            tv_date= (TextView) itemView.findViewById(R.id.date_inflate_fun);
        }
    }
}
