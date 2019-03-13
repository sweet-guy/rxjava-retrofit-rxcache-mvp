package com.wdcloud.myrxjavaorretrofit.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.wdcloud.myrxjavaorretrofit.util.GreenDaoManager;
import com.wdcloud.myrxjavaorretrofit.server.interceptor.HttpFactor;
import com.wdcloud.myrxjavaorretrofit.server.cache.Repository;
/**
 * Created by Umbrella on 2018/12/5.
 */

public class MyApplication extends MultiDexApplication {
    private static MyApplication instance;
    private static Repository repository;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
        instance = this;
        Fresco.initialize(this);
        GreenDaoManager.init(this);
        Logger.addLogAdapter(new AndroidLogAdapter());
    }

    private void init() {
        repository = HttpFactor.getRepository(this);//本地缓存对象
    }
    public static Repository getRepository()
    {
        return repository;
    }
    public static MyApplication getInstance() {
        return instance;
    }
    /**
     * 分割 Dex 支持
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
