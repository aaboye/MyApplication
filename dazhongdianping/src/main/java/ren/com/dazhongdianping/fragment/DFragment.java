package ren.com.dazhongdianping.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ren.com.dazhongdianping.R;
import ren.com.dazhongdianping.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class DFragment extends BaseFragment {


    public DFragment() {
    }

    @BindView(R.id.btn_fragment_skip)
    Button btn_skip;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_d, container, false);
        ButterKnife.bind(this, view);
        Skip(btn_skip);
        return view;
    }


}
