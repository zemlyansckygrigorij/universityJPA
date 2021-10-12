package com.learn.universityjpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student  extends Person {
    @ManyToOne
    private Group group;
}
