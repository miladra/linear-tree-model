package com.tradeshift.codingchallenge.api;

import com.tradeshift.codingchallenge.exception.BadRequestException;
import com.tradeshift.codingchallenge.servieapi.TreeNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/treeNode", produces = "application/json", consumes = "application/json")
public class ApiTreeNode {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiTreeNode.class);

    @Autowired
    protected TreeNodeService treeNodeService;

    @RequestMapping(value = "/{treeNodeName}", method = RequestMethod.GET, produces = "application/json")
    public ArrayList<String> getTreeNode(@PathVariable String treeNodeName) {
        try{
            return treeNodeService.findTreeNodeByName(treeNodeName);
        }catch (Exception ex){
            throw new BadRequestException("Resource not found: " + treeNodeName);
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json",consumes = "application/json")
    public void addTreeNode(@RequestBody Map<String, Object> parameters) {
        try{
         treeNodeService.add(parameters);
         }catch (Exception ex){
             throw new BadRequestException("Node cannot be updated");
         }
    }

}
