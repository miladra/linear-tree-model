package com.tradeshift.codingchallenge.Service;

import com.tradeshift.codingchallenge.common.exception.NotFoundException;
import com.tradeshift.codingchallenge.entity.TreeNode;
import com.tradeshift.codingchallenge.repository.TreeNodeRepository;
import com.tradeshift.codingchallenge.servieapi.TreeNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Map;

@Service
public class TreeNodeServiceImpl implements TreeNodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TreeNodeServiceImpl.class);

    protected static final String STRUCTURE_NOT_FOUND_MESSAGE_KEY = "PROCESS.ERROR.NOT-FOUND";

    @Autowired
    protected TreeNodeRepository treeNodeRepository;

    @Override
    public ArrayList<String> findTreeNodeByName(String name) {
        ArrayList<String> treeNodes = treeNodeRepository.findTreeNodeByName(name);

        if (treeNodes.isEmpty()) {
            NotFoundException structureNotFound = new NotFoundException("No tree Node found with the given id: " + name);
            throw structureNotFound;
        }

        return treeNodes;
    }

    @Override
    @Transactional()
    public void add(Map<String, Object> parameters){

        try
        {
            String afterNode = (String)parameters.get("afterNode");
            String newNode   = (String)parameters.get("newNode");
            //Get previous Node
            TreeNode afterTreeNode = treeNodeRepository.findByName(afterNode).get(0);


            //move all next mode after Node
            treeNodeRepository.UpdateRightId(afterTreeNode.getRightNodeId());
            treeNodeRepository.UpdateLefttId(afterTreeNode.getRightNodeId());

            //Create new node
            TreeNode treeNode = new TreeNode();
            treeNode.setName(newNode);
            treeNode.setIsRoot(false);
            treeNode.setLeftNodeId(afterTreeNode.getRightNodeId() + 1);
            treeNode.setRightNodeId(afterTreeNode.getRightNodeId() + 2);
            treeNode.setParentId(afterTreeNode.getId());

            //Save new Node
            treeNodeRepository.saveAndFlush(treeNode);

        } catch (Exception ex){

            throw ex;

        }
    }
}
