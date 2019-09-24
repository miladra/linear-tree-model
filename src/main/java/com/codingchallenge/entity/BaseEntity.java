package com.codingchallenge.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
@Data
public abstract class BaseEntity implements Cloneable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;
}