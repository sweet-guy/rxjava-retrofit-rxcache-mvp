package com.wdcloud.myrxjavaorretrofit.util.tree.tr;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckedTextView;

import com.wdcloud.myrxjavaorretrofit.util.tree.TreeSelectListener;


public abstract class TreeViewBinder<VH extends RecyclerView.ViewHolder> implements LayoutItemType {
    public static CheckedTextView checkedTextView;
    public static String postion = "";
    public TreeSelectListener selectListener;
    public abstract VH provideViewHolder(View itemView);

    public abstract void bindView(VH holder, int position, TreeNode node);

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View rootView) {
            super(rootView);
        }

        protected <T extends View> T findViewById(@IdRes int id) {
            return (T) itemView.findViewById(id);
        }
    }

    public TreeSelectListener getTreeSelectListener() {
        return selectListener;
    }

    public void setTreeSelectListener(TreeSelectListener selectListener) {
        this.selectListener = selectListener;
    }
}