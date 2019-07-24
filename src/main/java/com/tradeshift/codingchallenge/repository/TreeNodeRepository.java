package com.tradeshift.codingchallenge.repository;

import com.tradeshift.codingchallenge.entity.TreeNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface TreeNodeRepository extends JpaRepository<TreeNode, Long> {

    List<TreeNode> findByName(String name);

    @Query("SELECT node.name " +
           "FROM TreeNode AS node, TreeNode AS parent " +
           "WHERE node.leftNodeId BETWEEN parent.leftNodeId AND parent.rightNodeId AND parent.name = :nodeName  " +
           "ORDER BY node.leftNodeId")
    ArrayList<String> findTreeNodeByName(@Param("nodeName") String nodeName);

    @Modifying
    @Query("UPDATE TreeNode AS node SET node.rightNodeId = node.rightNodeId + 2 WHERE node.rightNodeId > :rightNodeId")
    void UpdateRightId(Long rightNodeId);

    @Modifying
    @Query("UPDATE TreeNode AS node SET node.leftNodeId = node.leftNodeId + 2 WHERE node.leftNodeId > :rightNodeId")
    void UpdateLefttId(Long rightNodeId);

}
