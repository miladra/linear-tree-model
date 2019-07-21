package com.tradeshift.codingchallenge.api;

import com.tradeshift.codingchallenge.entity.TreeNode;
import com.tradeshift.codingchallenge.servieapi.TreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiStructure {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiStructure.class);

    @Autowired
    protected TreeService structureService;

    @RequestMapping(value = "/structure/{treeNodeId}", method = RequestMethod.GET, produces = "application/json")
    public TreeNode getStructure(@PathVariable long treeNodeId) {
        return structureService.findById(treeNodeId);
    }

}
