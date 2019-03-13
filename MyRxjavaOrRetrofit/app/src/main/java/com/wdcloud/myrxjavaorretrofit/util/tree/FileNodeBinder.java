package com.wdcloud.myrxjavaorretrofit.util.tree;

import android.view.View;
import android.widget.CheckedTextView;

import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.entity.TextbookChaptersBean;
import com.wdcloud.myrxjavaorretrofit.util.tree.tr.TreeNode;
import com.wdcloud.myrxjavaorretrofit.util.tree.tr.TreeViewBinder;


/**
 * Created by Umbrella on 2018/7/25.
 */

public class FileNodeBinder extends TreeViewBinder<FileNodeBinder.ViewHolder> {


    @Override
    public ViewHolder provideViewHolder(View view) {
        ViewHolder holder = new ViewHolder(view);
        holder.cTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cTv.isChecked()) {
                    //移除数据
                    postion = "";
                    holder.cTv.setChecked(false);
                    if (selectListener != null)
                    selectListener.treeSelectListener(null);

                } else {
                    if (checkedTextView != null) {
                        checkedTextView.setChecked(false);
                    }
                    //添加数据
                    holder.cTv.setChecked(true);
                    checkedTextView = holder.cTv;
                    postion = (String) holder.cTv.getTag();
                    if (selectListener != null)
                    selectListener.treeSelectListener((TreeNode) holder.cTv.getTag(R.id.tree_item_file_name));
                }
            }
        });
        return holder;
    }

    @Override
    public void bindView(ViewHolder holder, int i, TreeNode treeNode) {
        TextbookChaptersBean file = (TextbookChaptersBean) treeNode.getContent();
        if (postion.equals(file.getDirectoryName()+i)) {
            holder.cTv.setChecked(true);
            checkedTextView = holder.cTv;
        } else {
            holder.cTv.setChecked(false);
        }

        holder.cTv.setTag(R.id.tree_item_file_name, treeNode);
        holder.cTv.setTag(file.getDirectoryName()+i);

        holder.cTv.setText(file.getDirectoryName());
    }

    @Override
    public int getLayoutId() {
        return R.layout.tree_item_file;
    }

    public static class ViewHolder extends TreeViewBinder.ViewHolder {
        public CheckedTextView cTv;

        public ViewHolder(View itemView) {
            super(itemView);
            cTv = itemView.findViewById(R.id.tree_item_file_name);
        }

        public CheckedTextView getcTv() {
            return cTv;
        }
    }
}
