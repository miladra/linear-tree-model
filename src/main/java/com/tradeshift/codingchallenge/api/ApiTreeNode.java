package com.tradeshift.codingchallenge.api;

import com.tradeshift.codingchallenge.servieapi.TreeNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;

@RestController
@RequestMapping(value = "/rest", produces = "application/json", consumes = "application/json")
public class ApiTreeNode {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiTreeNode.class);

    @Autowired
    protected TreeNodeService treeNodeService;

    @RequestMapping(value = "/treeNode/{treeNodeName}", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<String> getTreeNode(@PathVariable String treeNodeName) {
        return treeNodeService.findByName(treeNodeName);
    }

}
