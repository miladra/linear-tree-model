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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/rest/v1", produces = "application/json", consumes = "application/json")
@Api(value="Tree Structure System", description="Operations pertaining in Tree Structure System")
public class ApiTreeNode {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiTreeNode.class);

    @Autowired
    protected TreeNodeService treeNodeService;

    @ApiOperation(value = "Get list of node child, by parent node name", response = List.class, tags = "getTreeNode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Resource not found")
    })
    @ApiParam(name = "treeNodeName", value = "node name", required = true)
    @RequestMapping(value = "/treeNode/{treeNodeName}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<TreeNode>> getTreeNode(@PathVariable String treeNodeName) {
        try{
            List<TreeNode> result = treeNodeService.findTreeNodeByName(treeNodeName);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception ex){
            throw new NotFoundException("Resource not found: " + treeNodeName);
        }
    }

    @ApiOperation(value = "Move parent of current node to new position", tags = "updateTreeNode")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!"),
            @ApiResponse(code = 500, message = "Resource not found")
    })
    @ApiParam(name = "parameters", value = "it should had two item first is new position and second is current node", required = true)
    @RequestMapping(value = "/treeNode/update", method = RequestMethod.POST, produces = "application/json",consumes = "application/json")
    public String updateTreeNode(@RequestBody Map<String, Object> parameters) {
        try{
             treeNodeService.UpdateWithSubTree(parameters);
             return "Node added";
         }catch (Exception ex){
             throw new BadRequestException("Node cannot be updated");
         }
    }

}
