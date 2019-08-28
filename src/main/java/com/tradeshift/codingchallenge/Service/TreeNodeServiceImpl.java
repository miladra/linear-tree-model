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

    @Override
    public List<TreeNode> findAllTreeNode() {
        List<TreeNode> treeNodes;
        try {

            treeNodes = treeNodeRepository.findAllTreeNode();

            if (treeNodes.isEmpty()) {
                throw new NotFoundException("Exception accrued");
            }

        }catch (Exception ex){
            throw  ex;
        }
        return treeNodes;
    }

    @Override
    public TreeNode add(TreeNode node) {
        TreeNode treeNodes;
        try {

            TreeNode currentNode = treeNodeRepository.findByName("A").get(0);

            treeNodeRepository.MoveRightSpace(Long.valueOf(2) , currentNode.getRightNodeId());
            treeNodeRepository.MoveLeftSpace(Long.valueOf(2)  , currentNode.getRightNodeId());

            treeNodes = treeNodeRepository.saveAndFlush(node);

        }catch (Exception ex){
            throw  ex;
        }
        return treeNodes;
    }

    @Override
    public void delete(long id) {
        TreeNode treeNodes;
        try {
            treeNodeRepository.delete(id);
        }catch (Exception ex){
            throw  ex;
        }
    }

    /**
     * This method move a node to any position in the tree
     * @param parameters include leftPositionOfTargeted(is the left position where the subtree is targeted), currentNode
     * @return void
     */
    @Override
    @Transactional()
    public List<TreeNode> moveSubTree(Map<String, Object> parameters){
        try
        {
            String leftPositionOfTargeted  = (String)parameters.get("newPosition");
            String currentName      = (String)parameters.get("currentNode");

            //Get Nodes
            TreeNode currentNode        = treeNodeRepository.findByName(currentName).get(0);
            TreeNode leftPositionOfTargetedNode   = treeNodeRepository.findByName(leftPositionOfTargeted).get(0);
            TreeNode currentNodeParent = currentNode.getParentNode();
            Long oldRightPos = currentNodeParent.getRightNodeId();

            if(currentNodeParent.getParentNode() == null)
                throw new BadRequestException("move root is not possible");


            Long width = currentNodeParent.getRightNodeId()- currentNodeParent.getLeftNodeId() + 1;
            Long distance;
            if(leftPositionOfTargetedNode.getLeftNodeId() >  currentNodeParent.getRightNodeId()) {
                distance = leftPositionOfTargetedNode.getLeftNodeId() - currentNodeParent.getRightNodeId() - 1;
            }else {
                distance = leftPositionOfTargetedNode.getLeftNodeId()  - currentNodeParent.getLeftNodeId();
            }

            List<TreeNode> moveTree = treeNodeRepository.findTreeNodeByName(currentNodeParent.getName());
            treeNodeRepository.CreateLeftNewSpaceForSubtree(width , leftPositionOfTargetedNode.getLeftNodeId());
            treeNodeRepository.CreateRightNewSpaceForSubtree(width , leftPositionOfTargetedNode.getLeftNodeId());

            treeNodeRepository.RemoveLeftOldSubtreeSpace(width  , oldRightPos);
            treeNodeRepository.RemoveRightOldSubtreeSpace(width , oldRightPos);

            for (TreeNode r : moveTree) {
                r.setLeftNodeId(r.getLeftNodeId() + distance);
                r.setRightNodeId(r.getRightNodeId() + distance);
                r.setHeight(r.getHeight() + leftPositionOfTargetedNode.getHeight());
            }
            currentNodeParent.setParentNode(leftPositionOfTargetedNode.getParentNode());

            treeNodeRepository.flush();

            List<TreeNode> resultSubTree = treeNodeRepository.findTreeNodeByName(leftPositionOfTargetedNode.getParentNode().getName());
            return resultSubTree;

        } catch (Exception ex){
            throw ex;
        }
    }

}
