package com.wdcloud.myrxjavaorretrofit.util;

import android.content.Context;

import com.com.wdcloud.myrxjavaorretrofit.greendao.gen.DaoMaster;
import com.com.wdcloud.myrxjavaorretrofit.greendao.gen.DaoSession;

/**
 * Created by Umbrella on 2018/12/10.
 */

public class GreenDaoManager {
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;
    private static volatile GreenDaoManager mInstance = null;
    private static final String DB_NAME = "GreenDao.db";

    private GreenDaoManager() {
    }

    public static void init(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new
                DaoMaster.DevOpenHelper(context, DB_NAME);
        mDaoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
    }

    public static GreenDaoManager getInstance() {
        if (mInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (mInstance == null) {
                    mInstance = new GreenDaoManager();
                }
            }
        }
        return mInstance;
    }

    public DaoSession getSession() {
        return mDaoSession;
    }
}

