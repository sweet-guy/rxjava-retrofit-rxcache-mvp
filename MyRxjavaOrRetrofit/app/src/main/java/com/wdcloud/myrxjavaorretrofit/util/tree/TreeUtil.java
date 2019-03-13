package com.wdcloud.myrxjavaorretrofit.util.tree;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;


import com.wdcloud.myrxjavaorretrofit.R;
import com.wdcloud.myrxjavaorretrofit.entity.TextbookChaptersBean;
import com.wdcloud.myrxjavaorretrofit.util.tree.tr.TreeNode;
import com.wdcloud.myrxjavaorretrofit.util.tree.tr.TreeViewAdapter;
import com.wdcloud.myrxjavaorretrofit.util.tree.tr.TreeViewBinder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Umbrella on 2018/7/25.
 */

public class TreeUtil {
    private TreeViewAdapter adapter;

    public void setRecyclerViewTree(Context context, RecyclerView recyclerView, TreeSelectListener listener,
                                    List<TextbookChaptersBean> sectionList) {
        List<TreeNode> nodes = getTreeNodes(sectionList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        FileNodeBinder fileNodeBin = new FileNodeBinder();
        fileNodeBin.setTreeSelectListener(listener);
        DirectoryNodeBinder dirNodeBin = new DirectoryNodeBinder();
        dirNodeBin.setTreeSelectListener(listener);
        List<TreeViewBinder<? extends TreeViewBinder.ViewHolder>> binders = Arrays.asList(fileNodeBin, dirNodeBin);
        adapter = new TreeViewAdapter(nodes, binders);
        adapter.setOnTreeNodeListener(new TreeViewAdapter.OnTreeNodeListener() {
            @Override
            public boolean onClick(TreeNode node, RecyclerView.ViewHolder holder) {
                if (!node.isLeaf()) {
                    //是否有子节点并更新节点
                    onToggle(!node.isExpand(), holder);
                    //关闭上一个展开得
//                    if (!node.isExpand()){
//                        adapter.collapseBrotherNode(node);
//                    }
                } else {
                    //代表点击得最后得层级

                }
                return false;
            }

            @Override
            public void onToggle(boolean isExpand, RecyclerView.ViewHolder holder) {
                DirectoryNodeBinder.ViewHolder dirViewHolder = (DirectoryNodeBinder.ViewHolder) holder;
                final ImageView imageV = dirViewHolder.getImageV();
                int resId = isExpand ? R.mipmap.zjzk : R.mipmap.zjsq;//是否展开
                imageV.setImageResource(resId);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @NonNull
    private List<TreeNode> getTreeNodes(List<TextbookChaptersBean> sectionList) {
        List<TreeNode> treeNodes = new ArrayList<>();
        //获取测试数据
        for (TextbookChaptersBean bean : sectionList) {
            TreeNode tree = new TreeNode(bean);
            treeNodes.add(tree);
            if (bean.getSubTextbookChapters() != null && bean.getSubTextbookChapters().size() > 0) {
                digui(bean.getSubTextbookChapters(), tree);
            }
        }
        return treeNodes;
    }

    private void digui(List<TextbookChaptersBean> subTextbookChapters, TreeNode tree) {
        for (TextbookChaptersBean bean : subTextbookChapters) {
            TreeNode tree1 = new TreeNode(bean);
            tree.addChild(tree1);
            if (bean.getSubTextbookChapters() != null && bean.getSubTextbookChapters().size() > 0) {
                digui(bean.getSubTextbookChapters(), tree1);
            }
        }
    }


}
