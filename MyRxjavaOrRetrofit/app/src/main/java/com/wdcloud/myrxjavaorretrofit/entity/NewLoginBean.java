package com.wdcloud.myrxjavaorretrofit.entity;

import com.wdcloud.myrxjavaorretrofit.base.BaseEntity;

/**
 * Created by Umbrella on 2018/12/18.
 */

public class NewLoginBean{

    /**
     * id : 1100001000002292431
     * result : 1
     * usertype : 02
     * loginid : 2002468700
     * msg : [WARNING] !Request method is 'GET', suggest use 'POST'
     */

    private String id;
    private String result;
    private String usertype;
    private String loginid;
    private String msg;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
