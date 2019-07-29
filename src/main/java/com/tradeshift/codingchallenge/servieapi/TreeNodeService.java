package com.tradeshift.codingchallenge.servieapi;

import com.tradeshift.codingchallenge.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface TreeNodeService {

    List<TreeNode> findTreeNodeByName(String name);

    List<TreeNode> UpdateWithSubTree(Map<String, Object> parameters);
}
