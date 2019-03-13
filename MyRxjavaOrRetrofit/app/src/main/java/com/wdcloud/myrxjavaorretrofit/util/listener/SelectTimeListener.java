package com.wdcloud.myrxjavaorretrofit.util.listener;

import android.view.View;

import java.util.Date;

/**
 * Created by Umbrella on 2018/7/18.
 */

public interface SelectTimeListener {
    int startTime = 1;
    int endTime = 2;
    int taskTime=3;
    int taskendTime=4;
    void onTimeSelect(Date date, View v, int index);
}
