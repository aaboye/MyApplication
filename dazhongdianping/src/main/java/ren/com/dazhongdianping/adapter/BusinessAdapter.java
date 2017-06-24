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
import ren.com.dazhongdianping.entity.BusinessList;
import ren.com.dazhongdianping.util.HttpUtil;

/**
 * Created by tarena on 2017/6/23.
 */

public class BusinessAdapter extends MyBaseAdapter<BusinessList.BusinessesBean> {

    int[] image=new int[]{R.drawable.movie_star10,R.drawable.movie_star20,R.drawable.movie_star30,R.drawable.movie_star35,
            R.drawable.movie_star40,R.drawable.movie_star45,R.drawable.movie_star50,};

    public BusinessAdapter(Context context, List<BusinessList.BusinessesBean> datas) {
        super(context, datas);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.inflate_business_listview, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        BusinessList.BusinessesBean list = getItem(i);
        int number=new Random().nextInt(7);
        int price=new Random().nextInt(200)+100;
        //呈现图片
        HttpUtil.displayImage(list.getPhoto_url(), holder.imageView);
        //名称
        String str = list.getName();
        String name = str.substring(0, str.indexOf("("));
        holder.textView_name.setText(name);

        String branchName = list.getBranch_name();
        if (branchName == "") {
            holder.textView_branchName.setText("" );
        }else {
            holder.textView_branchName.setText("(" + branchName + ")");
        }

        StringBuffer sb=new StringBuffer();
        for (int a=0;a<list.getRegions().size();a++){
            sb.append(list.getRegions().get(a)).append("/");
        }
        String regions=sb.substring(0,sb.length()-1);


        StringBuffer sb2=new StringBuffer();
        for (int a=0;a<list.getCategories().size();a++){
            sb2.append(list.getCategories().get(a)).append("/");

        }
        String catagories=sb2.substring(0,sb2.length()-1);

        holder.textView_category.setText(catagories);
        holder.textView_region.setText(regions);
        holder.textView_rmb.setText("￥"+price+"/人");
        holder.imageView_pingfen.setImageResource(image[number]);


        return view;
    }

    public class ViewHolder {
        @BindView(R.id.photo_item_business)
        ImageView imageView;
        @BindView(R.id.name_item_business)
        TextView textView_name;
        @BindView(R.id.branch_name_item_business)
        TextView textView_branchName;
        @BindView(R.id.pingfen_item_business)
        ImageView imageView_pingfen;
        @BindView(R.id.rmb_item_business)
        TextView textView_rmb;
        @BindView(R.id.region_item_business)
        TextView textView_region;
        @BindView(R.id.category_item_business)
        TextView textView_category;
        @BindView(R.id.distance_item_business)
        TextView textView_distance;

        public ViewHolder(View view) {

            ButterKnife.bind(this, view);
        }

    }
}
