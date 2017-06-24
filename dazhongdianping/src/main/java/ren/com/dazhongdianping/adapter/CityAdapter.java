package ren.com.dazhongdianping.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.entity.CitynameBean;

/**
 * Created by tarena on 2017/6/21.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> implements SectionIndexer{
    //声明基本的属性
    //上下文
    Context context;
    //数据源
    List<CitynameBean> dates;
    LayoutInflater inflater;
    //为recycler添加的条目监听
    OnItemClickListener listener;
    //为recycler添加的头部视图
    View headerView;
    private static final int HEADER=0;
    private static final int ITEM=1;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public void addHeaderView(View view){
        if (headerView==null) {
            this.headerView = view;
            notifyItemChanged(0);
        }else {
            throw new RuntimeException("不允许添加多个条目");
        }
    }
    public View getHeaderView(){
        return headerView;
    }


    //构造器中完成对属性的初始化
    public CityAdapter(Context context,List<CitynameBean> dates){
        this.context=context;
        this.dates=dates;
        this.inflater=LayoutInflater.from(context);

    }
    public void addAll(List<CitynameBean> list,boolean isClear){
        if (isClear){
            dates.clear();
        }
        dates.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (this.headerView!=null){
            if (position==0) {
                return HEADER;
            }else {
                return ITEM;
            }
        }else {
            return ITEM;
        }

    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType==HEADER){
            ViewHolder viewHolder=new ViewHolder(headerView);
            return viewHolder;
        }
        View view= inflater.inflate(R.layout.inflate_city_list_layout,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CityAdapter.ViewHolder holder, final int position) {
        if (headerView!=null&&position==0){
            return;
        }
        final int dataIndex=getDataIndex(position);
        CitynameBean cityName=dates.get(dataIndex);
        holder.tvName.setText(cityName.getCityName());
        holder.tvLetter.setText(cityName.getLetter()+"");
        //position这个位置的数据是不是该数据所属分组的起始位置
        if (dataIndex==getPositionForSection(getSectionForPosition(dataIndex))){
            holder.tvLetter.setVisibility(View.VISIBLE);
        }else {
            holder.tvLetter.setVisibility(View.GONE);
        }
        final View itemView=holder.itemView;
        if (this.listener!=null){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(view,dataIndex);
                }
            });
        }
    }

    private int getDataIndex(int position) {
        return headerView==null?position:position-1;
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    @Override
    public Object[] getSections() {
        return null;
    }
    //某一个分组的起始位置是什么
    @Override
    public int getPositionForSection(int i) {
        for (int k=0;k<dates.size();k++){
            if (dates.get(k).getLetter()==i){
                return k;
            }
        }
        return dates.size()+1;//返回一个下标不存在的值即可0或比0小
    }
    //第i位置上的数据的分组是什么
    @Override
    public int getSectionForPosition(int position) {
        return dates.get(position).getLetter();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Nullable
        @BindView(R.id.tv_city_list_A)
        TextView tvLetter;
        @Nullable
        @BindView(R.id.tv_city_list_city)
        TextView tvName;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface OnItemClickListener{
        void  onItemClick(View itemView,int position);
    }
}
