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
     * if new position is between two node all node to its right would have their left and right values increased by two.
     * if the node is added as child of a node that has no children we need increase left and right values from its left.
     * @param parameters include newParentPosition and currentNode
     * @return void
     */

    @Override
    @Transactional()
    public void Update(Map<String, Object> parameters){

        try
        {
            String newPosition = (String)parameters.get("newParentPosition");
            String currentName = (String)parameters.get("currentNode");

            //Get Nodes
            TreeNode newPositionNode = treeNodeRepository.findByName(newPosition).get(0);
            TreeNode currentNode     = treeNodeRepository.findByName(currentName).get(0);

            if(newPositionNode.getRootNode().getId() == newPositionNode.getId()){
                throw new BadRequestException("Update root node is not possible");
            }

            if(currentNode.getSubNodes() == null || currentNode.getSubNodes().size() == 0) {

                Long width = currentNode.getRightNodeId()-currentNode.getLeftNodeId() + 1;
                treeNodeRepository.DeleteBetweenLeftAndRightNode(currentNode.getLeftNodeId(),currentNode.getRightNodeId());
                treeNodeRepository.UpdateRightAndReduceWidth(width , newPositionNode.getRightNodeId());
                treeNodeRepository.UpdateLeftAndReduceWidth(width ,newPositionNode.getRightNodeId());
            }
            else{
                throw new BadRequestException("Update a node with children is not possible");
            }


            if(newPositionNode.getSubNodes() != null && newPositionNode.getSubNodes().size() != 0) {
                //move all next mode after Node
                treeNodeRepository.UpdateRightId(newPositionNode.getRightNodeId());
                treeNodeRepository.UpdateLeftId(newPositionNode.getRightNodeId());
                //Create new node
                TreeNode treeNode = new TreeNode();
                treeNode.setName(currentName);
                treeNode.setParentNode(newPositionNode);
                treeNode.setRootNode(newPositionNode.getRootNode());
                treeNode.setLeftNodeId(newPositionNode.getRightNodeId() + 1);
                treeNode.setRightNodeId(newPositionNode.getRightNodeId() + 2);
                treeNode.setHeight(newPositionNode.getHeight() + 1);
                //Save new Node
                treeNodeRepository.save(treeNode);
            }
            else{

                //move all next mode after Node
                treeNodeRepository.UpdateRightId(newPositionNode.getLeftNodeId());
                treeNodeRepository.UpdateLeftId(newPositionNode.getLeftNodeId());
                //Create new node
                TreeNode treeNode = new TreeNode();
                treeNode.setName(currentName);
                treeNode.setParentNode(newPositionNode);
                treeNode.setRootNode(newPositionNode.getRootNode());
                treeNode.setLeftNodeId(newPositionNode.getLeftNodeId() + 1);
                treeNode.setRightNodeId(newPositionNode.getLeftNodeId() + 2);
                treeNode.setHeight(newPositionNode.getHeight() + 1);
                //Save new Node
                treeNodeRepository.save(treeNode);

            }


            treeNodeRepository.flush();

        } catch (Exception ex){

            throw ex;

        }
    }

    /**
     * parameters included three values
     * newParentPosition is new parent position
     * childOfNewParent is child of new parent which old node (or tree) should move next of it. the problem here is if parent has children we have to determin new node should add after which one.
     * @param parameters include newParentPosition, childOfNewParent, currentNode
     * @return void
     */
    @Override
    @Transactional()
    public void UpdateWithSubTree(Map<String, Object> parameters){

        try
        {
            String newPosition      = (String)parameters.get("newParentPosition");
            String childOfNewParent = (String)parameters.get("childOfNewParent");
            String currentName      = (String)parameters.get("currentNode");

            //Get Nodes
            TreeNode newPositionNode    = treeNodeRepository.findByName(newPosition).get(0);
            TreeNode currentNode        = treeNodeRepository.findByName(currentName).get(0);
            TreeNode currentNodeParrent = currentNode.getParentNode();
            TreeNode childOfNewParentNode = null;

            Long width = currentNodeParrent.getRightNodeId()- currentNodeParrent.getLeftNodeId() + 1;



            Long rightOfNewPosition;
            Long distance;
            if(newPositionNode.getSubNodes() != null && newPositionNode.getSubNodes().size() != 0) {
                distance = newPositionNode.getRightNodeId() - currentNodeParrent.getRightNodeId();

                for (TreeNode c : newPositionNode.getSubNodes()) {
                    if (c.getName().equals(childOfNewParent)) {
                        childOfNewParentNode = c;
                    }
                }

                if (childOfNewParentNode == null) {
                    throw new BadRequestException("The " + childOfNewParent + "is not child of " + newPosition);
                }
                rightOfNewPosition = childOfNewParentNode.getRightNodeId();
            }
            else{
                distance = newPositionNode.getRightNodeId() - currentNodeParrent.getRightNodeId() -1;
                rightOfNewPosition = newPositionNode.getRightNodeId();
            }

            if (distance < 0) {
                distance = Math.abs(distance);
            }

             List<TreeNode> moveTree = treeNodeRepository.findTreeNodeByName(currentNodeParrent.getName());
             treeNodeRepository.UpdateLeftBetweenCurentAndNewPosition(width , currentNodeParrent.getRightNodeId() , rightOfNewPosition);
              for (TreeNode r : moveTree) {

                 if(r.getRootNode() == null)
                     throw new BadRequestException("move root is not possible");

                 r.setLeftNodeId(r.getLeftNodeId() + distance);
                 r.setRightNodeId(r.getRightNodeId() + distance);
                 r.setHeight(r.getHeight() + newPositionNode.getHeight());
                 currentNodeParrent.setParentNode(newPositionNode);

             }

            treeNodeRepository.flush();

        } catch (Exception ex){

            throw ex;

        }
    }


}
