package com.wdcloud.myrxjavaorretrofit.activity.Model;

import android.app.Dialog;

/**
 * Created by Umbrella on 2018/12/25.
 */

public interface FetUserModel {
    void initData();

    void getFetUserData(Dialog dialog,String loginid,String logintype);//获取数据
}
