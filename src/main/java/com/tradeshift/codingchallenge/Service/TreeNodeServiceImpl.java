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
     * This method move a node to any position in the tree
     * @param parameters include leftPositionOfTargeted(is the left position where the subtree is targeted), currentNode
     * @return void
     */
    @Override
    @Transactional()
    public List<TreeNode> UpdateWithSubTree(Map<String, Object> parameters){
        try
        {
            String leftPositionOfTargeted  = (String)parameters.get("newPosition");
            String currentName      = (String)parameters.get("currentNode");

            //Get Nodes
            TreeNode currentNode        = treeNodeRepository.findByName(currentName).get(0);
            TreeNode leftPositionOfTargetedNode   = treeNodeRepository.findByName(leftPositionOfTargeted).get(0);
            TreeNode currentNodeParrent = currentNode.getParentNode();
            Long oldRightPos = currentNodeParrent.getRightNodeId();

            if(currentNodeParrent.getParentNode() == null)
                throw new BadRequestException("move root is not possible");


            Long width = currentNodeParrent.getRightNodeId()- currentNodeParrent.getLeftNodeId() + 1;
            Long distance = leftPositionOfTargetedNode.getLeftNodeId() - currentNodeParrent.getRightNodeId() -1;
            Long tmppos = currentNodeParrent.getLeftNodeId();

            //for backwards movement
            if (distance < 0) {
                distance -= width;
                tmppos += width;
            }

            List<TreeNode> moveTree = treeNodeRepository.findTreeNodeByName(currentNodeParrent.getName());
            treeNodeRepository.CreateLeftNewSpaceForSubtree(width , leftPositionOfTargetedNode.getLeftNodeId());
            treeNodeRepository.CreateRightNewSpaceForSubtree(width , leftPositionOfTargetedNode.getLeftNodeId());


            for (TreeNode r : moveTree) {
                r.setLeftNodeId(r.getLeftNodeId() + distance);
                r.setRightNodeId(r.getRightNodeId() + distance);
                r.setHeight(r.getHeight() + leftPositionOfTargetedNode.getHeight());

            }

            currentNodeParrent.setParentNode(leftPositionOfTargetedNode.getParentNode());


            treeNodeRepository.RemoveLeftOldSubtreeSpace(width  , oldRightPos);
            treeNodeRepository.RemoveRightOldSubtreeSpace(width , oldRightPos);

            treeNodeRepository.flush();

            List<TreeNode> resultSubTree = treeNodeRepository.findTreeNodeByName(leftPositionOfTargeted);
            return resultSubTree;

        } catch (Exception ex){
            throw ex;
        }
    }

}
