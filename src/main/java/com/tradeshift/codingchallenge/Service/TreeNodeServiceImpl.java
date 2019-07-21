package com.tradeshift.codingchallenge.Service;

import com.tradeshift.codingchallenge.common.exception.NotFoundException;
import com.tradeshift.codingchallenge.entity.TreeNode;
import com.tradeshift.codingchallenge.repository.TreeNodeRepository;
import com.tradeshift.codingchallenge.servieapi.TreeNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TreeNodeServiceImpl implements TreeNodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TreeNodeServiceImpl.class);

    protected static final String STRUCTURE_NOT_FOUND_MESSAGE_KEY = "PROCESS.ERROR.NOT-FOUND";

    @Autowired
    protected TreeNodeRepository treeNodeRepository;

    @Override
    public ArrayList<String> findByName(String name) {
        ArrayList<String> treeNodes = treeNodeRepository.findTreeNodeByName(name);

        if (treeNodes.isEmpty()) {
            NotFoundException structureNotFound = new NotFoundException("No tree Node found with the given id: " + name);
            throw structureNotFound;
        }

        return treeNodes;
    }


    @Override
    public void updateTreeNode(TreeNode treeNode) {
     try{
          treeNodeRepository.save(treeNode);
     } catch (Exception ex){
         NotFoundException structureNotFound = new NotFoundException("No node found with the given id: " + treeNode.getId());
         throw structureNotFound;
       }
    }

    @Override
    public void add(TreeNode treeNode){
        treeNodeRepository.save(treeNode);
    }
}
