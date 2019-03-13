package com.wdcloud.myrxjavaorretrofit.server.interceptor;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.wdcloud.myrxjavaorretrofit.util.longtodate.LongDate;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by Umbrella on 2018/12/10.
 */

public class LoggingInterceptor implements Interceptor{
    String TAG = "LoggingInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.Response response = chain.proceed(chain.request());
        HttpUrl url = response.request().url();
        String curretdate= LongDate.longToDate();
        Logger.e("请求时间："+curretdate+"\n请求的URL:"+url.toString());
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.e(TAG, "response body:" + content);
        Logger.json(content);
        return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
    }
}
