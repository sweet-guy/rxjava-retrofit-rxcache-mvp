package com.wdcloud.myrxjavaorretrofit.util.tree;

import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;

import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.entity.TextbookChaptersBean;
import com.wdcloud.myrxjavaorretrofit.util.tree.tr.TreeNode;
import com.wdcloud.myrxjavaorretrofit.util.tree.tr.TreeViewBinder;


/**
 * Created by Umbrella on 2018/7/25.
 */

public class DirectoryNodeBinder extends TreeViewBinder<DirectoryNodeBinder.ViewHolder> {

    @Override
    public ViewHolder provideViewHolder(View view) {
        ViewHolder holder = new ViewHolder(view);
        holder.cTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.cTv.isChecked()){
                    postion = "";
                    holder.cTv.setChecked(false);
                    if (selectListener != null){
                        selectListener.treeSelectListener(null);
                    }
                }else {
                    if (checkedTextView != null){
                        checkedTextView.setChecked(false);
                    }
                    holder.cTv.setChecked(true);
                    checkedTextView = holder.cTv;
                    postion = (String) holder.cTv.getTag();
                    if (selectListener != null){
                        selectListener.treeSelectListener((TreeNode) holder.cTv.getTag(R.id.dir_item_dir_center_name));
                    }
                }
            }
        });

        return holder;
    }

    @Override
    public void bindView(ViewHolder holder, int i, TreeNode treeNode) {
        TextbookChaptersBean dir = (TextbookChaptersBean) treeNode.getContent();
        if (postion.equals(dir.getDirectoryName()+i)){
            holder.cTv.setChecked(true);
            checkedTextView = holder.cTv;
        }else {
            holder.cTv.setChecked(false);
        }


        holder.cTv.setTag(R.id.dir_item_dir_center_name,treeNode);
        holder.cTv.setTag(dir.getDirectoryName()+i);


        holder.cTv.setText(dir.getDirectoryName());
        int isExpand = treeNode.isExpand() ? R.mipmap.zjzk : R.mipmap.zjsq;//是否展开
        holder.imageV.setImageResource(isExpand);
        if (treeNode.isLeaf()) {
            holder.imageV.setVisibility(View.INVISIBLE);
        } else {
            holder.imageV.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getLayoutId() {
        return R.layout.dir_item_dir;
    }

    public static class ViewHolder extends TreeViewBinder.ViewHolder {

        private final ImageView imageV;
        private final CheckedTextView cTv;

        public ViewHolder(View itemView) {
            super(itemView);
            imageV = itemView.findViewById(R.id.dir_item_dir_left_no);
            cTv = itemView.findViewById(R.id.dir_item_dir_center_name);
        }

        public ImageView getImageV() {
            return imageV;
        }

        public CheckedTextView getcTv() {
            return cTv;
        }
    }


}
