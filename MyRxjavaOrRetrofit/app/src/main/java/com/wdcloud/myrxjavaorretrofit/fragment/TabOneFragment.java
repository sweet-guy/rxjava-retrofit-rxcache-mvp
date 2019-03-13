package com.wdcloud.myrxjavaorretrofit.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Umbrella on 2018/12/29.
 */

public class TabOneFragment extends BaseFragment {
    @BindView(R.id.tab_text)
    TextView tabText;
    @Override
    protected int getContentView() {
        return R.layout.tab_layout;
    }

    @Override
    protected void init() {
        tabText.setText("One");
    }
}
