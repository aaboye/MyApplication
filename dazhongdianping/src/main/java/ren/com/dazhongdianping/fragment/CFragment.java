package ren.com.dazhongdianping.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class CFragment extends BaseFragment {


    public CFragment() {
        // Required empty public constructor
    }
    @BindView(R.id.tv_fragment_skip)
    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_c, container, false);
        ButterKnife.bind(this,view);
        Skip(tv);
        return view;
    }


}
