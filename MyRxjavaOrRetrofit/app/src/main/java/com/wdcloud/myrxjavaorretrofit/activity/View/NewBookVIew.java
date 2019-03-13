package com.wdcloud.myrxjavaorretrofit.activity.View;

import com.wdcloud.myrxjavaorretrofit.base.view.BaseMvpView;
import com.wdcloud.myrxjavaorretrofit.entity.MyBooks;

/**
 * Created by Umbrella on 2018/12/24.
 */

public interface NewBookVIew extends BaseMvpView{
    void onBookSuccess(MyBooks book);
}
