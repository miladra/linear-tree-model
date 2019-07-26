package com.tradeshift.codingchallenge.entity;

import javax.persistence.*;
import java.io.Serializable;


@MappedSuperclass
public abstract class BaseEntity implements Cloneable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

}