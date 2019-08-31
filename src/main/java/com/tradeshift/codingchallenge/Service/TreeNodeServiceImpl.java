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
import java.util.Optional;

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
    public TreeNode add(TreeNode node , String newPosition ,Boolean addAsChild) throws Exception {
        TreeNode treeNodes;
        try {

            TreeNode currentNode = treeNodeRepository.findByName(newPosition).get(0);

            Long position = currentNode.getRightNodeId();

            if(addAsChild){
                if(currentNode.getSubNodes() != null && currentNode.getSubNodes().size() != 0){
                    throw new Exception("Operation Error");
                }
                position = currentNode.getLeftNodeId();
            }

            treeNodeRepository.MoveRightSpace(Long.valueOf(2) , position);
            treeNodeRepository.MoveLeftSpace(Long.valueOf(2)  , position);


            node.setLeftNodeId(position + 1);
            node.setRightNodeId(position + 2);
            node.setHeight(currentNode.getHeight());
            node.setParentNode(currentNode.getParentNode());
            node.setRootNode(currentNode.getRootNode());
            treeNodes = treeNodeRepository.saveAndFlush(node);

        }catch (Exception ex){
            throw ex;
        }
        return treeNodes;
    }


    @Override
    public TreeNode update(TreeNode node){
        TreeNode treeNode;
        try {
            treeNode = treeNodeRepository.saveAndFlush(node);
        }catch (Exception ex){
            throw  ex;
        }
        return treeNode;
    }
    @Override
    public void delete(long id) throws Exception {

            try {

                Optional<TreeNode> currentNode = treeNodeRepository.findById(id);

                if(!currentNode.isPresent()){
                    throw new Exception("node not found");
                }

                treeNodeRepository.DeleteNodes(currentNode.get().getLeftNodeId() , currentNode.get().getRightNodeId());

                long width = currentNode.get().getRightNodeId() - currentNode.get().getLeftNodeId() + 1;
                treeNodeRepository.RemoveRightSpace(width , currentNode.get().getRightNodeId());
                treeNodeRepository.RemoveLeftSpace(width, currentNode.get().getRightNodeId());


            }catch (Exception ex){
                throw ex;
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
