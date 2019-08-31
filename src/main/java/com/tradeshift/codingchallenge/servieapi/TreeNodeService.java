package com.tradeshift.codingchallenge.servieapi;

import com.tradeshift.codingchallenge.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TreeNodeService {

    List<TreeNode> findTreeNodeByName(String name);

    List<TreeNode> findAllTreeNode();

    TreeNode add(TreeNode node , String newPosition ,Boolean addAsChild) throws Exception;

    TreeNode update(TreeNode node);

    void delete(long id) throws Exception;

    List<TreeNode> moveSubTree(Map<String, Object> parameters);
}
