package com.wdcloud.myrxjavaorretrofit.entity;

/**
 * Created by Umbrella on 2018/5/17.
 */

public abstract class BaseEntity {

    private String execTime;
    private String isSuccess;
    private String msgCode;
    private boolean successFlg;
    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public boolean isSuccessFlg() {
        return successFlg;
    }

    public void setSuccessFlg(boolean successFlg) {
        this.successFlg = successFlg;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }
}
