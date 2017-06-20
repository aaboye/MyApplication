package ren.com.dazhongdianping.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import ren.com.dazhongdianping.ui.MainActivity;

/**
 * Created by tarena on 2017/6/15.
 */

public class BaseFragment extends Fragment {
    public void Skip(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
}
