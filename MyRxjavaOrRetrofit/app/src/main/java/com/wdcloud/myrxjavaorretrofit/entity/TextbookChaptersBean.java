package com.wdcloud.myrxjavaorretrofit.entity;

import com.google.gson.Gson;
import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.util.tree.tr.LayoutItemType;

import java.util.List;

/**
 * Created by Umbrella on 2018/8/8.
 */

public class TextbookChaptersBean implements LayoutItemType {

    private String textbookChapterId;
    private String textbookId;
    private String versionId;
    private String parentId;
    private String directoryName;
    private int sort;
    private int grade;
    private String labelCode;
    private String labelValue;
    private List<TextbookChaptersBean> subTextbookChapters;

    public static TextbookChaptersBean objectFromData(String str) {

        return new Gson().fromJson(str, TextbookChaptersBean.class);
    }

    public String getTextbookChapterId() {
        return textbookChapterId;
    }

    public void setTextbookChapterId(String textbookChapterId) {
        this.textbookChapterId = textbookChapterId;
    }

    public String getTextbookId() {
        return textbookId;
    }

    public void setTextbookId(String textbookId) {
        this.textbookId = textbookId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabelValue() {
        return labelValue;
    }

    public void setLabelValue(String labelValue) {
        this.labelValue = labelValue;
    }

    public List<TextbookChaptersBean> getSubTextbookChapters() {
        return subTextbookChapters;
    }

    public void setSubTextbookChapters(List<TextbookChaptersBean> subTextbookChapters) {
        this.subTextbookChapters = subTextbookChapters;
    }

    @Override
    public int getLayoutId() {
        return getSubTextbookChapters() != null && getSubTextbookChapters().size() > 0 ? R.layout.dir_item_dir : R.layout.tree_item_file;
    }

}
