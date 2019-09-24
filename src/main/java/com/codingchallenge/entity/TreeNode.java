package com.codingchallenge.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="TreeNode")
@Data
public class TreeNode extends BaseEntity{

    @Column(name="Name")
    private String name;

    @Column(name="LeftNodeId")
    private Long leftNodeId;

    @Column(name="RightNodeId")
    private Long rightNodeId;

    @Column(name="height")
    private Long height;

    @OneToMany(mappedBy="parentNode" , fetch=FetchType.EAGER)
    private Set<TreeNode> subNodes;

    @ManyToOne
    @JoinColumn(name="parentId" ,referencedColumnName = "id" )
    private TreeNode parentNode;

    @OneToMany(mappedBy="rootNode" , fetch=FetchType.LAZY)
    private Set<TreeNode> subRootNodes;

    @ManyToOne
    @JoinColumn(name="rootId" ,referencedColumnName = "id" )
    private TreeNode rootNode;
}


