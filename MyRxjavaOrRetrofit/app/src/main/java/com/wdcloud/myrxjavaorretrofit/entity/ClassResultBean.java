package com.wdcloud.myrxjavaorretrofit.entity;

import java.util.List;

/**
 * Created by Umbrella on 2019/1/10.
 */

public class ClassResultBean {
    private List<ClassinfoBean> result;

    public List<ClassinfoBean> getResult() {
        return result;
    }

    public void setResult(List<ClassinfoBean> result) {
        this.result = result;
    }

    public static class ClassinfoBean{
        private String grade_name;
        private String id;

        public String getGrade_name() {
            return grade_name;
        }

        public void setGrade_name(String grade_name) {
            this.grade_name = grade_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
