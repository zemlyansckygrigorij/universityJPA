package com.learn.universityjpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student  extends Person {
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_group", nullable = false)
    private Group group;
}
