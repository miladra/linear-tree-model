package com.codingchallenge.entity;

import lombok.Data;
import lombok.ToString;
import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToOne
    @JoinColumn(name="parentId" ,referencedColumnName = "id" )
    private TreeNode parentNode;

    @OneToMany(mappedBy="parentNode" , fetch=FetchType.LAZY)
    @ToString.Exclude
    private Set<TreeNode> subNodes = new HashSet<>();

    @OneToMany(mappedBy="rootNode" , fetch=FetchType.LAZY)
    @ToString.Exclude
    private Set<TreeNode> subRootNodes = new HashSet<>();

    @ManyToOne
    @JoinColumn(name="rootId" ,referencedColumnName = "id" )
    private TreeNode rootNode;
}


