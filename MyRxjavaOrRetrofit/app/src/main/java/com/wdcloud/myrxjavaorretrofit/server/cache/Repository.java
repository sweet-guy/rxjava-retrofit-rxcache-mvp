package com.wdcloud.myrxjavaorretrofit.server.cache;

import android.content.Context;

import com.wdcloud.myrxjavaorretrofit.entity.NewLoginBean;
import com.wdcloud.myrxjavaorretrofit.entity.SchoolDataBean;

import java.io.File;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.DynamicKeyGroup;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Created by Umbrella on 2019/3/13.
 */

public class Repository {
    private Providers providers;
    //初始化RxCache的Provider
    public Repository(Context context) {
        providers = new RxCache.Builder()
                .useExpiredDataIfLoaderNotAvailable(true)
                .persistence(getCacheDir(context), new GsonSpeaker())
                .using(Providers.class);
    }
    private File getCacheDir(Context context) {
        return context.getCacheDir();
    }
    public Observable<SchoolDataBean> getAllLessons(Observable<SchoolDataBean> observable, int page, boolean update) {
//        new DynamicKeyGroup(filter, page), new EvictDynamicKey(updateFilter)//记得替换因为是按用户分的
        return providers.getSchoolData(observable, new DynamicKey(page), new EvictDynamicKey(update));
    }
    public Observable<NewLoginBean> getNewLogin(Observable<NewLoginBean> observable, int page, boolean update) {
//        new DynamicKeyGroup(filter, page), new EvictDynamicKey(updateFilter)//记得替换因为是按用户分的
        return providers.getNewLogin(observable, new DynamicKey(page), new EvictDynamicKey(update));
    }
}
