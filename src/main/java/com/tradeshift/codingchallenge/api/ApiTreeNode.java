package com.tradeshift.codingchallenge.api;

import com.tradeshift.codingchallenge.common.exception.BadRequestException;
import com.tradeshift.codingchallenge.common.exception.NotFoundException;
import com.tradeshift.codingchallenge.entity.TreeNode;
import com.tradeshift.codingchallenge.servieapi.TreeNodeService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/v1")
@Api(value="Tree Structure System", description="Operations pertaining in Tree Structure System")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Success|OK"), @ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"), @ApiResponse(code = 404, message = "not found!!!"), @ApiResponse(code = 500, message = "Resource not found")})
public class ApiTreeNode {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiTreeNode.class);

    @Autowired
    protected TreeNodeService treeNodeService;

    @ApiOperation(value = "Get list of all node", response = List.class, tags = "nodes")
    @ApiParam()
    @RequestMapping(value = "/nodes", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<TreeNode>> getAllNodes() {
        try{
            List<TreeNode> result = treeNodeService.findAllTreeNode();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception ex){
            throw new NotFoundException("Resource not found");
        }
    }

    @ApiOperation(value = "Get list of node child, by parent node name", response = List.class, tags = "nodes")
    @ApiParam(name = "name", value = "name of node", required = true)
    @RequestMapping(value = "/nodes/{name}", produces = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.GET)
    public ResponseEntity<List<TreeNode>> getNode(@PathVariable("name") String name) {
        try{
            List<TreeNode> result = treeNodeService.findTreeNodeByName(name);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception ex){
            throw new NotFoundException("Resource not found: " + name);
        }
    }

    @ApiOperation(value = "Add new node in tree", response = List.class, tags = "add")
    @ApiParam(name = "params",  value = "params should have three item first is nodeName which is name of new object of TreeNode " +
                                        " second is newposition which the node will be add in right side of it" +
                                        " if you want add new node as children of node which does not have children addAsChild should be true", required = true)


    @RequestMapping(value = "/nodes/{newPosition}/{addAsChild}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<TreeNode> addNode(@RequestBody TreeNode node , @PathVariable("newPosition") String newPosition , @PathVariable("addAsChild") Boolean addAsChild) {
        try{
            TreeNode result = treeNodeService.add(node , newPosition , addAsChild);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception ex){
            throw new NotFoundException("add new node is not possible");
        }
    }

    @ApiOperation(value = "Update a node", response = List.class, tags = "add")
    @ApiParam(name = "node", value = "a object of TreeNode type", required = true)
    @RequestMapping(value = "/nodes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<TreeNode> updateNode(@RequestBody TreeNode node) {
        try{
            TreeNode result = treeNodeService.update(node);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception ex){
            throw new NotFoundException("add new node is not possible");
        }
    }

    @ApiOperation(value = "Delete a node", response = List.class, tags = "delete")
    @ApiParam(name = "id", value = "id of TreeNode which you would delete", required = true)
    @RequestMapping(value = "/nodes/{id}" , method = RequestMethod.DELETE)
    public void deleteNode(@PathVariable("id") Long id) {
        try{
            treeNodeService.delete(id);
        }catch (Exception ex){
            throw new NotFoundException("add new node is not possible");
        }
    }

    @ApiOperation(value = "Move a sub tree to any position in the tree", tags = "moveSubTree")
    @ApiParam(name = "parameters", value = "It should had two item first is leftPositionOfTargeted it is the left position where the subtree is targeted, and second is current node which its parent will be moved", required = true)
    @RequestMapping(value = "/nodes", produces = "application/json",consumes = "application/json" , method = RequestMethod.PATCH)
    public ResponseEntity<List<TreeNode>> moveSubTree(@RequestBody Map<String, Object> parameters) {
        try{
            List<TreeNode> result =treeNodeService.moveSubTree(parameters);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception ex){
            throw new BadRequestException("Node cannot be updated");
        }
    }

}
