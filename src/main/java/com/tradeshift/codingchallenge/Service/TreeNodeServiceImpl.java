package com.tradeshift.codingchallenge.Service;

import com.tradeshift.codingchallenge.common.exception.BadRequestException;
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
import java.util.List;
import java.util.Map;

@Service
public class TreeNodeServiceImpl implements TreeNodeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TreeNodeServiceImpl.class);

    protected static final String STRUCTURE_NOT_FOUND_MESSAGE_KEY = "PROCESS.ERROR.NOT-FOUND";

    @Autowired
    protected TreeNodeRepository treeNodeRepository;

    @Override
    public List<TreeNode> findTreeNodeByName(String name) {
        List<TreeNode> treeNodes;
        try {

            if (name.isEmpty() || name.equals("")) {

            }
           treeNodes = treeNodeRepository.findTreeNodeByName(name);

            if (treeNodes.isEmpty()) {
                throw new NotFoundException("No tree Node found with the given name: " + name);
            }

        }catch (Exception ex){
            throw  ex;
        }
        return treeNodes;
    }


    /**
     * This method move parent of current node to new position
     * @param parameters include newPosition, currentNode
     * @return void
     */
    @Override
    @Transactional()
    public void UpdateWithSubTree(Map<String, Object> parameters){

        try
        {

            String newPosition    = (String)parameters.get("newPosition");
            String currentName      = (String)parameters.get("currentNode");

            //Get Nodes
            TreeNode currentNode        = treeNodeRepository.findByName(currentName).get(0);
            TreeNode newParentPosition   = treeNodeRepository.findByName(newPosition).get(0);
            TreeNode currentNodeParrent = currentNode.getParentNode();

            if(currentNodeParrent.getParentNode() == null)
                throw new BadRequestException("move root is not possible");


            Long width = currentNodeParrent.getRightNodeId()- currentNodeParrent.getLeftNodeId() + 1;


            List<TreeNode> moveTree = treeNodeRepository.findTreeNodeByName(currentNodeParrent.getName());
            treeNodeRepository.UpdateLeftAndIncreaseWidth(width , currentNodeParrent.getRightNodeId());
            treeNodeRepository.UpdateRightAndIncreaseWidth(width , currentNodeParrent.getRightNodeId());

            Long pos;
            if(newParentPosition.getRightNodeId()>currentNode.getRightNodeId()){
                pos = newParentPosition.getRightNodeId() - width;
            } else {
                pos = newParentPosition.getRightNodeId();
            }
            treeNodeRepository.UpdateEqualLeftAndIncreaseWidth(width , pos);
            treeNodeRepository.UpdateEqualRightAndIncreaseWidth(width ,pos);

            Long dis;
            if(newParentPosition.getRightNodeId()>currentNode.getRightNodeId()){
                dis = newParentPosition.getRightNodeId() - currentNode.getRightNodeId() - 2;
            } else {
                dis = newParentPosition.getRightNodeId() - currentNode.getRightNodeId() - 2 + width;
            }

            for (TreeNode r : moveTree) {
                r.setLeftNodeId(r.getLeftNodeId() + dis);
                r.setRightNodeId(r.getRightNodeId() + dis);
                r.setHeight(r.getHeight() + newParentPosition.getHeight());

            }

            currentNodeParrent.setParentNode(newParentPosition);


            treeNodeRepository.flush();

        } catch (Exception ex){

            throw ex;

        }
    }


}
