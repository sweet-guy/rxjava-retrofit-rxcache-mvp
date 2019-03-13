package com.wdcloud.myrxjavaorretrofit.activity.View;

import com.wdcloud.myrxjavaorretrofit.base.view.BaseMvpView;
import com.wdcloud.myrxjavaorretrofit.entity.NewLoginBean;

/**
 * Created by Umbrella on 2018/12/25.
 */

public interface NewLoginView extends BaseMvpView {
    void LoginSuccess(NewLoginBean newLoginBean);
}
