package com.wdcloud.myrxjavaorretrofit.activity.Model;

import android.app.Dialog;

import java.util.Map;

/**
 * Created by Umbrella on 2019/1/10.
 */

public interface HomeFragmentModel {
    void initData();

    void getSelectSchoolData(Dialog dialog, Map<String,Object> map);//获取学校数据

    void getSelectClassData(Dialog dialog,Map<String,Object> map);//获取班级数据

    void getSelectNJData(Dialog dialog,Map<String,Object> map);//获取年级数据
}
