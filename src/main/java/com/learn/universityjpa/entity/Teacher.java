package com.learn.universityjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {
    @Column(name="category", nullable=false)
    private String category;
}
