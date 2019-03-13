package com.wdcloud.myrxjavaorretrofit.activity.Model;

import android.app.Dialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umbrella on 2018/12/24.
 */

public interface BookModel {
    void initData();

    void getData(Dialog dialog);//获取数据
}
