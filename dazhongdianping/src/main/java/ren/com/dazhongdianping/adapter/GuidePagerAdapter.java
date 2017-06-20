package ren.com.dazhongdianping.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import ren.com.dazhongdianping.fragment.AFragment;
import ren.com.dazhongdianping.fragment.BFragment;
import ren.com.dazhongdianping.fragment.CFragment;
import ren.com.dazhongdianping.fragment.DFragment;


/**
 * Created by tarena on 2017/6/15.
 */

public class GuidePagerAdapter extends FragmentPagerAdapter {
    List<Fragment> fragmentList;
    public GuidePagerAdapter(FragmentManager fm) {
        super(fm);
        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(new AFragment());
        fragmentList.add(new BFragment());
        fragmentList.add(new CFragment());
        fragmentList.add(new DFragment());
    }

    @Override
    public Fragment getItem(int position) {
       return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
