package com.tradeshift.codingchallenge.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="TreeNode")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
public class TreeNode extends BaseEntity{

    public TreeNode() {}

    public TreeNode(String name, Long leftNodeId , Long rightNodeId) {
        this.leftNodeId = leftNodeId;
        this.rightNodeId = rightNodeId;
        this.name = name;
    }

    @Column(name="Name")
    private String name;

    @Column(name="LeftNodeId")
    private Long leftNodeId;

    @Column(name="RightNodeId")
    private Long rightNodeId;

    @Column(name="height")
    private Long height;

    @JsonIgnore
    @OneToMany(mappedBy="parentNode" , fetch=FetchType.EAGER)
    private Set<TreeNode> subNodes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="parentId" ,referencedColumnName = "id" )
    private TreeNode parentNode;

    ///

    @JsonIgnore
    @OneToMany(mappedBy="rootNode" , fetch=FetchType.LAZY)
    private Set<TreeNode> subRootNodes;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="rootId" ,referencedColumnName = "id" )
    private TreeNode rootNode;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLeftNodeId() {
        return leftNodeId;
    }

    public void setLeftNodeId(Long leftNodeId) {
        this.leftNodeId = leftNodeId;
    }

    public Long getRightNodeId() {
        return rightNodeId;
    }

    public void setRightNodeId(Long rightNodeId) {
        this.rightNodeId = rightNodeId;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Set<TreeNode> getSubNodes() {
        return subNodes;
    }

    public void setSubNodes(Set<TreeNode> subNodes) {
        this.subNodes = subNodes;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public Set<TreeNode> getSubRootNodes() {
        return subRootNodes;
    }

    public void setSubRootNodes(Set<TreeNode> subRootNodes) {
        this.subRootNodes = subRootNodes;
    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(TreeNode rootNode) {
        this.rootNode = rootNode;
    }
}


