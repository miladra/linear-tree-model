package com.codingchallenge.domain;

import lombok.Data;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
public class TreeModel{

    private long id;
    private String name;
    private Long leftNodeId;
    private Long rightNodeId;
    private Long height;
    private Set<TreeModel> subNodes;
    private TreeModel parentNode;
    private Set<TreeModel> subRootNodes;
    private TreeModel rootNode;
}


