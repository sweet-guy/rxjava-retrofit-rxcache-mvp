package com.wdcloud.myrxjavaorretrofit.activity.Model;

import android.app.Dialog;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Umbrella on 2018/12/25.
 */

public interface LoginModel{
    void initData();

    void getLoginData(Dialog dialog,String account,String password,String yyxtdm,String yddbbh);//获取数据
//    void getSchools(Map<String,Object> map);
//    void getGrades(Map<String,Object> map);
//    void getClass(Map<String,Object> map);
}
