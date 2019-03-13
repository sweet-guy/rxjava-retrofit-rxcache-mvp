package com.wdcloud.myrxjavaorretrofit.server.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by Umbrella on 2018/7/31.
 */

public class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request;
//        SharedPreferencesHelper instance = SharedPreferencesHelper.getInstance();
//        String token = (String) instance.getData("mytkesss", "");
//        String location = (String) instance.getData("location", "");
//        if(token.equals(""))
//        {
//            request = chain.request()
//                    .newBuilder()
////                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
////                .addHeader("Accept-Encoding", "gzip, deflate")
////                .addHeader("Connection", "keep-alive")
////                .addHeader("Accept", "*/*")
//                    .addHeader("location",location)//地区编号
//                    .addHeader("pl","android")
//                    .build();
//        }
//        else
//        {
            request = chain.request()
                    .newBuilder()
//                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
//                .addHeader("Accept-Encoding", "gzip, deflate")
//                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
//                    .addHeader("token", "")
//                    .addHeader("location","")//地区编号
//                    .addHeader("pl","android")
                    .build();
//        }
        return chain.proceed(request);
    }
}
