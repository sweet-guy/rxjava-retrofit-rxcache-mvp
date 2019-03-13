package com.wdcloud.myrxjavaorretrofit.server.interceptor;


import com.wdcloud.myrxjavaorretrofit.server.apiserver.ApiServer;
import com.wdcloud.myrxjavaorretrofit.util.net.SSLSocketClient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.wdcloud.myrxjavaorretrofit.server.cache.Repository;
import com.wdcloud.myrxjavaorretrofit.app.MyApplication;
/**
 * Created by Umbrella on 2018/5/18.
 */

public class HttpFactor {

    private HttpFactor() {

    }
    private static Repository repository;
    private static ApiServer apiService;

    //创建请求对象
    public static synchronized ApiServer getApiService(String baseUrl) {
        if (apiService == null){
            createApiService(baseUrl);
        }
        return apiService;
    }

    private static void createApiService(String baseUrl) {
        //日志拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS)
                .setLevel(HttpLoggingInterceptor.Level.BASIC)
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        SSLSocketClient sslSocketClient = new SSLSocketClient();
        LoggingInterceptor loggingInterceptor = new LoggingInterceptor();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(logging)
                .addInterceptor(new MyInterceptor())//拦截器 MyInterceptor  ComponInterceptor
                .connectTimeout(3, TimeUnit.SECONDS)//连接超时秒数
                .sslSocketFactory(sslSocketClient.getSSLSocketFactory())//忽略https安全策略
                .hostnameVerifier(sslSocketClient.getHostnameVerifier())//忽略https安全策略
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
        apiService = retrofit.create(ApiServer.class);
    }

//    创建请求对象   Rxchache缓存
    public static synchronized Repository getRepository(MyApplication appContext) {
        if (repository == null){
            createRepository(appContext);
        }
        return repository;
    }

    private static void createRepository(MyApplication appContext) {
        repository = new Repository(appContext);
    }
}
