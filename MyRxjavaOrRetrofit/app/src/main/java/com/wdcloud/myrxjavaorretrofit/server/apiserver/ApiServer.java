package com.wdcloud.myrxjavaorretrofit.server.apiserver;

import com.wdcloud.myrxjavaorretrofit.entity.ClassBean;
import com.wdcloud.myrxjavaorretrofit.entity.FetUserBean;
import com.wdcloud.myrxjavaorretrofit.entity.MyBooks;
import com.wdcloud.myrxjavaorretrofit.entity.NewLoginBean;
import com.wdcloud.myrxjavaorretrofit.entity.NewSchoolBean;
import com.wdcloud.myrxjavaorretrofit.entity.NjBean;
import com.wdcloud.myrxjavaorretrofit.entity.SchoolDataBean;
import com.wdcloud.myrxjavaorretrofit.server.api.ApiUrl;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Umbrella on 2018/12/5.
 */

public interface ApiServer {
    @GET(ApiUrl.getAllBooks)
    Observable<MyBooks> getAllBooks();
    @POST(ApiUrl.newlogincheck)
    Observable<NewLoginBean> getnewLogin(@Query("account") String account, @Query("password") String password, @Query("yyxtdm") String yyxtdm, @Query("yddbbh") String yddbbh);
    @GET(ApiUrl.fetuser)
    Observable<FetUserBean> getFetUser(@Query("loginId") String loginid, @Query("userType") String userType);
    @POST(ApiUrl.getSchools)
    Observable<SchoolDataBean> getSchoolData(@Body Map<String, Object> map);
    @POST(ApiUrl.getGrades)
    Observable<ClassBean> getClassData(@Body Map<String, Object> map);
    @POST(ApiUrl.getClasss)
    Observable<NjBean> getNJData(@Body Map<String, Object> map);
}
