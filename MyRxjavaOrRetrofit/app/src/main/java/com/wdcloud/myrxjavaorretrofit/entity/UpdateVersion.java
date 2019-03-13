package com.wdcloud.myrxjavaorretrofit.entity;

/**
 * Created by Umbrella on 2018/12/13.
 */

public class UpdateVersion {

    /**
     * records : null
     * rows : {"info":"解决部分问题","id":"121123121212","allowversion":"1.1","latestversion":"1.3","createtime":"2018-08-23 17:33:12","address":"http://dl.wdcloud.cc/group6/M08/4B/B4/o4YBAFvPzyiAb9JOAH--pkPzej4731.apk","updatetime":"2018-08-23 17:33:12","location":"370200","type":"android","remarks":"备注信息","delflag":"A"}
     * msgCode : null
     * execTime : null
     * isSuccess : true
     */

    private String records;
    private RowsBean rows;
    private String msgCode;
    private String execTime;
    private String isSuccess;

    public String getRecords() {
        return records;
    }

    public void setRecords(String records) {
        this.records = records;
    }

    public RowsBean getRows() {
        return rows;
    }

    public void setRows(RowsBean rows) {
        this.rows = rows;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(String isSuccess) {
        this.isSuccess = isSuccess;
    }

    public static class RowsBean {
        /**
         * info : 解决部分问题
         * id : 121123121212
         * allowversion : 1.1
         * latestversion : 1.3
         * createtime : 2018-08-23 17:33:12
         * address : http://dl.wdcloud.cc/group6/M08/4B/B4/o4YBAFvPzyiAb9JOAH--pkPzej4731.apk
         * updatetime : 2018-08-23 17:33:12
         * location : 370200
         * type : android
         * remarks : 备注信息
         * delflag : A
         */

        private String info;
        private String id;
        private String allowversion;
        private String latestversion;
        private String createtime;
        private String address;
        private String updatetime;
        private String location;
        private String type;
        private String remarks;
        private String delflag;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAllowversion() {
            return allowversion;
        }

        public void setAllowversion(String allowversion) {
            this.allowversion = allowversion;
        }

        public String getLatestversion() {
            return latestversion;
        }

        public void setLatestversion(String latestversion) {
            this.latestversion = latestversion;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getDelflag() {
            return delflag;
        }

        public void setDelflag(String delflag) {
            this.delflag = delflag;
        }
    }
}
