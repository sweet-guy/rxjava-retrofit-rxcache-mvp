package com.wdcloud.myrxjavaorretrofit.util.tree;


import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.util.tree.tr.LayoutItemType;

/**
 * Created by Umbrella on 2018/7/25.
 */

public class Dir implements LayoutItemType {

    private String textbookChapterId;
    private String textbookId;
    private String versionId;
    private String parentId;
    private String directoryName;
    private int sort;
    private int grade;
    private String labelCode;
    private String labelValue;

    public String getName() {
        return directoryName;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dir_item_dir;
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

    public void setName(String name) {
        this.directoryName = name;
    }
}
