package com.wdcloud.myrxjavaorretrofit.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Umbrella on 2018/12/5.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    private Unbinder bind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentView(), container, false);
        bind = ButterKnife.bind(this, view);
        init();
        return view;
    }

    /* 设置布局id*/
    protected abstract int getContentView();

    /*初始化准备*/
    protected abstract void init();

    /**
     * 防止用户快速点击
     *
     * @return
     */
    private boolean fastClick() {
        long lastClick = 0;
        if (System.currentTimeMillis() - lastClick <= 2000) {
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    @Override
    public void onClick(View v) {
        if (fastClick())
            widgetClick(v);
    }

    /**
     * View点击
     **/
    public void widgetClick(View v) {
    }
    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }
    public void showToastMessage(String message) {
        getBaseActivity().showToastMessage(message);
    }
    /**
     * SmartRefreshLayout 停止刷新
     **/
//    public void finishLoadRefresh(SmartRefreshLayout refreshLayout) {
//        if (refreshLayout != null){
//            if (refreshLayout.isRefreshing()) {
//                refreshLayout.finishRefresh();
//            } else if (refreshLayout.isLoading()) {
//                refreshLayout.finishLoadmore();
//            }
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
