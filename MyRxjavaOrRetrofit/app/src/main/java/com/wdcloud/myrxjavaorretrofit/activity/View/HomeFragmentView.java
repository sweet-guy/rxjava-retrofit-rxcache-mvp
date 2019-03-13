package com.wdcloud.myrxjavaorretrofit.activity.View;

import com.wdcloud.myrxjavaorretrofit.base.view.BaseMvpView;
import com.wdcloud.myrxjavaorretrofit.entity.ClassResultBean;
import com.wdcloud.myrxjavaorretrofit.entity.MyBooks;
import com.wdcloud.myrxjavaorretrofit.entity.NjIInfoBean;
import com.wdcloud.myrxjavaorretrofit.entity.SchoolDataBean;
import com.wdcloud.myrxjavaorretrofit.entity.SchoolDataResult;

/**
 * Created by Umbrella on 2019/1/10.
 */

public interface HomeFragmentView extends BaseMvpView{
    void onSchoolSuccess(SchoolDataResult schoolDataResult);
    void onClassSuccess(ClassResultBean classResultBean);
    void onNJSuccess(NjIInfoBean njIInfoBean);
}
