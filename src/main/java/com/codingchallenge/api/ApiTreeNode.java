package com.codingchallenge.api;

import com.codingchallenge.Mapper.TreeModelMapper;
import com.codingchallenge.common.exception.BadRequestException;
import com.codingchallenge.common.exception.NotFoundException;
import com.codingchallenge.domain.TreeModel;
import com.codingchallenge.entity.TreeNode;
import com.codingchallenge.servieapi.TreeNodeService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/v1")
@Api(value="Tree Structure System", description="Operations pertaining in Tree Structure System")
@ApiResponses(value = {@ApiResponse(code = 200, message = "Success|OK"), @ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"), @ApiResponse(code = 404, message = "not found!!!"), @ApiResponse(code = 500, message = "Resource not found")})
public class ApiTreeNode {

    private static final Logger logger = LoggerFactory.getLogger(ApiTreeNode.class);

    @Autowired
    protected TreeNodeService treeNodeService;

    @ApiOperation(value = "Get list of all node", response = List.class, tags = "nodes")
    @ApiParam()
    @RequestMapping(value = "/nodes", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
    public ResponseEntity<List<TreeModel>> getAllNodes() {
        try{
            List<TreeNode> result = treeNodeService.findAllTreeNode();
            List<TreeModel> response = TreeModelMapper.INSTANCE.toListOfTreeModel(result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new NotFoundException("Resource not found");
        }
    }

    @ApiOperation(value = "Get list of node child, by parent node name", response = List.class, tags = "nodes")
    @ApiParam(name = "name", value = "name of node", required = true)
    @RequestMapping(value = "/nodes/{name}", produces = MediaType.APPLICATION_JSON_VALUE , method = RequestMethod.GET)
    public ResponseEntity<List<TreeModel>> getNode(@PathVariable("name") String name) {
        try{
            List<TreeNode> result = treeNodeService.findTreeNodeByName(name);
            List<TreeModel> response = TreeModelMapper.INSTANCE.toListOfTreeModel(result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Add new node in tree", response = List.class, tags = "add")
    @RequestMapping(value = "/nodes/{newPosition}/{addAsChild}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<TreeModel> addNode( @ApiParam("the node will be add in right ") @PathVariable("newPosition") String newPosition ,
                                             @ApiParam("if you want add new node as children of node which does not have children addAsChild should be true") @PathVariable("addAsChild")  String addAsChild,
                                             @ApiParam("new object of TreeModel") @RequestBody TreeModel node) {
        try{
            TreeNode inputNode = TreeModelMapper.INSTANCE.toTreeNode(node);
            TreeNode result = treeNodeService.add(inputNode , newPosition , addAsChild);
            TreeModel response = TreeModelMapper.INSTANCE.toTreeModel(result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new NotFoundException("add new node is not possible");
        }
    }

    @ApiOperation(value = "Update a node", response = List.class, tags = "add")
    @ApiParam(name = "node", value = "a object of TreeModel type", required = true)
    @RequestMapping(value = "/nodes", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<TreeModel> updateNode(@RequestBody TreeModel node) {
        try{
            TreeNode inputNode = TreeModelMapper.INSTANCE.toTreeNode(node);
            TreeNode result = treeNodeService.update(inputNode);
            TreeModel response = TreeModelMapper.INSTANCE.toTreeModel(result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new NotFoundException("Update node is not possible");
        }
    }

    @ApiOperation(value = "Delete a node", response = List.class, tags = "delete")
    @ApiParam(name = "id", value = "id of TreeModel which you would delete", required = true)
    @RequestMapping(value = "/nodes/{id}" ,method = RequestMethod.DELETE)
    public String deleteNode(@PathVariable("id") String id) {
        try{
            treeNodeService.delete(Long.valueOf(id));
            return "";
        }catch (Exception ex){
            throw new NotFoundException("Delete new node is not possible" + ex.getStackTrace());
        }
    }

    @ApiOperation(value = "Move a sub tree to any position in the tree", tags = "moveSubTree")
    @ApiParam(name = "parameters", value = "It should had two item first is leftPositionOfTargeted it is the left position where the subtree is targeted, and second is current node which its parent will be moved", required = true)
    @RequestMapping(value = "/nodes/{newPosition}/{currentNode}", produces = "application/json", method = RequestMethod.PATCH)
    public ResponseEntity<List<TreeModel>> moveSubTree(@PathVariable("newPosition") String newPosition , @PathVariable("currentNode") String currentNode) {
        try{
            List<TreeNode> result =treeNodeService.moveSubTree(newPosition , currentNode);
            List<TreeModel> response = TreeModelMapper.INSTANCE.toListOfTreeModel(result);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception ex){
            throw new BadRequestException("Node cannot be updated");
        }
    }
}
