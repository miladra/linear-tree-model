package com.tradeshift.codingchallenge.Service;

import com.tradeshift.codingchallenge.common.exception.NotFoundException;
import com.tradeshift.codingchallenge.entity.TreeNode;
import com.tradeshift.codingchallenge.repositoryapi.TreeNodeRepository;
import com.tradeshift.codingchallenge.servieapi.TreeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TreeNodeServiceImpl implements TreeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TreeNodeServiceImpl.class);

    protected static final String STRUCTURE_NOT_FOUND_MESSAGE_KEY = "PROCESS.ERROR.NOT-FOUND";

    @Autowired
    protected TreeNodeRepository treeNodeRepository;

    @Override
    public TreeNode findById(Long treeNodeId) {
        Optional<TreeNode> treeNode = treeNodeRepository.findById(treeNodeId);

        if (treeNode == null) {
            NotFoundException structureNotFound = new NotFoundException("No structure found with the given id: " + treeNodeId);
            throw structureNotFound;
        }

        return treeNode.get();
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
