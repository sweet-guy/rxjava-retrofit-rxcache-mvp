package com.wdcloud.myrxjavaorretrofit.entity;

import java.util.List;

/**
 * Created by Umbrella on 2019/1/10.
 */

public class NjIInfoBean {
    private String success;
    private NjResultData result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public NjResultData getNjResultData() {
        return result;
    }

    public void setNjResultData(NjResultData njResultData) {
        this.result = njResultData;
    }

    public static class NjResultData{
        List<NjResultBean> data;

        public List<NjResultBean> getData() {
            return data;
        }

        public void setData(List<NjResultBean> data) {
            this.data = data;
        }
    }
    public static class NjResultBean{
        private String id;
        private String classes_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getClasses_name() {
            return classes_name;
        }

        public void setClasses_name(String classes_name) {
            this.classes_name = classes_name;
        }
    }
}
