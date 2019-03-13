package com.wdcloud.myrxjavaorretrofit.base.presenter;

import com.wdcloud.myrxjavaorretrofit.base.view.BaseMvpView;

/**
 * Created by Umbrella on 2018/12/24.
 */

public interface BasePresenter<T extends BaseMvpView>  {

    /**
     * presenter和对应的view绑定
     *
     * @param mvpView 目标view
     */
    void attachView(T mvpView);

    /**
     * presenter与view解绑
     */
    void detachView();
}
