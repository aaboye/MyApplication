package ren.com.dazhongdianping.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.entity.IdList;
import ren.com.dazhongdianping.util.HttpUtil;

/**
 * Created by tarena on 2017/6/20.
 */

public class DealAdapter extends MyBaseAdapter<IdList.Deal> {


    public DealAdapter(Context context, List<IdList.Deal> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            view=inflater.inflate(R.layout.inflate_main_listview_layout,viewGroup,false);
            holder=new ViewHolder(view);
            view.setTag(holder);

        }else {
            holder= (ViewHolder) view.getTag();
        }
        IdList.Deal deal=getItem(i);
        //呈现图片
       // HttpUtil.loadImage(deal.getS_image_url(),holder.ivPic);
        HttpUtil.displayImage(deal.getS_image_url(),holder.ivPic);

        holder.tvTitle.setText(deal.getTitle());
        holder.tvDetail.setText(deal.getDescription());
        holder.tvPrice.setText(deal.getList_price()+"");
        Random random=new Random();
        int count=random.nextInt(2000)+500;
        holder.tvCount.setText("已售出"+count);
        //  距离 holder.tvDistance.setText("");

        return view;
    }

    public class ViewHolder {
        @BindView(R.id.imageView_listView_main)
        ImageView ivPic;
        @BindView(R.id.textView_name_listView_main)
        TextView tvTitle;
        @BindView(R.id.textView_content_listView_main)
        TextView tvDetail;
        @BindView(R.id.textView_distance_listView_main)
        TextView tvDistance;
        @BindView(R.id.RMB_listView_main)
        TextView tvPrice;
        @BindView(R.id.count_listView_main)
        TextView tvCount;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
    }

    }
}
