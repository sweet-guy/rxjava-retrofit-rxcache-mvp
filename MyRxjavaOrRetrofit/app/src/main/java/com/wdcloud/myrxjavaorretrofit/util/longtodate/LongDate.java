package com.wdcloud.myrxjavaorretrofit.util.longtodate;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Umbrella on 2018/12/10.
 */

public class LongDate {
    public static String longToDate(){
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }
}
