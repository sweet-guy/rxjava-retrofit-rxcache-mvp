package com.wdcloud.myrxjavaorretrofit.util;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.gson.Gson;
import com.wdcloud.myrxjavaorretrofit.entity.UpdateVersion;

/**
 * Created by Umbrella on 2018/12/13.
 */

public class MyAutoUpdate {
    public Activity activity;
    public int versionCode = 0;
    public String versionName = "";
    public UpdateVersion versionInfo = null;

    public MyAutoUpdate(Activity activity) {
        this.activity = activity;
        getCurrentVersion();    //得到当前版本
    }

    public void getCurrentVersion() {
        try {
            PackageInfo info = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
            this.versionCode = info.versionCode;
            this.versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static final int NO_NEED_UPDATE = 0;
    public static final int NORMAL_NEED_UPDATE = 1;
    public static final int STRONG_NEED_UPDATE = 2;

    public UpdateVersion getUpdateInfo(String jsonstr) {
        Gson gson = new Gson();
        UpdateVersion updateVersion = gson.fromJson(jsonstr, UpdateVersion.class);
        return updateVersion;
    }

    public int compareversion(UpdateVersion updateVersion) throws Exception {
        String allowversion = updateVersion.getRows().getAllowversion();
        String latestversion = updateVersion.getRows().getLatestversion();
        double Allowversion = Double.parseDouble(allowversion);
        double Lastversion = Double.parseDouble(latestversion);
        if (updateVersion != null) {
            if (versionCode == Lastversion) {
                return NO_NEED_UPDATE;//不更新
            } else {
                if (versionCode >= Allowversion) {
                    return NORMAL_NEED_UPDATE;//弱更新
                } else{
                    return STRONG_NEED_UPDATE;//强更新
                }
            }
        } else {
            return NO_NEED_UPDATE;//不更新
        }
    }
}
