package com.learn.universityjpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;
    @Column(name="name", nullable=false)
    private String name;
    @Column(name="description", nullable=false)
    private String description;
}
