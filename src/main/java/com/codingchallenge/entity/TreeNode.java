package com.codingchallenge.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="TreeNode")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
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
}


