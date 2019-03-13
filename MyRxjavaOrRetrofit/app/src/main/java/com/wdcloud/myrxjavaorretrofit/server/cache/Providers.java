package com.wdcloud.myrxjavaorretrofit.server.cache;

import com.wdcloud.myrxjavaorretrofit.entity.NewLoginBean;
import com.wdcloud.myrxjavaorretrofit.entity.SchoolDataBean;
import com.wdcloud.myrxjavaorretrofit.server.api.ApiUrl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.LifeCache;
import io.rx_cache2.ProviderKey;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Umbrella on 2019/3/13.
 */

public interface Providers {
    @ProviderKey(ApiUrl.getSchools)
    @LifeCache(duration = ApiUrl.CACHE_TIME,timeUnit = TimeUnit.MINUTES)
    Observable<SchoolDataBean> getSchoolData(Observable<SchoolDataBean> observable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);
    @ProviderKey(ApiUrl.newlogincheck)
    @LifeCache(duration = ApiUrl.CACHE_TIME,timeUnit = TimeUnit.MINUTES)
    Observable<NewLoginBean> getNewLogin(Observable<NewLoginBean> observable, DynamicKey dynamicKey, EvictDynamicKey evictDynamicKey);
}
