package com.tradeshift.codingchallenge.entity;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public abstract class BaseEntity implements Cloneable, Serializable {

    private long id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}