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
import ren.com.dazhongdianping.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends BaseFragment {
    public AFragment() {
    }

    @BindView(R.id.tv_fragment_skip)
    TextView tv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        ButterKnife.bind(this, view);
        Skip(tv);
        return view;
    }


}
