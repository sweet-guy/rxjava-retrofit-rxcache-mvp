package com.wdcloud.myrxjavaorretrofit.entity;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Umbrella on 2019/1/10.
 */

public class SchoolDataResult {
    private String success;
    private ResultBean result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ResultBean getResultBean() {
        return result;
    }

    public void setResultBean(ResultBean resultBean) {
        this.result = resultBean;
    }

    public static class ResultBean{
        private List<SchoolBean> data;

        public List<SchoolBean> getData() {
            return data;
        }

        public void setData(List<SchoolBean> data) {
            this.data = data;
        }
    }
    public static class SchoolBean{
        private String org_id;
        private String school_name;
        private String id;

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
}
