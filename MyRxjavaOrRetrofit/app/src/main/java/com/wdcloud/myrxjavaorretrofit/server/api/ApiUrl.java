package com.wdcloud.myrxjavaorretrofit.server.api;

/**
 * Created by Umbrella on 2018/12/6.
 */

public interface ApiUrl {
//    long CACHE_TIME = 1440 * 10;//10天
    long CACHE_TIME=3;
    String k12 = "http://rrt.wdcloud.cc/";
    String BaseUrl="http://192.168.8.24:8080/";
    String getAllBooks=BaseUrl+"MyWebProject/BookServlet";
    String newlogincheck ="http://login.qdeduyun.cn/ptyhzx-uic/rest/v1/users/check/account_passwd_return_loginid";
    String fetuser="http://sysadmin.wdcloud.cc/jyx-xtgl/rest/v2/jyxxtgl/fetchUserOrgInfoByLoginID";
    //    String FWDZ="http://mconf.12study.cn:8888/fw!getFWDZList.action";
    String userxx="http://usr.wdcloud.cc/ptyhzx-uic/rest/v1/users/showByLoginID";
    String getSchools =k12+"k12_lecture/bkxx/getSchoolList";//获取学校
    String getGrades =k12+"k12_lecture/bkxx/getGradeList";//获取年级
    String getClasss =k12+"k12_lecture/bkxx/getClassesList";//获取班级
}
