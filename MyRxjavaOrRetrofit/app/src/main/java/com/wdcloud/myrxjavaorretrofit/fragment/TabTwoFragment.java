package com.wdcloud.myrxjavaorretrofit.fragment;

import android.widget.TextView;

import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by Umbrella on 2018/12/29.
 */

public class TabTwoFragment extends BaseFragment {
    @BindView(R.id.tab_text)
    TextView tabText;
    @Override
    protected int getContentView() {
        return R.layout.tab_layout;
    }

    @Override
    protected void init() {
        tabText.setText("Two");
    }
}
