package com.wdcloud.myrxjavaorretrofit.base;

/**
 * Created by Umbrella on 2018/12/5.
 */

public class BaseEntity{
    private String code;
    private String msg;
    private long time;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
