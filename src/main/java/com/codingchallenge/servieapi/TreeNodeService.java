package com.codingchallenge.servieapi;

import com.codingchallenge.entity.TreeNode;

import java.util.List;

public interface TreeNodeService {

    List<TreeNode> findTreeNodeByName(String name);

    List<TreeNode> findAllTreeNode();

    TreeNode add(TreeNode node , String newPosition ,String addAsChild) throws Exception;

    TreeNode update(TreeNode node) throws Exception;

    void delete(long id) throws Exception;

    List<TreeNode> moveSubTree(String newPosition ,String currentNodeName);
}
