package com.codingchallenge.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode
public class TreeModel{

    private long id;
    private String name;
    private Long leftNodeId;
    private Long rightNodeId;
    private Long height;

    @ToString.Exclude
    private Set<TreeModel> subNodes;
    private TreeModel parentNode;

    @ToString.Exclude
    private Set<TreeModel> subRootNodes;
    private TreeModel rootNode;
}


