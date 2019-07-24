package com.tradeshift.codingchallenge.servieapi;

import com.tradeshift.codingchallenge.entity.TreeNode;

import java.util.ArrayList;
import java.util.Map;

public interface TreeNodeService {
    ArrayList<String> findTreeNodeByName(String name);
    void add(Map<String, Object> parameters);
}
