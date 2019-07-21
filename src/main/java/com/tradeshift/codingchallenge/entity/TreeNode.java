package com.tradeshift.codingchallenge.entity;


import javax.persistence.*;

@Entity
@Table(name="TreeNode")
public class TreeNode extends BaseEntity{

    public TreeNode() {}

    public TreeNode(Boolean isRoot, Long parentId, Long leftNodeId ,  Long rightNodeId , String name) {
        this.isRoot = isRoot;
        this.ParentId = parentId;
        this.LeftNodeId = leftNodeId;
        this.RightNodeId = rightNodeId;
        this.name = name;
    }
    private Boolean isRoot;

    private  String name;

    private Long ParentId;

    private Long LeftNodeId;

    private Long RightNodeId;

    @Column(name="isRoot")
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

    @Column(name="Name")
    public String getName() {
        return name;
    }

    public void setIsRoot(Boolean root) {
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

    public void setName(String name) { this.name = name; }
}


