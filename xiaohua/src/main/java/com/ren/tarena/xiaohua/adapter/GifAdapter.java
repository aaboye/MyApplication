package com.ren.tarena.xiaohua.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ren.tarena.xiaohua.R;
import com.ren.tarena.xiaohua.entity.Gif;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarena on 2017/6/17.
 */

public class GifAdapter extends RecyclerView.Adapter<GifAdapter.ViewHolder> {
    ImageView image_inflate_gif;
    private List<Gif.ShowapiResBodyBean.ContentlistBean> gifs = new ArrayList<Gif.ShowapiResBodyBean.ContentlistBean>();
    Context context;
    public GifAdapter(Context context) {

        this.context = context;
    }
    public void addGifs(List<Gif.ShowapiResBodyBean.ContentlistBean> list, boolean isClear) {
        //如果要清空原始数据
        if (isClear) {
            this.gifs.clear();
            this.gifs.addAll(list);
        } else {
            //不清空原始数据
            this.gifs.addAll(0, list);
        }
        //刷新UI
        notifyDataSetChanged();

    }

    @Override
    public GifAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_gif_list, parent, false);

        return new GifAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GifAdapter.ViewHolder holder, int position) {

        holder.tv_title.setText(gifs.get(position).getTitle());
        String imageUrl=gifs.get(position).getImg();
        Glide.with(context).load(imageUrl).into(image_inflate_gif);
        String str=gifs.get(position).getCt();
        str=str.substring(0,str.lastIndexOf(":"));
        holder.tv_date.setText(str);

    }

    @Override
    public int getItemCount() {
        return gifs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;

        TextView tv_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title_inflate_gif);
            image_inflate_gif = (ImageView) itemView.findViewById(R.id.image_inflate_gif);
            tv_date = (TextView) itemView.findViewById(R.id.date_inflate_gif);

        }
    }
}
