package com.tradeshift.codingchallenge.entity;


import javax.persistence.*;

@Entity
@Table(name="TreeNode")
public class TreeNode extends BaseEntity{

    public TreeNode() {}

    public TreeNode(Boolean isRoot, Long parentId, Long leftNodeId ,  Long rightNodeId) {
        this.isRoot = isRoot;
        this.ParentId = parentId;
        this.LeftNodeId = leftNodeId;
        this.RightNodeId = rightNodeId;
    }
    private Boolean isRoot;

    private Long ParentId;

    private Long LeftNodeId;

    private Long RightNodeId;

    @Column(name="IsRoot")
    public Boolean getIsRoot() {
        return isRoot;
    }

    @Column(name="ParentId")
    public Long getParentId() {
        return ParentId;
    }

    @Column(name="LeftNodeId")
    public Long getLeftNodeId() {
        return LeftNodeId;
    }

    @Column(name="RightNodeId")
    public Long getRightNodeId() {
        return RightNodeId;
    }

    public void setRoot(Boolean root) {
        isRoot = root;
    }

    public void setParentId(Long parentId) {
        ParentId = parentId;
    }

    public void setLeftNodeId(Long leftNodeId) {
        LeftNodeId = leftNodeId;
    }

    public void setRightNodeId(Long rightNodeId) {
        RightNodeId = rightNodeId;
    }
}


