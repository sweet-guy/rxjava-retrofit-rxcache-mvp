package com.wdcloud.myrxjavaorretrofit.activity.View;

import com.wdcloud.myrxjavaorretrofit.base.view.BaseMvpView;
import com.wdcloud.myrxjavaorretrofit.entity.FetUserBean;

/**
 * Created by Umbrella on 2018/12/25.
 */

public interface NewFetUserView extends BaseMvpView {
    void onFetUserSuccess(FetUserBean fetUserBean);
}
