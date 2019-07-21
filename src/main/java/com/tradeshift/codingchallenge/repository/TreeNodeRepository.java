package com.tradeshift.codingchallenge.repository;

import com.tradeshift.codingchallenge.entity.TreeNode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.stream.Stream;

@Repository
public interface TreeNodeRepository extends CrudRepository<TreeNode, Long> {

    @Query("SELECT node.name " +
           "FROM TreeNode AS node, TreeNode AS parent " +
           "WHERE node.leftNodeId BETWEEN parent.leftNodeId AND parent.rightNodeId AND parent.name = :nodeName  " +
           "ORDER BY node.leftNodeId")
    ArrayList<String> findTreeNodeByName(@Param("nodeName") String nodeName);
}
