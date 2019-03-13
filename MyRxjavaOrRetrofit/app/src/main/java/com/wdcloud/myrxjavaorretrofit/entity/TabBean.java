package com.wdcloud.myrxjavaorretrofit.entity;

/**
 * Created by Umbrella on 2019/1/2.
 */

public class TabBean {
    private String tabname;
    private String id;
    private Boolean select=false;

    public TabBean(String tabname, String id) {
        this.tabname = tabname;
        this.id = id;
    }

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }

    public String getTabname() {
        return tabname;
    }

    public void setTabname(String tabname) {
        this.tabname = tabname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
