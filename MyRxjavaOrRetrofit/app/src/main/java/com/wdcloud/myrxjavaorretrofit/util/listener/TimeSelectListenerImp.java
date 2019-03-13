package com.wdcloud.myrxjavaorretrofit.util.listener;

import android.view.View;

import com.bigkoo.pickerview.TimePickerView;

import java.util.Date;

/**
 * Created by Umbrella on 2018/7/18.
 */

public class TimeSelectListenerImp implements TimePickerView.OnTimeSelectListener {
    private SelectTimeListener timeListener;
    private int index;

    public TimeSelectListenerImp(SelectTimeListener timeListener, int index) {
        this.timeListener = timeListener;
        this.index = index;
    }

    @Override
    public void onTimeSelect(Date date, View v) {
        timeListener.onTimeSelect(date,v,index);
    }
}
