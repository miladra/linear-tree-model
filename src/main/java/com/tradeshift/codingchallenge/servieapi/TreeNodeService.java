package com.tradeshift.codingchallenge.servieapi;

import com.tradeshift.codingchallenge.entity.TreeNode;

import java.util.ArrayList;

public interface TreeNodeService {
    ArrayList<String> findByName(String name);
    void updateTreeNode(TreeNode treeNode);
    void add(TreeNode treeNode);
}
