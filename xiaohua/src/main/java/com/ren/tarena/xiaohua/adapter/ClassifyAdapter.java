package com.ren.tarena.xiaohua.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ren.tarena.xiaohua.R;

/**
 * Created by tarena on 2017/5/18.
 */

public class ClassifyAdapter extends RecyclerView.Adapter<ClassifyAdapter.ViewHolder> {

    private String[] mDataset;
    private int[] mImages0;
    private int[] mImages1;
    private RecyclerView mRecyclerView;
    /**item对象监听器(自己写的)*/
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ClassifyAdapter(String[] myDataset,int[]images0,int[]images1) {

        mDataset = myDataset;
        mImages0=images0;
        mImages1=images1;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageview0;
        public ImageView mImageview1;


        public ViewHolder(View v) {
            super(v);

            mTextView= (TextView) v.findViewById(R.id.textview1ID);
            mImageview0= (ImageView) v.findViewById(R.id.imageviewID0);
            mImageview1= (ImageView) v.findViewById(R.id.imageviewID1);

        }
    }



    @Override
    public ClassifyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //初始化itemview对象
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflate_classify_list, parent, false);
        //添加item的监听事件
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=mRecyclerView.getChildAdapterPosition(v);
                onItemClickListener.onItemClick(mRecyclerView,v,position);
            }
        });

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset[position]);
       // Log.i("tag", "onBindViewHolder: "+ mImages[position]);
        holder.mImageview0.setImageResource(mImages0[position]);
        holder.mImageview1.setImageResource(mImages1[position]);
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    //关联recyclerview时执行
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView=recyclerView;
    }
    //取消关联recyclerview时执行
    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView=null;
    }
    /**定义RecyclerView中itemview的监听器接口*/
    public interface  OnItemClickListener{
        /**点击recyclerview中的item时执行此方法
         * @param  parent 指向recyclerview
         * @param  view 指向itemview
         * @param  position itemview在recyclerview中的位置
         * */
        public void onItemClick(RecyclerView parent,
                                View view,int position);
    }
}
