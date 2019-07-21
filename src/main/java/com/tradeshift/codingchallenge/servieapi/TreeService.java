package com.tradeshift.codingchallenge.servieapi;

import com.tradeshift.codingchallenge.entity.TreeNode;

public interface TreeService {
    TreeNode findById(Long treeNodeId);
    void updateTreeNode(TreeNode treeNode);
    void add(TreeNode treeNode);
}
