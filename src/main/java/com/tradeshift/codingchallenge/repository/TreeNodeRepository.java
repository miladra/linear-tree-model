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

    @Query("SELECT node " +
           "FROM TreeNode as node , TreeNode as parent " +
           "WHERE node.leftNodeId BETWEEN parent.leftNodeId AND parent.rightNodeId AND parent.name = :nodeName  " +
           "ORDER BY node.leftNodeId")
    List<TreeNode> findTreeNodeByName(@Param("nodeName") String nodeName);

    @Modifying
    @Query("UPDATE TreeNode AS node SET node.leftNodeId  = node.leftNodeId  + :width WHERE node.leftNodeId  >= :newLeftPos ")
    void CreateLeftNewSpaceForSubtree(@Param("width") Long width , @Param("newLeftPos") Long newLeftPos);

    @Modifying
    @Query("UPDATE TreeNode AS node SET node.rightNodeId = node.rightNodeId + :width WHERE node.rightNodeId >= :newLeftPos")
    void CreateRightNewSpaceForSubtree(@Param("width") Long width , @Param("newLeftPos") Long newLeftPos);

    @Modifying
    @Query("UPDATE TreeNode AS node SET node.leftNodeId  = node.leftNodeId  - :width WHERE node.leftNodeId  > :oldRightPos ")
    void RemoveLeftOldSubtreeSpace(@Param("width") Long width , @Param("oldRightPos") Long oldRightPos);

    @Modifying
    @Query("UPDATE TreeNode AS node SET node.rightNodeId = node.rightNodeId - :width WHERE node.rightNodeId > :oldRightPos")
    void RemoveRightOldSubtreeSpace(@Param("width") Long width , @Param("oldRightPos") Long oldRightPos);
}
